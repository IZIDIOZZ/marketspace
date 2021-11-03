package com.marketspace.application.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.PessoaService;
import com.marketspace.application.services.ProdutoService;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.Produto;
import com.marketspace.domain.entities.ControlViewState;
import com.marketspace.domain.enums.TipoRespostaBotaoEnum;
import com.marketspace.domain.validators.BasicValidator;
import com.marketspace.domain.validators.InputValidator;
import com.marketspace.domain.viewModels.FornecedorViewModel;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	private TextField txtCodigoBarras;

	@FXML
	private TextField txtPreco;

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

	private Produto produto;

	public CadastroProdutoController() {
		_pessoaService = new PessoaService();
		_produtoService = new ProdutoService();
		produto = new Produto();
	}

	@FXML
	void initialize() {
		ConfigurarInputs();
		ConfigurarTabelaFornecedor();
		DefinirPadraoVisualizacao();
		
	}

	@FXML
	void AtualizarProdutoEvent(ActionEvent event) {
		
		if (_produtoService.CodigoDeBarrasJaExiste(txtCodigoBarras.getText(),Integer.valueOf(txtCodigoProduto.getText()))) {
			new DialogMessage("O Código de barras já foi cadastrado.", "Código de Barras já cadastro.", AlertType.WARNING).Show();
			return;
		}
		
		if (!ValidarFormulario())
			return;
		
		if (_produtoService.AtualizarProduto(AtualizarProdutoAtravesDeFormulario(produto)))
			new DialogMessage("Produto Atualizado com sucesso", "Produto Atualizado com sucesso", AlertType.INFORMATION)
					.Show();
		else
			new DialogMessage("Ocorreu um erro ao atualizar o Produto.", "Falha ao Atualizar", AlertType.ERROR).Show();
	}

	@FXML
	void CadastrarProdutoEvent(ActionEvent event) {
		
		if (_produtoService.CodigoDeBarrasJaExiste(txtCodigoBarras.getText())) {
			new DialogMessage("O Código de barras já foi cadastrado.", "Código de Barras já cadastro.", AlertType.WARNING).Show();
			return;
		}
		
		if (!ValidarFormulario())
			return;
		
		if (_produtoService.InserirProduto(CriarProdutoAtravesDeFormulario())){
			new DialogMessage("Produto inserido com sucesso.", "Sucesso na inserção", AlertType.INFORMATION).Show();
			LimparFormulario();
			DefinirPesquisaVisualizacao();
		}
		else
			new DialogMessage("Falha na inserção do produto.", "Falha na inserção", AlertType.WARNING).Show();
	}

	@FXML
	void CancelarOperacaoEvent(ActionEvent event) {
		DefinirPadraoVisualizacao();
	}

	@FXML
	void NovoProdutoEvent(ActionEvent event) {
		LimparFormulario();
		DefinirNovoUsuarioVisualizacao();
	}

	@FXML
	void PesquisarFornecedorEvent(ActionEvent event) {
		AdicionarFornecedorNoGridFornecedor(
				_pessoaService.PesquisarFornecedoresPorDocumentoOuNome(txtPesquisarFornecedor.getText()));
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
		DefinirPesquisaVisualizacao();
		this.produto = produto;
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

		if (reposta.get() == ButtonType.OK) {
			if (_produtoService.RemoverProduto(Integer.valueOf(txtCodigoProduto.getText()))) {

				new DialogMessage("Produto removido com sucesso.", "Produto removido com sucesso", AlertType.INFORMATION).Show();
				LimparFormulario();
				DefinirPadraoVisualizacao();
			} 
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
		grdFornecedor.getItems().clear();
		grdFornecedor.getItems().add(produto.getFornecedor().ConverterToFornecedorViewModel());
	}

	public void AdicionarFornecedorNoGridFornecedor(List<Pessoa> fornecedores) {
		List<FornecedorViewModel> fornecedorViewModel = new ArrayList<FornecedorViewModel>();
		for (Pessoa fornecedor : fornecedores) {
			fornecedorViewModel.add(fornecedor.ConverterToFornecedorViewModel());
		}
		grdFornecedor.setItems(FXCollections.observableArrayList(fornecedorViewModel));
	}

	public Produto CriarProdutoAtravesDeFormulario() {
		return new Produto(txtNome.getText(), txtCodigoBarras.getText(), Float.valueOf(txtPreco.getText()),
				_pessoaService.PesquisarPessoaPorId(Integer.valueOf(txtCodigoFornecedor.getText())), new Date(),
				new Date());
	}

	public Produto AtualizarProdutoAtravesDeFormulario(Produto produto) {
		produto.setCodigoBarras(txtCodigoBarras.getText());
		produto.setFornecedor(_pessoaService.PesquisarPessoaPorId(Integer.valueOf(txtCodigoFornecedor.getText())));
		produto.setNome(txtNome.getText());
		produto.setPreco(Float.valueOf(txtPreco.getText()));
		return produto;
	}

	public boolean ValidarFormulario() {
		try {

			if (BasicValidator.IsnullOrEmpty(txtCodigoFornecedor.getText()))
				throw new IllegalArgumentException("Selecione um fornecedor para este produto.");

			if (BasicValidator.IsnullOrEmpty(txtCodigoBarras.getText())
					|| BasicValidator.IsnullOrEmpty(txtNome.getText())
					|| BasicValidator.IsnullOrEmpty(txtPreco.getText()))
				throw new IllegalArgumentException("Um ou mais campos não foram preenchidos.");

			if (Float.valueOf(txtPreco.getText()) == 0F)
				throw new IllegalArgumentException("Valor do produto não pode ser 0.");
		
			return true;
		} catch (IllegalArgumentException e) {
			new DialogMessage("Campos inválidos no cadastro de produto", e.getMessage(), AlertType.WARNING).Show();
			return false;
		}
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

	public void LimparFormulario() {
		List<Control> controles = new ArrayList<Control>(List.of(txtCodigoProduto, txtCodigoFornecedor, txtNome,
				txtCodigoBarras, txtPreco, grdFornecedor, txtPesquisarFornecedor));

		controles.forEach(control -> {
			if (control instanceof ComboBox<?>)
				((ComboBox<?>) control).setValue(null);
			if (control instanceof TextField)
				((TextField) control).setText("");
			if (control instanceof TableView<?>)
				((TableView<?>) control).getItems().clear();
		});
	}

	public void DefinirPesquisaVisualizacao() {
		List<ControlViewState> controles = Arrays.asList(new ControlViewState(txtCodigoProduto, false),
				new ControlViewState(btnPesquisarProduto, false), new ControlViewState(txtNome, true),
				new ControlViewState(txtCodigoBarras, true), new ControlViewState(txtPreco, true),
				new ControlViewState(txtPesquisarFornecedor, true), new ControlViewState(btnPesquisarFornecedor, true),
				new ControlViewState(grdFornecedor, true), new ControlViewState(btnCancelarOperacao, true),
				new ControlViewState(btnNovoCadastro, false), new ControlViewState(btnRemoverProduto, true),
				new ControlViewState(btnAtualizarProduto, true), new ControlViewState(btnCadastrarProduto, false));
		DefineVisualizacaoTela(controles);
	}

	public void DefinirPadraoVisualizacao() {
		List<ControlViewState> controles = Arrays.asList(new ControlViewState(txtCodigoProduto, true),
				new ControlViewState(btnPesquisarProduto, true), new ControlViewState(txtNome, false),
				new ControlViewState(txtCodigoBarras, false), new ControlViewState(txtPreco, false),
				new ControlViewState(txtPesquisarFornecedor, false),
				new ControlViewState(btnPesquisarFornecedor, false), new ControlViewState(grdFornecedor, false),
				new ControlViewState(btnCancelarOperacao, false), new ControlViewState(btnNovoCadastro, true),
				new ControlViewState(btnRemoverProduto, false), new ControlViewState(btnAtualizarProduto, false),
				new ControlViewState(btnCadastrarProduto, false));
		LimparFormulario();
		DefineVisualizacaoTela(controles);
	}

	public void DefinirNovoUsuarioVisualizacao() {
		List<ControlViewState> controles = Arrays.asList(new ControlViewState(txtCodigoProduto, false),
				new ControlViewState(btnPesquisarProduto, false), new ControlViewState(txtNome, true),
				new ControlViewState(txtCodigoBarras, true), new ControlViewState(txtPreco, true),
				new ControlViewState(txtPesquisarFornecedor, true), new ControlViewState(btnPesquisarFornecedor, true),
				new ControlViewState(grdFornecedor, true), new ControlViewState(btnCancelarOperacao, true),
				new ControlViewState(btnNovoCadastro, false), new ControlViewState(btnRemoverProduto, false),
				new ControlViewState(btnAtualizarProduto, false), new ControlViewState(btnCadastrarProduto, true));
		DefineVisualizacaoTela(controles);
	}

	public void DefineVisualizacaoTela(List<ControlViewState> controles) {

		for (ControlViewState c : controles) {
			Control control = c.getControl();
			if (control instanceof ComboBox<?>)
				((ComboBox<?>) control).setDisable(!c.isEnabled());

			if (control instanceof TextField)
				((TextField) control).setDisable(!c.isEnabled());

			if (control instanceof TableView<?>)
				((TableView<?>) control).setDisable(!c.isEnabled());

			if (control instanceof Button)
				((Button) control).setDisable(!c.isEnabled());
		}
	}
	
	public void ConfigurarInputs() {
		InputValidator.SetNumericLimitInput(txtCodigoProduto, 11);
		InputValidator.SetNumericLimitInput(txtCodigoBarras, 20);
		InputValidator.SetFloatLimitInput(txtPreco,20);
		InputValidator.SetLimitInput(txtNome, 80);
		InputValidator.SetLimitInput(txtPesquisarFornecedor, 255);
	}

}
