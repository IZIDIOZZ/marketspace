package com.marketspace.application.controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.VendaService;
import com.marketspace.data.mappings.ItemVenda;
import com.marketspace.data.mappings.Venda;
import com.marketspace.domain.entities.DateHelper;
import com.marketspace.domain.entities.ProdutoQuantidade;
import com.marketspace.domain.viewModels.RelatorioAnaliticoViewModel;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RelatorioAnaliticoController extends Navigation {

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dtpInicial;

    @FXML
    private DatePicker dtpFinal;

    @FXML
    private Button btnGerarRelatórioAnalítico;

    @FXML
    private Button btnNovoRelatorio;

    @FXML
    private TableView<RelatorioAnaliticoViewModel> grdRelatorioAnalitico;

    @FXML
    private TableColumn<RelatorioAnaliticoViewModel, Integer> colCodigoVenda;

    @FXML
    private TableColumn<RelatorioAnaliticoViewModel, Integer> colCodigoProduto;

    @FXML
    private TableColumn<RelatorioAnaliticoViewModel, Integer> colQuantidade;

    @FXML
    private TableColumn<RelatorioAnaliticoViewModel, Float> colValorProduto;
    
    @FXML
    private TableColumn<RelatorioAnaliticoViewModel, String> colNomeProduto;

    @FXML
    private TextField txtTotalComprasPeriodo;

    @FXML
    private TextField txtProdutoMaisVendido;

    @FXML
    private TextField txtMediaComprasPeriodo;

    private VendaService _vendaService;
    
    public RelatorioAnaliticoController() {
    	_vendaService= new VendaService();
    }
   
    @FXML
    public void initialize() throws ParseException {
    	ConfigurarFormulario();
    }
    
    @FXML
    void GerarRelatorioAnaliticoEvent(ActionEvent event) {
    	if(!EntradasFormularioSaoValidas()) return;
    	List<ItemVenda> vendasItens = _vendaService.BuscarItensVendaDetalhado(DateHelper.LocalDateToDate(dtpInicial.getValue()),
    																		  DateHelper.LocalDateToDate(dtpFinal.getValue()));
    	if(vendasItens.isEmpty()) {
    		LimparFormulario();
    		new DialogMessage("Nenhum Resultado Encontrado", "Nenhuma Venda Encontrada no Período", AlertType.WARNING).Show();
    		return;
    	}
    	
    	MontarGridRelatorioAnalitico(vendasItens);
    	CalcularValorTotalDeVendasNoPeriodo(vendasItens);
    	CalcularProdutoMaisVendido(vendasItens);
    	CalcularMediaCompradaNoPeriodo();
		HabilitarBotaoNovoRelatorio(true);
    }
    
	@FXML
    void NovoRelatorioEvent(ActionEvent event) {
		LimparFormulario();
		HabilitarBotaoNovoRelatorio(false);
    }
	
	private void CalcularMediaCompradaNoPeriodo() {
		List<Venda> vendas = _vendaService.BuscarVendasResumidas(DateHelper.LocalDateToDate(dtpInicial.getValue()),
				DateHelper.LocalDateToDate(dtpFinal.getValue()));
		
		int contadorVendas = vendas.size();
		
		Float valorTotal = 0F;
		for(Venda item: vendas) {
			valorTotal += item.getValorTotal();			
		}
		
		txtMediaComprasPeriodo.setText(String.valueOf((valorTotal/contadorVendas)));
	}
	
	public void CalcularProdutoMaisVendido(List<ItemVenda> vendasItens) {
		List<ProdutoQuantidade> produtos = new ArrayList<ProdutoQuantidade>();
		for(ItemVenda item: vendasItens) {
			
			if(!ProdutoQuantidade.ProdutoJaEstahNaColecao(produtos,item.getProdutoId())) {
				produtos.add(item.ToProdutoQuantidade());
			}
			else {
				ProdutoQuantidade.AdicionaQuantidadeVendidaProduto(produtos,item);
			}
		}
		txtProdutoMaisVendido.setText(ProdutoQuantidade.RetornarProdutoMaisVendido(produtos).getNome());
	}
	
	public void CalcularValorTotalDeVendasNoPeriodo(List<ItemVenda> vendaItens) {
		Float valorTotal = 0F;
		for(ItemVenda item: vendaItens) {
			valorTotal += (item.getQuantidade() * item.getValor());			
		}
		txtTotalComprasPeriodo.setText(String.valueOf(valorTotal));
	}
    
    public void MontarGridRelatorioAnalitico(List<ItemVenda> vendas) {
    	List<RelatorioAnaliticoViewModel> registrosRelatorio = new ArrayList<RelatorioAnaliticoViewModel>();
    	for(ItemVenda v: vendas) {
    		registrosRelatorio.add(v.ConvertToRelatorioAnaliticoViewModel());
    	}
    	grdRelatorioAnalitico.setItems(FXCollections.observableArrayList(registrosRelatorio));
    }
    
    public boolean EntradasFormularioSaoValidas() {
		try {

			if (dtpInicial.getValue().isAfter(dtpFinal.getValue()))
				throw new IllegalArgumentException("Data inicial não pode ser maior que a data final.");
			
			return true;
		} catch (IllegalArgumentException e) {
			new DialogMessage("Entradas Inválidas", e.getMessage(), AlertType.WARNING).Show();
			return false;
		}
	}
    
    public void ConfigurarFormulario() {
    	ConfigurarGridAnalitico();
    	ConfigurarEntradasDeData();
    }
    
    private void ConfigurarEntradasDeData() {
    	LocalDate dataAtual = LocalDate.now();
    	dtpInicial.setValue(LocalDate.of(dataAtual.getYear(), dataAtual.getMonthValue()-1, dataAtual.getDayOfMonth()));
    	dtpFinal.setValue(dataAtual);
	}

	public void ConfigurarGridAnalitico() {
    	colCodigoVenda.setCellValueFactory(new PropertyValueFactory<>("CodigoVenda"));
    	colCodigoProduto.setCellValueFactory(new PropertyValueFactory<>("CodigoProduto"));
    	colQuantidade.setCellValueFactory(new PropertyValueFactory<>("QuantidadeVendida"));
    	colValorProduto.setCellValueFactory(new PropertyValueFactory<>("ValorProduto")); 
    	colNomeProduto.setCellValueFactory(new PropertyValueFactory<>("NomeProduto"));  	
    	
    }
	

	
	public void LimparFormulario() {
		List<Control> controles = new ArrayList<Control>(List.of(txtProdutoMaisVendido,grdRelatorioAnalitico));
		txtMediaComprasPeriodo.setText("0"); 
		txtTotalComprasPeriodo.setText("0");
		ConfigurarEntradasDeData();
		controles.forEach(control -> {
			if (control instanceof ComboBox<?>)
				((ComboBox<?>) control).setValue(null);
			if (control instanceof TextField)
				((TextField) control).setText("");
			if (control instanceof TableView<?>)
				((TableView<?>) control).getItems().clear();
		});
	}
	
	public void HabilitarBotaoNovoRelatorio(boolean habilita) {
		btnNovoRelatorio.setDisable(!habilita);
		btnGerarRelatórioAnalítico.setDisable(habilita);
	}
}

