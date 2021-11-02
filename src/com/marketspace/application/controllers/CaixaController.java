package com.marketspace.application.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.ProdutoService;
import com.marketspace.application.services.VendaService;
import com.marketspace.data.mappings.ItemVenda;
import com.marketspace.data.mappings.Produto;
import com.marketspace.data.mappings.Venda;
import com.marketspace.domain.entities.ControlViewState;
import com.marketspace.domain.validators.BasicValidator;
import com.marketspace.domain.validators.CPFValidator;
import com.marketspace.domain.validators.InputValidator;
import com.marketspace.domain.viewModels.ItemVendaViewModel;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CaixaController extends Navigation {

	@FXML
	private AnchorPane MainForm;

	@FXML
	private Button btnVoltar;

	@FXML
	private Pane pnlPesquisaPessoa1;

	@FXML
	private TextField txtCodigoBarrasProduto;

	@FXML
	private Button btnAdicionarProduto;
		
	@FXML
	private Button btnRemoverProduto;

	@FXML
	private Button btnRemoverUnidade;

	@FXML
	private TableView<ItemVendaViewModel> grdItemVenda;

	@FXML
	private TableColumn<ItemVendaViewModel, Integer> colCodigoItem;

	@FXML
	private TableColumn<ItemVendaViewModel, String> colNomeItem;

	@FXML
	private TableColumn<ItemVendaViewModel, Float> colPrecoItem;
	@FXML

	private TableColumn<ItemVendaViewModel, String> colCodigoBarras;
	@FXML

	private TableColumn<ItemVendaViewModel, Integer> colQuantidadeItem;

	@FXML
	private TextField txtCpfCliente;

	@FXML
	private TextField txtTotalCompra;

	@FXML
	private Pane pnlPesquisaPessoa11;

	@FXML
	private Button btnNovaCompra;

	@FXML
	private Button btnFinalizarCompra;
	
    @FXML
    private Button btnCancelarOperacao;


	private ProdutoService _produtoService;
	private VendaService _vendaService;
	private List<ItemVenda> itensVenda;
	private Venda venda;
	
	public CaixaController() {
		_produtoService = new ProdutoService();
		_vendaService = new VendaService();
		itensVenda = new ArrayList<ItemVenda>();
		venda = new Venda(itensVenda);
	}
	
	@FXML
	void initialize() {
		ConfigurarTabelaItemVenda();
		DefinirVisualizacaPadrao();
	}

	@FXML
	void AdicionarItemNaVenda(ActionEvent event) {

		Produto prod = _produtoService.PesquisarProdutoPorCodigoDeBarras(txtCodigoBarrasProduto.getText());
		if (prod == null) {
			new DialogMessage("Nenhum produto com este código de barras foi encontrado", "Código de barras inexistente",
					AlertType.WARNING).Show();
			return;
		}

		if (ItemJaFoiAdicionado())
			AdicionarUmaUnidadeNoProduto(txtCodigoBarrasProduto.getText());
		else {
			itensVenda.add(new ItemVenda(prod, venda, 1, new Date(), new Date()));
			venda.CalcularTotalVenda();
		}
		AtualizarTela();
	}
	
	@FXML
	void RemoverUnidadeItemVenda(ActionEvent event) {
		RemoverUmaUnidadeNoProduto(txtCodigoBarrasProduto.getText());
		AtualizarTela();
	}

	@FXML
	void RemoverItemVenda(ActionEvent event) {
		RemoverItemVenda(txtCodigoBarrasProduto.getText());
		AtualizarTela();
	}

	@FXML
	void CancelarOperacaoEvent(ActionEvent event) {
		DefinirVisualizacaPadrao();
	}
	
	@FXML
	void NovaCompraEvent(ActionEvent event) {
		DefinirVisualizacaPadrao();	
		LimparFormulario();
	}
	
	@FXML
	void FinalizarCompraEvent(ActionEvent event) {
		if(!ValidarFormulario()) return;
		
		if(_vendaService.FinalizarVenda(MontarVenda())) {
			new DialogMessage("Venda finalizada com sucesso.", "Venda Finalizada", AlertType.INFORMATION).Show();
			LimparFormulario();
		}
		else
			new DialogMessage("Ocorreu um processar a venda.", "Não foi possível concluir a venda", AlertType.WARNING).Show();
	}
	
	
	public void AtualizarTela() {
		AtualizarGridItens();
		AtualizarTotalVenda();
	}
	
	public void AtualizarTotalVenda() {
		txtTotalCompra.setText(String.valueOf(venda.getValorTotal()));
	}
	
	public void AtualizarGridItens() {
		RemoverItemSeQuantidadeZerada();
		List<ItemVendaViewModel> ItensVendaViewModel = new ArrayList<ItemVendaViewModel>();
		for (ItemVenda iv : itensVenda) {
			ItensVendaViewModel.add(iv.ConvertTo());
		}
		grdItemVenda.setItems(FXCollections.observableArrayList(ItensVendaViewModel));
		System.out.println(venda.getValorTotal());
	}

	private void RemoverItemSeQuantidadeZerada() {
		for (int i = 0; i < itensVenda.size(); i++) {
			if (itensVenda.get(i).getQuantidade() <= 0) {
				itensVenda.remove(i);
			}
		}
		if(itensVenda.isEmpty()) {
			DefinirVisualizacaPadrao();
		}
	}
	
	private void RemoverItemVenda(String codigoBarras) {
		this.venda.RemoverItemVenda(codigoBarras);
	}

	public boolean ItemJaFoiAdicionado() {
		for (ItemVenda iv : itensVenda) {
			if (iv.getProduto().getCodigoBarras().equals(txtCodigoBarrasProduto.getText())) {
				return true;
			}
		}
		return false;
	}

	private void AdicionarUmaUnidadeNoProduto(String codigoBarras) {
		for (ItemVenda iv : itensVenda) {
			if (iv.getProduto().getCodigoBarras().equals(codigoBarras)) {
				iv.AdicionarUmaUnidadeAoItem();
				break;
			}
		}
	}

	private void RemoverUmaUnidadeNoProduto(String codigoBarras) {
		for (ItemVenda iv : itensVenda) {
			if (iv.getProduto().getCodigoBarras().equals(codigoBarras)) {
				iv.RemoverUmaUnidadeAoItem();
				break;
			}
		}
	}

	public void ConfigurarTabelaItemVenda() {
		colCodigoItem.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
		colNomeItem.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colQuantidadeItem.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
		colPrecoItem.setCellValueFactory(new PropertyValueFactory<>("Preco"));
		colCodigoBarras.setCellValueFactory(new PropertyValueFactory<>("CodigoBarras"));

		grdItemVenda.setRowFactory(rf -> {
			TableRow<ItemVendaViewModel> row = new TableRow<ItemVendaViewModel>();
			row.setOnMouseClicked(evento -> {
				ItemVendaViewModel Item = row.getItem();
				if (Item != null ) {
					txtCodigoBarrasProduto.setText(Item.getCodigoBarras());
					DefinirVisualizacaoEditarItem();
				}
			});
			return row;
		});
	}
	
	public void DefinirVisualizacaPadrao() {
		List<ControlViewState> controles = Arrays.asList(
				new ControlViewState(txtCodigoBarrasProduto, true),
				new ControlViewState(btnAdicionarProduto, true),
				new ControlViewState(btnRemoverUnidade, false),
				new ControlViewState(btnRemoverProduto, false),
				new ControlViewState(btnCancelarOperacao, false)
				);
		DefineVisualizacaoTela(controles);
	}	
	
	public void DefinirVisualizacaoEditarItem() {
		List<ControlViewState> controles = Arrays.asList(
				new ControlViewState(txtCodigoBarrasProduto, false),
				new ControlViewState(btnAdicionarProduto, true),
				new ControlViewState(btnRemoverUnidade, true),
				new ControlViewState(btnRemoverProduto, true),
				new ControlViewState(btnCancelarOperacao, true)
				);
		DefineVisualizacaoTela(controles);
	}
	
	public void DefineVisualizacaoTela(List<ControlViewState> controles) {

		for (ControlViewState c : controles) {
			Control control = c.getControl();
			if (control instanceof TextField)
				((TextField) control).setDisable(!c.isEnabled());
			
			if (control instanceof Button)
				((Button) control).setDisable(!c.isEnabled());
		}
	}
	
	public void LimparFormulario() {
		List<Control> controles = new ArrayList<Control>
		(List.of(txtCodigoBarrasProduto, txtCpfCliente, grdItemVenda));
		txtTotalCompra.setText("0");
		itensVenda = new ArrayList<ItemVenda>();
		venda = new Venda(itensVenda);
		controles.forEach(control -> {
			if (control instanceof ComboBox<?>)
				((ComboBox<?>) control).setValue(null);
			if (control instanceof TextField)
				((TextField) control).setText("");
			if (control instanceof TableView<?>)
				((TableView<?>) control).getItems().clear();
		});
	}
	
	public Venda MontarVenda() {
		Date dataAtual = new Date();
		venda.setCPF(txtCpfCliente.getText());
		venda.setDataAtualizacao(dataAtual);
		venda.setDataCadastro(dataAtual);
		return venda;
	}
	
	public boolean ValidarFormulario() {
		try {
			
			if (Float.valueOf(grdItemVenda.getItems().size()) == 0)
				throw new IllegalArgumentException("Compra deve conter um ou mais itens.");
			
			if (Float.valueOf(txtTotalCompra.getText()) == 0)
				throw new IllegalArgumentException("Compra deve ter um valor maior que 0.");
			
			if(!BasicValidator.IsnullOrEmpty(txtCpfCliente.getText())) {
				if (!CPFValidator.isCPF(txtCpfCliente.getText()))
					throw new IllegalArgumentException("CPF do cliente inválido.");
			}
			
			return true;
		} catch (IllegalArgumentException e) {
			new DialogMessage("Campos inválidos no cadastro de produto", e.getMessage(), AlertType.WARNING).Show();
			return false;
		}
	}

	
	public void ConfigurarInputs() {
		InputValidator.SetNumericLimitInput(txtCodigoBarrasProduto, 20);
		InputValidator.SetNumericLimitInput(txtCpfCliente, 11);
		InputValidator.SetFloatLimitInput(txtTotalCompra,20);
	}

}
