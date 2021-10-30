package com.marketspace.application.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.PessoaService;
import com.marketspace.application.services.UsuarioService;
import com.marketspace.data.mappings.NivelUsuario;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.Usuario;
import com.marketspace.domain.entities.ControlViewState;
import com.marketspace.domain.enums.TipoRespostaBotaoEnum;
import com.marketspace.domain.validators.BasicValidator;
import com.marketspace.domain.validators.InputValidator;
import com.marketspace.domain.viewModels.PessoaViewModel;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CadastroUsuarioController extends Navigation{

	@FXML
	private AnchorPane MainForm;

	@FXML
	private Button btnVoltar;

	@FXML
	private Pane pnlPesquisaPessoa;

	@FXML
	private TextField txtCodigoUsuario;

	@FXML
	private Button btnPesquisarUsuarioPorId;

	@FXML
	private TextField txtCodigoPessoa;

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private PasswordField txtConfirmarSenha;

	@FXML
	private ComboBox<String> cboTipoUsuario;

	@FXML
	private Pane pnlPesquisaPessoa1;

	@FXML
	private TextField txtPesquisarPessoa;

	@FXML
	private Button btnPesquisarPessoa;

	@FXML
	private Button btnCancelarOperacao;

	@FXML
	private Button btnNovoCadastro;

	@FXML
	private Button btnRemoverPessoa;

	@FXML
	private Button btnAtualizarPessoa;

	@FXML
	private Button btnCadastrarPessoa;

	@FXML
	private TableView<PessoaViewModel> grdPessoa;

	@FXML
	private TableColumn<PessoaViewModel, Integer> colCodigoPessoa;

	@FXML
	private TableColumn<PessoaViewModel, String> colDocumento;

	@FXML
	private TableColumn<PessoaViewModel, String> colRazaoSocial;

	@FXML
	private TableColumn<PessoaViewModel, String> colNomeFantasia;

	@FXML
	private TableColumn<PessoaViewModel, String> colTipoPessoa;

	@FXML
	private TableColumn<PessoaViewModel, Boolean> colJaTemVinculo;

	private UsuarioService _usuarioService;

	private PessoaService _pessoaService;

	private Usuario usuario = new Usuario();

	public CadastroUsuarioController() {
		_usuarioService = new UsuarioService();
		_pessoaService = new PessoaService();
	}

	@FXML
	void initialize() {
		ConfiguraFormulario();
		ConfiguraInputs();
		DefinirPadraoVisualizacao();
	}

	@FXML
	void AtualizarUsuarioEvent(ActionEvent event) {
		if (!ValidarFormulario())
			return;

		if (_usuarioService.AtualizarUsuario(AtualizarUsuarioAtravesDoFormulario(usuario)))
			new DialogMessage("Usuario Atualizado com sucesso", "Sucesso ao Atualizar", AlertType.INFORMATION).Show();
		else
			new DialogMessage("Ocorreu um erro ao atualizar o usuário.", "Falha ao Atualizar", AlertType.ERROR).Show();
		DefinirPadraoVisualizacao();
	}

	@FXML
	void NovoUsuarioEvent(ActionEvent event) {
		LimparFormulario();
		DefinirNovoUsuarioVisualizacao();
	}

	@FXML
	void CadastrarUsuarioEvent(ActionEvent event) {
		if (!ValidarFormulario())
			return;
		if (_usuarioService.InserirUsuario(CriarUsuarioAtravesDoFormulario()))
			new DialogMessage("Usuário inserido com sucesso.", "Sucesso na inserção", AlertType.INFORMATION).Show();
		else
			new DialogMessage("Falha na inserção do usuário.", "Falha na inserção", AlertType.WARNING).Show();

		DefinirPadraoVisualizacao();
	}

	@FXML
	void CancelarOperacaoEvent(ActionEvent event) {
		DefinirPadraoVisualizacao();
	}

	@FXML
	void PesquisarUsuarioPorIdEnvent(ActionEvent event) {
		if (BasicValidator.IsnullOrEmpty(txtCodigoUsuario.getText())) {
			new DialogMessage("Insira um Id para pesquisar um usuário.", "Campo de código vazio.", AlertType.WARNING)
					.Show();
			return;
		}

		Usuario usuario = _usuarioService.PesquisarUsuario(Integer.parseInt(txtCodigoUsuario.getText()));
		if (usuario == null) {
			new DialogMessage("Nenhum usuário com id " + txtCodigoUsuario.getText() + " foi encontrado.",
					"Usuário não encontrado", AlertType.WARNING).Show();
			return;
		}
		DefinirPesquisaVisualizacao();
		PreencherUsuario(usuario);
		this.usuario = usuario;
	}

	@FXML
	void PesquisarPessoaEvent(ActionEvent event) {
		AdicionarPessoaNoGridPessoa(_pessoaService.PesquisarPessoasPorDocumentoOuNome(txtPesquisarPessoa.getText()));
	}

	@FXML
	void RemoverUsuarioEvent(ActionEvent event) {
		if (BasicValidator.IsnullOrEmpty(txtCodigoUsuario.getText())) {
			new DialogMessage("Pesquise o usuário que deseja para remover.", "Nenhum usuário selecionado para remoção.",
					AlertType.WARNING).Show();
			return;
		}

		Optional<ButtonType> reposta = new DialogMessage("Deseja realmente remover este usuário?",
				"Ao Aceitar, você estará removendo permanentemente este usuário.", AlertType.CONFIRMATION,
				TipoRespostaBotaoEnum.YesOrNo).Show();

		if (reposta.get() == ButtonType.OK) {
			if (_usuarioService.RemoverUsuario(Integer.parseInt(txtCodigoUsuario.getText()))) {
				new DialogMessage("Usuário removido com sucesso", "Todos os dados deste usuário foram removidos.",
						AlertType.INFORMATION).Show();
			} else {
				new DialogMessage("Ocorreu um erro ao remover este usuário.", "Não foi possível remover o usuário",
						AlertType.WARNING).Show();
			}
		}
		DefinirPadraoVisualizacao();
	}

	public Usuario CriarUsuarioAtravesDoFormulario() {
		return new Usuario(txtUsuario.getText(), txtSenha.getText(), new Date(), new Date(),
				RetornarPessoaSelecionadaNoGrid(), RetornarNivelUsuarioSelecionado());
	}

	private Usuario AtualizarUsuarioAtravesDoFormulario(Usuario usuario) {
		usuario.setPessoa(RetornarPessoaSelecionadaNoGrid());
		usuario.setLogin(txtUsuario.getText());
		usuario.setNivelUsuario(RetornarNivelUsuarioSelecionado());
		usuario.setSenha(txtConfirmarSenha.getText());
		return usuario;
	}

	public void AdicionarPessoaNoGridPessoa(List<Pessoa> pessoas) {
		List<PessoaViewModel> pessoaViewModel = new ArrayList<PessoaViewModel>();
		for (Pessoa pessoa : pessoas) {
			pessoaViewModel.add(pessoa.ConverterTo());
		}
		grdPessoa.setItems(FXCollections.observableArrayList(pessoaViewModel));
	}

	public void ConfiguraFormulario() {

		ConfigurarTabelaPessoa();
		PreencheComboTipoDeUsuario();
	}

	public void PreencheComboTipoDeUsuario() {
		cboTipoUsuario.getItems().addAll(_usuarioService.PesquisarTodosNiveisUsuario());
	}

	public NivelUsuario RetornarNivelUsuarioSelecionado() {
		return _usuarioService.PesquisarNivelUsuario(cboTipoUsuario.getSelectionModel().getSelectedItem().toString());
	}

	public Pessoa RetornarPessoaSelecionadaNoGrid() {
		return _pessoaService.PesquisarPessoaPorId(Integer.parseInt(txtCodigoPessoa.getText()));
	}

	public void PreencherUsuario(Usuario usuario) {
		txtCodigoPessoa.setText(Integer.toString(usuario.getPessoa().getId()));
		txtCodigoUsuario.setText(Integer.toString(usuario.getId()));
		txtUsuario.setText(usuario.getLogin());
		txtSenha.setText(usuario.getSenha());
		txtConfirmarSenha.setText(usuario.getSenha());
		cboTipoUsuario.getSelectionModel().select(usuario.getNivelUsuario().getNivel());
		grdPessoa.getItems().add(usuario.getPessoa().ConverterTo());
	}

	public void ConfigurarTabelaPessoa() {
		colCodigoPessoa.setCellValueFactory(new PropertyValueFactory<>("Id"));
		colDocumento.setCellValueFactory(new PropertyValueFactory<>("Documento"));
		colRazaoSocial.setCellValueFactory(new PropertyValueFactory<>("RazaoSocial"));
		colNomeFantasia.setCellValueFactory(new PropertyValueFactory<>("NomeFantasia"));
		colTipoPessoa.setCellValueFactory(new PropertyValueFactory<>("TipoPessoa"));
		colJaTemVinculo.setCellValueFactory(new PropertyValueFactory<>("JaEstahVinculadoComUsuario"));

		grdPessoa.setRowFactory(rf -> {
			TableRow<PessoaViewModel> row = new TableRow<PessoaViewModel>();
			row.setOnMouseClicked(evento -> {
				PessoaViewModel pessoa = row.getItem();
				if (pessoa != null)
					txtCodigoPessoa.setText(Integer.toString(pessoa.getId()));
			});
			return row;
		});
	}

	public boolean ValidarFormulario() {
		try {
			if (!txtSenha.getText().equals(txtConfirmarSenha.getText()))
				throw new IllegalArgumentException("As Senhas não coincidem.");

			if (BasicValidator.IsnullOrEmpty(txtCodigoPessoa.getText()))
				throw new IllegalArgumentException("Selecione uma pessoa para este usuário.");

			if (cboTipoUsuario.getSelectionModel().getSelectedItem() == null)
				throw new IllegalArgumentException("Insira um nivel de usuário.");

			if (BasicValidator.IsnullOrEmpty(txtSenha.getText()) 
					|| BasicValidator.IsnullOrEmpty(txtConfirmarSenha.getText())
					|| BasicValidator.IsnullOrEmpty(txtUsuario.getText()))
				throw new IllegalArgumentException("Um ou mais campos não foram preenchidos");

			return true;
		} catch (IllegalArgumentException e) {
			new DialogMessage("Campos inválidos no Cadastro", e.getMessage(), AlertType.WARNING).Show();
			return false;
		}
	}

	public void DefinirPesquisaVisualizacao() {
		List<ControlViewState> controles = Arrays.asList(new ControlViewState(txtCodigoUsuario, false),
				new ControlViewState(btnPesquisarUsuarioPorId, false),
				new ControlViewState(txtUsuario, true),
				new ControlViewState(txtSenha, true), 
				new ControlViewState(txtConfirmarSenha, true),
				new ControlViewState(cboTipoUsuario, true),
				new ControlViewState(grdPessoa, true),
				new ControlViewState(txtPesquisarPessoa, true),
				new ControlViewState(btnPesquisarPessoa, true),
				new ControlViewState(btnCadastrarPessoa, false),
				new ControlViewState(btnAtualizarPessoa, true),
				new ControlViewState(btnRemoverPessoa, true), 
				new ControlViewState(btnNovoCadastro, false),
				new ControlViewState(btnCancelarOperacao, true));
		DefineVisualizacaoTela(controles);
	}

	public void DefinirPadraoVisualizacao() {
		List<ControlViewState> controles = Arrays.asList(
				new ControlViewState(txtCodigoUsuario, true),
				new ControlViewState(btnPesquisarUsuarioPorId, true),
				new ControlViewState(txtUsuario, false),
				new ControlViewState(txtSenha, false), 
				new ControlViewState(txtConfirmarSenha, false),
				new ControlViewState(cboTipoUsuario, false), 
				new ControlViewState(grdPessoa, false),
				new ControlViewState(txtPesquisarPessoa, false), 
				new ControlViewState(btnPesquisarPessoa, false),
				new ControlViewState(btnCadastrarPessoa, false), 
				new ControlViewState(btnAtualizarPessoa, false),
				new ControlViewState(btnRemoverPessoa, false), 
				new ControlViewState(btnNovoCadastro, true),
				new ControlViewState(btnCancelarOperacao, false));
		LimparFormulario();
		DefineVisualizacaoTela(controles);
	}

	public void DefinirNovoUsuarioVisualizacao() {
		List<ControlViewState> controles = Arrays.asList(
				new ControlViewState(txtCodigoUsuario, false),
				new ControlViewState(btnPesquisarUsuarioPorId, false),
				new ControlViewState(txtUsuario, true),
				new ControlViewState(txtSenha, true), 
				new ControlViewState(txtConfirmarSenha, true),
				new ControlViewState(cboTipoUsuario, true),
				new ControlViewState(grdPessoa, true),
				new ControlViewState(txtPesquisarPessoa, true),
				new ControlViewState(btnPesquisarPessoa, true),
				new ControlViewState(btnCadastrarPessoa, true),
				new ControlViewState(btnAtualizarPessoa, false),
				new ControlViewState(btnRemoverPessoa, false), 
				new ControlViewState(btnNovoCadastro, false),
				new ControlViewState(btnCancelarOperacao, true));
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
	
	public void LimparFormulario() {
			List<Control> controles = new ArrayList<Control>(
					List.of(txtCodigoUsuario,
							txtUsuario,
							txtSenha,
							txtConfirmarSenha,
							cboTipoUsuario,
							grdPessoa,
							txtPesquisarPessoa,
							txtCodigoPessoa));

			controles.forEach(control -> {
				if (control instanceof ComboBox<?>)
					((ComboBox<?>) control).setValue(null);
				if (control instanceof TextField)
					((TextField) control).setText("");
				if (control instanceof TableView<?>)
					((TableView<?>) control).getItems().clear();
			});
	}
	
	public void ConfiguraInputs() {
		InputValidator.SetNumericLimitInput(txtCodigoUsuario, 20);
	}

}
