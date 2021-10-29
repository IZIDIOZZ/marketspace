package com.marketspace.application.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.PessoaService;
import com.marketspace.application.services.ProdutoService;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.Produto;
import com.marketspace.domain.enums.TipoRespostaBotaoEnum;
import com.marketspace.domain.validators.BasicValidator;
import com.marketspace.domain.viewModels.FornecedorViewModel;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CadastroProdutoController extends Navigation {

	@FXML
	private AnchorPane MainForm;

	@FXML
	private Button btnVoltar;

	@FXML
	private Pane pnlPesquisaPessoa;

	@FXML
	private TextField txtCodigoProduto;

	@FXML
	private Button btnPesquisarProduto;

	@FXML
	private TextField txtCodigoFornecedor;

	@FXML
	private TextField txtNome;

	@FXML
	private PasswordField txtCodigoBarras;

	@FXML
	private PasswordField txtPreco;

	@FXML
	private Pane pnlPesquisaPessoa1;

	@FXML
	private TextField txtPesquisarFornecedor;

	@FXML
	private Button btnPesquisarFornecedor;

	@FXML
	private Button btnCancelarOperacao;

	@FXML
	private Button btnNovoCadastro;

	@FXML
	private Button btnRemoverProduto;

	@FXML
	private Button btnAtualizarProduto;

	@FXML
	private Button btnCadastrarProduto;

	@FXML
	private TableView<FornecedorViewModel> grdFornecedor;

	@FXML
	private TableColumn<FornecedorViewModel, Integer> colCodigoFornecedor;

	@FXML
	private TableColumn<FornecedorViewModel, String> colCNPJ;

	@FXML
	private TableColumn<FornecedorViewModel, String> colRazaoSocial;

	@FXML
	private TableColumn<FornecedorViewModel, String> colNomeFantasia;

	private PessoaService _pessoaService;

	private ProdutoService _produtoService;

	public CadastroProdutoController() {
		_pessoaService = new PessoaService();
		_produtoService = new ProdutoService();
	}

	@FXML
	void initialize() {
		ConfigurarTabelaFornecedor();
	}

	@FXML
	void AtualizarProdutoEvent(ActionEvent event) {

	}

	@FXML
	void CadastrarProdutoEvent(ActionEvent event) {
		
	}

	@FXML
	void CancelarOperacaoEvent(ActionEvent event) {

	}

	@FXML
	void NovoProdutoEvent(ActionEvent event) {

	}

	@FXML
	void PesquisarFornecedorEvent(ActionEvent event) {

	}

	@FXML
	void PesquisarProdutoPorIdEnvent(ActionEvent event) {
		Produto produto = _produtoService.PesquisarProdutoPorId(Integer.valueOf(txtCodigoProduto.getText()));
		if (produto == null) {
			new DialogMessage("Nenhum produto com o id " + txtCodigoProduto.getText() + " encontrado.",
					"Produto Não Encontrado", AlertType.WARNING).Show();
			return;
		}
		PreencherFormulario(produto);
	}

	@FXML
	void RemoverProdutoEvent(ActionEvent event) {
		if (BasicValidator.IsnullOrEmpty(txtCodigoProduto.getText())) {
			new DialogMessage("Pesquise o produto que deseja para remover.", "Nenhum produto selecionado para remoção.",
					AlertType.WARNING).Show();
			return;
		}
		
		Optional<ButtonType> reposta = new DialogMessage("Deseja realmente remover este produto?",
				"Ao Aceitar, você estará removendo permanentemente este produto.", AlertType.CONFIRMATION,
				TipoRespostaBotaoEnum.YesOrNo).Show();
		
		if(reposta.get() == ButtonType.OK) {
			if (_produtoService.RemoverProduto(Integer.valueOf(txtCodigoProduto.getText()))) 
				new DialogMessage("Produto removido com sucesso.", "Produto removido com sucesso", AlertType.INFORMATION).Show();
			else 
				new DialogMessage("Ocorreu um erro ao remover este produto.", "Não foi possível remover o produto.", AlertType.WARNING).Show();
		}
	}

	public void PreencherFormulario(Produto produto) {
		txtCodigoBarras.setText(produto.getCodigoBarras());
		txtCodigoFornecedor.setText(String.valueOf(produto.getFornecedor().getId()));
		txtCodigoProduto.setText(String.valueOf(produto.getId()));
		txtNome.setText(produto.getNome());
		txtPreco.setText(String.valueOf(produto.getPreco()));
		grdFornecedor.getItems().add(produto.getFornecedor().ConverterToFornecedorViewModel());
	}

	public void AdicionarFornecedorNoGridFornecedor(List<Pessoa> fornecedores) {
		List<FornecedorViewModel> fornecedorViewModel = new ArrayList<FornecedorViewModel>();
		for (Pessoa fornecedor : fornecedores) {
			fornecedorViewModel.add(fornecedor.ConverterToFornecedorViewModel());
		}
		grdFornecedor.setItems(FXCollections.observableArrayList(fornecedorViewModel));
	}
	
	public void CriarProdutoAtravesDeFormulario() {
		
	}

	public void ConfigurarTabelaFornecedor() {
		colCodigoFornecedor.setCellValueFactory(new PropertyValueFactory<>("Id"));
		colCNPJ.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));
		colRazaoSocial.setCellValueFactory(new PropertyValueFactory<>("RazaoSocial"));
		colNomeFantasia.setCellValueFactory(new PropertyValueFactory<>("NomeFantasia"));

		grdFornecedor.setRowFactory(rf -> {
			TableRow<FornecedorViewModel> row = new TableRow<FornecedorViewModel>();
			row.setOnMouseClicked(evento -> {
				FornecedorViewModel fornecedor = row.getItem();
				if (fornecedor != null)
					txtCodigoFornecedor.setText(Integer.toString(fornecedor.getId()));
			});
			return row;
		});
	}

}
