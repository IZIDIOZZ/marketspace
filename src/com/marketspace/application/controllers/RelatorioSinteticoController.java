package com.marketspace.application.controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.VendaService;
import com.marketspace.data.mappings.Venda;
import com.marketspace.domain.viewModels.RelatorioSinteticoViewModel;

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

public class RelatorioSinteticoController extends Navigation {

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dtpInicial;

    @FXML
    private DatePicker dtpFinal;

    @FXML
    private Button btnGerarRelatorioSintetico;

    @FXML
    private Button btnNovoRelatorio;

    @FXML
    private TextField txtTotalVendasRealizadas;

    @FXML
    private TextField txtTotalProdutosVendidos;

    @FXML
    private TextField txtMediaProdutosPorVenda;

    @FXML
    private TableView<RelatorioSinteticoViewModel> grdRelatorioSintetico;

    @FXML
    private TableColumn<RelatorioSinteticoViewModel, Integer> colCodigoVenda;

    @FXML
    private TableColumn<RelatorioSinteticoViewModel, Float> colTotalVenda;

    @FXML
    private TableColumn<RelatorioSinteticoViewModel, Integer> colQuantidadeTotalProdutos;

    @FXML
    private TableColumn<RelatorioSinteticoViewModel, String> colDataVenda;
    
    
    private VendaService _vendaService;
    
    public RelatorioSinteticoController() {
    	_vendaService= new VendaService();
    }
    
    @FXML
    public void initialize() throws ParseException {
    	ConfigurarFormulario();
    }
    
    @FXML
    void GerarRelatorioSinteticoEvent(ActionEvent event) {
    	if(!EntradasFormularioSaoValidas()) return;
    	List<Venda> vendas = _vendaService.BuscarVendasResumidas(LocalDateToDate(dtpInicial.getValue()),
    																  LocalDateToDate(dtpFinal.getValue()));
    	
    	if(vendas.isEmpty()) {
    		LimparFormulario();
    		new DialogMessage("Nenhum Resultado Encontrado", "Nenhuma Venda Encontrada no Período", AlertType.WARNING).Show();
    		return;
    	}
    	
    	MontarGridRelatorioSintetico(vendas);
		HabilitarBotaoNovoRelatorio(true);
		CalcularTotalDeVendasRealizadas(vendas);
		CalcularTotalDeProdutosVendidos(vendas);
		CalcularMediaDeProdutosVendidos(vendas);
    }

  
	@FXML
    void NovoRelatorioEvent(ActionEvent event) {
    	LimparFormulario();
    	HabilitarBotaoNovoRelatorio(false);
    }
    
    public void MontarGridRelatorioSintetico(List<Venda> vendas) {
    	List<RelatorioSinteticoViewModel> registrosRelatorio = new ArrayList<RelatorioSinteticoViewModel>();
    	for(Venda v: vendas) {
    		registrosRelatorio.add(v.ConvertToRelatorioSinteticoViewModel());
    	}
    	grdRelatorioSintetico.setItems(FXCollections.observableArrayList(registrosRelatorio));
    }
    
    private void CalcularTotalDeVendasRealizadas(List<Venda> vendas) {
    	txtTotalVendasRealizadas.setText(String.valueOf(vendas.size()));
  	}
    
    private void CalcularTotalDeProdutosVendidos(List<Venda> vendas) {
    	int quantidadeTotalDeProdutos = 0;
    	for(Venda venda: vendas) {
    		quantidadeTotalDeProdutos+=venda.getQuantidadeTotalDeProdutosVendidos();
    	}
    	txtTotalProdutosVendidos.setText(String.valueOf(quantidadeTotalDeProdutos));
  	}
    
    private void CalcularMediaDeProdutosVendidos(List<Venda> vendas) {
    	Float quantidadeTotalDeProdutos = 0F;
    	
    	for(Venda venda: vendas) {
    		quantidadeTotalDeProdutos+=venda.getQuantidadeTotalDeProdutosVendidos();
    	}
    	txtMediaProdutosPorVenda.setText(String.valueOf(quantidadeTotalDeProdutos/vendas.size()));
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
    	colTotalVenda.setCellValueFactory(new PropertyValueFactory<>("ValorTotalVenda"));
    	colQuantidadeTotalProdutos.setCellValueFactory(new PropertyValueFactory<>("QuantidadeTotalProdutosVendidos"));
    	colDataVenda.setCellValueFactory(new PropertyValueFactory<>("DataVenda")); 	
    }
	
	public Date LocalDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public void LimparFormulario() {
		List<Control> controles = new ArrayList<Control>(List.of(txtMediaProdutosPorVenda,
				txtTotalProdutosVendidos,
				txtTotalVendasRealizadas));
		ConfigurarEntradasDeData();
		controles.forEach(control -> {
			if (control instanceof ComboBox<?>)
				((ComboBox<?>) control).setValue(null);
			if (control instanceof TextField)
				((TextField) control).setText("0");
			if (control instanceof TableView<?>)
				((TableView<?>) control).getItems().clear();
		});
	}
	
	public void HabilitarBotaoNovoRelatorio(boolean habilita) {
		btnNovoRelatorio.setDisable(!habilita);
		btnGerarRelatorioSintetico.setDisable(habilita);
	}
}
