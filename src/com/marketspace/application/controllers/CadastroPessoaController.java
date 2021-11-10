package com.marketspace.application.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.EnderecoService;
import com.marketspace.application.services.PessoaService;
import com.marketspace.data.mappings.Endereco;
import com.marketspace.data.mappings.Estado;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.TipoPessoa;
import com.marketspace.domain.enums.TipoPessoaEnum;
import com.marketspace.domain.enums.TipoRespostaBotaoEnum;
import com.marketspace.domain.validators.CNPJValidator;
import com.marketspace.domain.validators.CPFValidator;
import com.marketspace.domain.validators.InputValidator;
import com.marketspace.domain.viewModels.EnderecoViewModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CadastroPessoaController extends Navigation {

	@FXML
	private AnchorPane MainForm;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField txtCodigoPessoa;

	@FXML
	private TextField txtRazaoSocial;

	@FXML
	private TextField txtNomeFantasia;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtCNPJ;

	@FXML
	private TableView<EnderecoViewModel> grdEndereco;

	@FXML
	private TableColumn<EnderecoViewModel, Integer> colId;

	@FXML
	private TableColumn<EnderecoViewModel, String> colCEP;

	@FXML
	private TableColumn<EnderecoViewModel, String> colLogradouro;

	@FXML
	private TableColumn<EnderecoViewModel, String> colNumero;

	@FXML
	private TableColumn<EnderecoViewModel, String> colBairro;

	@FXML
	private TableColumn<EnderecoViewModel, String> colCidade;

	@FXML
	private TableColumn<EnderecoViewModel, Estado> colEstado;

	@FXML
	private TableColumn<EnderecoViewModel, Date> colDataCadastro;

	@FXML
	private TableColumn<EnderecoViewModel, Date> colDataAtualizacao;

	@FXML
	private TextField txtIdEndereco;

	@FXML
	private TextField txtCEP;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCidade;

	@FXML
	private ComboBox<String> cmbEstado;

	@FXML
	private ComboBox<String> cmbTipoPessoa;

	@FXML
	private Button btnNovoCadastro;

	@FXML
	private Button btnRemoverPessoa;

	@FXML
	private Button btnAtualizarPessoa;

	@FXML
	private Button btnCadastrarPessoa;

	@FXML
	private Button btnRemoverEndereco;

	@FXML
	private Button btnAlterarEndereco;

	@FXML
	private Button btnInserirEndereco;

	@FXML
	private Button btnPesquisarPessoaPorId;

	@FXML
	private Button btnCancelarOperacao;

	@FXML
	private Pane pnlPesquisaPessoa;

	@FXML
	private HBox hBoxBotoesEndereco;

	private PessoaService _pessoaService;

	private EnderecoService _enderecoService;

	private Pessoa _pessoa;

	public CadastroPessoaController() {
		_pessoaService = new PessoaService();
		_enderecoService = new EnderecoService();
		_pessoa = new Pessoa();
	}

	@FXML
	public void initialize() {
		ConfiguraTabelaEndereco();
		ConfiguraComboTipoPessoa();
		ConfiguraInputs();
		ObterValoresComboTipoPessoa();
		ObterValoresComboEstado();
		SetDefaultView();
	}

	@FXML
	void AdicionarEnderecoGridEnderecoEvent(ActionEvent event) {
		InserirEndereco();
	}

	@FXML
	void RemoverEnderecoGridEnderecoEvent(ActionEvent event) {
		
		if(grdEndereco.getItems().isEmpty()) {
			new DialogMessage("Não há enderecos para remover", "Pessoa sem endereço cadastrado.",
					AlertType.WARNING).Show();
			return;
		}
		
		RemoverEndereco();
	}

	@FXML
	void AlterarEnderecoGridEnderecoEvent(ActionEvent event) {
		AlterarEndereco();
	}

	@FXML
	void CadastrarPessoaEvent(ActionEvent event) {

		if (!DadosInseridosSaoValidos())
			return;

		MontarPessoaComEntradasDoFormulario();

		if (_pessoaService.CadastrarPessoa(_pessoa)) {
			new DialogMessage("Cadastro realizado com sucesso.", "Cadastro realizado com sucesso",
					AlertType.INFORMATION).Show();
			LimparFormulario();
		}
	}

	@FXML
	void AtualizarPessoaEvent(ActionEvent event) {
		if (!DadosInseridosSaoValidos())
			return;

		MontarPessoaComEntradasDoFormulario();
		SetEditView();
		if (_pessoaService.AtualizarPessoa(_pessoa)) {
			new DialogMessage("Pessoa Atualizada com sucesso.", "Pessoa Atualizada com sucesso", AlertType.INFORMATION)
					.Show();
			LimparFormulario();
		} else {
			new DialogMessage("Ocorreu um erro ao atualizar a pessoa.", "Não foi possível atualizar a pessoa",
					AlertType.WARNING).Show();
		}
	}

	@FXML
	void RemoverPessoaEvent(ActionEvent event) {

		Optional<ButtonType> reposta = new DialogMessage("Deseja realmente remover esta pessoa?",
				"Ao Aceitar, você estará removendo todos os registros relacionados com essa pessoa.",
				AlertType.CONFIRMATION, TipoRespostaBotaoEnum.YesOrNo).Show();

		if (reposta.get() == ButtonType.OK) {
			if (_pessoaService.RemoverPessoa(Integer.parseInt(txtCodigoPessoa.getText()))) {
				new DialogMessage("Pessoa removida com sucesso", "Todos os dados desta pessoa foram removidos",
						AlertType.INFORMATION).Show();
				LimparFormulario();
			} else {
				new DialogMessage("Ocorreu um erro ao remover esta pessoa.", "Não foi possível remover a pessoa",
						AlertType.WARNING).Show();
			}
		}
	}

	@FXML
	void CadastrarNovoUsuarioEvent(ActionEvent event) {
		LimparFormulario();
		SetInsertView();
	}

	@FXML
	void PesquisarPessoaPorIdEvent(ActionEvent event) {
		PesquisarPessoaPorId(Integer.parseInt(txtCodigoPessoa.getText()));
		
	}

	@FXML
	void CancelarOperacaoEvent(ActionEvent event) {
		LimparFormulario();
		SetDefaultView();
		if (!txtCodigoPessoa.getText().isEmpty())
			PesquisarPessoaPorId(Integer.parseInt(txtCodigoPessoa.getText()));
	}

	public void MudancaTipoDePessoaEvent() {
		Control controle = EhPessoaFisica() ? txtCNPJ : txtCPF;
		DesabilitarDocumento(EhPessoaFisica() ? TipoPessoaEnum.Fisica : TipoPessoaEnum.Juridica);
		((TextField) controle).setText("");
	}

	public void PesquisarPessoaPorId(int Id) {
		_pessoa = _pessoaService.PesquisarPessoaPorId(Id);
		if (_pessoa == null) {
			new DialogMessage("Usuário não encontrado",
					"Nenhum usuário com o id " + txtCodigoPessoa.getText() + " foi encontrado", AlertType.WARNING)
							.Show();
			return;
		}
		SetSearchView();
		PreencherFormularioPessoa(_pessoa);
		PreencherGridEnderecos(_pessoa.getEnderecos());
		MudancaTipoDePessoaEvent();
	}

	public void PreencherGridEnderecos(List<Endereco> enderecos) {
		List<EnderecoViewModel> enderecosViewModel = new ArrayList<EnderecoViewModel>();
		for (Endereco ende : enderecos) {
			enderecosViewModel.add(ende.ConverterTo());
		}
		grdEndereco.setItems(FXCollections.observableArrayList(enderecosViewModel));
	}

	public void PreencherFormularioEndereco(int id, String CEP, String logradouro, String bairro, String cidade,
			String numero, String estado) {

		txtIdEndereco.setText(String.valueOf(id));
		txtCEP.setText(CEP);
		txtEndereco.setText(logradouro);
		txtBairro.setText(bairro);
		txtCidade.setText(cidade);
		txtNumero.setText(numero);
		cmbEstado.getSelectionModel().select(estado);
	}

	public void PreencherFormularioPessoa(Pessoa pessoa) {

		txtRazaoSocial.setText(pessoa.getRazaoSocial());
		txtNomeFantasia.setText(pessoa.getNomeFantasia());
		txtCPF.setText(pessoa.getDocumento());
		txtCNPJ.setText(pessoa.getDocumento());
		txtCodigoPessoa.setText(String.valueOf(pessoa.getId()));
		cmbTipoPessoa.getSelectionModel().select(pessoa.getTipoPessoa().getTipoPessoa());
	}

	public void ConfiguraTabelaEndereco() {

		SetCellsFactoryEndereco();
		grdEndereco.setRowFactory(rf -> {
			TableRow<EnderecoViewModel> row = new TableRow<EnderecoViewModel>();
			row.setOnMouseClicked(evento -> {
				EnderecoViewModel endereco = row.getItem();
				if (endereco != null) {
					PreencherFormularioEndereco(endereco.getId(), endereco.getCEP(), endereco.getLogradouro(),
							endereco.getBairro(), endereco.getCidade(), endereco.getNumero(), endereco.getEstado());
				}
			});
			return row;
		});
	}

	public void ConfiguraComboTipoPessoa() {
		cmbTipoPessoa.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				MudancaTipoDePessoaEvent();
			}
		});
	}

	public void SetCellsFactoryEndereco() {

		colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		colBairro.setCellValueFactory(new PropertyValueFactory<>("Bairro"));
		colCEP.setCellValueFactory(new PropertyValueFactory<>("CEP"));
		colLogradouro.setCellValueFactory(new PropertyValueFactory<>("Logradouro"));
		colCidade.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
		colNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
		colDataCadastro.setCellValueFactory(new PropertyValueFactory<>("DataCadastro"));
		colDataAtualizacao.setCellValueFactory(new PropertyValueFactory<>("DataAtualizacao"));
		colEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
	}

	public void ObterValoresComboTipoPessoa() {
		cmbTipoPessoa.getItems().addAll(_pessoaService.PesquisarTodosTiposDePessoas());
	}

	public void ObterValoresComboEstado() {
		cmbEstado.getItems().addAll(_enderecoService.BuscarEstados());
	}

	public void InserirEndereco() {
		if(!ValidarEndereco()) return;
		
		Endereco endereco = new Endereco(txtCEP.getText(), txtEndereco.getText(), txtBairro.getText(),
				txtCidade.getText(), txtNumero.getText(), new Date(), new Date(), _pessoa,
				_enderecoService.PesquisarEstadoPorNome(cmbEstado.getSelectionModel().getSelectedItem().toString()));

		AdicionarEnderecoNaPessoa(endereco);

		EnderecoViewModel evm = endereco.ConverterTo();

		grdEndereco.getItems().add(evm);
	}

	public void AlterarEndereco() {
		if(!ValidarEndereco()) return;
		Endereco endereco = new Endereco(Integer.parseInt(txtIdEndereco.getText()), txtCEP.getText(),
				txtEndereco.getText(), txtBairro.getText(), txtCidade.getText(), txtNumero.getText(), new Date(),
				new Date(), _pessoa,
				_enderecoService.PesquisarEstadoPorNome(cmbEstado.getSelectionModel().getSelectedItem().toString()));

		AtualizarEnderecoNaPessoa(endereco);
		PreencherGridEnderecos(_pessoa.getEnderecos());
	}

	public void RemoverEndereco() {
		if(!ValidarEndereco()) return;
		RemoverEnderecoNaPessoa(Integer.parseInt(txtIdEndereco.getText()));
		PreencherGridEnderecos(_pessoa.getEnderecos());
	}

	public void AdicionarEnderecoNaPessoa(Endereco endereco) {
		if (_pessoa.getEnderecos() != null)
			_pessoa.getEnderecos().add(endereco);
		else {
			List<Endereco> enderecos = new ArrayList<Endereco>();
			enderecos.add(endereco);
			_pessoa.setEnderecos(enderecos);
		}
	}

	public void AtualizarEnderecoNaPessoa(Endereco enderecoAtualizado) {
		int index = 0;
		for (Endereco e : _pessoa.getEnderecos()) {
			if (e.getId() == enderecoAtualizado.getId())
				index = _pessoa.getEnderecos().indexOf(e);
		}
		_pessoa.getEnderecos().set(index, enderecoAtualizado);

	}

	public void RemoverEnderecoNaPessoa(int enderecoId) {
		int index = 0;
		for (Endereco e : _pessoa.getEnderecos()) {
			if (e.getId() == enderecoId)
				index = _pessoa.getEnderecos().indexOf(e);
		}
		_pessoa.getEnderecos().remove(index);
	}

	public String RetornaDocumentoPessoa() {
		return EhPessoaFisica() ? txtCPF.getText() : txtCNPJ.getText();
	}

	public boolean EhPessoaFisica() {
		if (cmbTipoPessoa.getSelectionModel().getSelectedItem() == null)
			return true;
		return cmbTipoPessoa.getSelectionModel().getSelectedItem().equals(TipoPessoaEnum.Fisica.getTipoPessoa());

	}

	public void MontarPessoaComEntradasDoFormulario() {
		TipoPessoa tipoPessoa = _pessoaService.RetornarTipoPessoa(cmbTipoPessoa.getSelectionModel().getSelectedItem());
		_pessoa.setRazaoSocial(txtRazaoSocial.getText());
		_pessoa.setNomeFantasia(txtNomeFantasia.getText());
		_pessoa.setDocumento(RetornaDocumentoPessoa());
		_pessoa.setDataCadastro(new Date());
		_pessoa.setDataAtualizacao(new Date());
		_pessoa.setTipoPessoa(tipoPessoa);
	}

	public void DesabilitarDocumento(TipoPessoaEnum tipo) {
		txtCNPJ.setDisable(tipo == TipoPessoaEnum.Fisica);
		txtCPF.setDisable(!(tipo == TipoPessoaEnum.Fisica));
	}

	public void LimparPessoa() {
		_pessoa = new Pessoa();
	}

	public void LimparFormulario() {
		List<Control> controles = new ArrayList<Control>(
				List.of(txtRazaoSocial, txtNomeFantasia, cmbTipoPessoa, txtCPF, txtCNPJ, txtCEP, txtEndereco, txtNumero,
						txtBairro, txtCidade, cmbEstado, grdEndereco, txtCodigoPessoa));

		LimparPessoa();
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
		List<TextField> TextInputs = List.of(txtCodigoPessoa, txtCEP);
		TextInputs.forEach(x -> InputValidator.SetNumericLimitInput(x, 8));
		InputValidator.SetNumericLimitInput(txtCPF, 11);
		InputValidator.SetNumericLimitInput(txtCNPJ, 14);
	}

	public void DesabilitarFormularioPessoa(boolean estado) {
		txtRazaoSocial.setDisable(estado);
		txtNomeFantasia.setDisable(estado);
		cmbTipoPessoa.setDisable(estado);
		txtCPF.setDisable(estado);
		txtCNPJ.setDisable(estado);
	}

	public void DesabilitarFormularioEndereco(boolean estado) {
		txtCEP.setDisable(estado);
		txtEndereco.setDisable(estado);
		txtNumero.setDisable(estado);
		txtBairro.setDisable(estado);
		txtCidade.setDisable(estado);
		cmbEstado.setDisable(estado);
		grdEndereco.setDisable(estado);
	}

	public boolean DadosInseridosSaoValidos() {
		String documento = EhPessoaFisica() ? "CPF" : "CNPJ";

		try {
			if (!CamposParaCadastroEstaoPrenchidos())
				throw new IllegalArgumentException("Um ou mais Campos não estão preenchidos");

			if (!(EhPessoaFisica() ? CPFValidator.isCPF(txtCPF.getText()) : CNPJValidator.isCNPJ(txtCNPJ.getText())))
				throw new IllegalArgumentException("Insira um " + documento + " válido");

			if (grdEndereco.getItems().size() == 0)
				throw new IllegalArgumentException("Insira no mínimo um Endereco");
			
			return true;
		} catch (IllegalArgumentException e) {
			new DialogMessage("Campos inválidos no Cadastro", e.getMessage(), AlertType.WARNING).Show();
			return false;
		}
	}
	
	public boolean ValidarEndereco() {
		try {
			if (!CamposParaCadastroDeEnderecoEstaoPrenchidos())
				throw new IllegalArgumentException("Um ou mais campos para o cadastro do endereço não estão preenchidos.");
			
			return true;
		} catch (IllegalArgumentException e) {
			new DialogMessage("Campos inválidos no cadastro do endereço", e.getMessage(), AlertType.WARNING).Show();
			return false;
		}
	}

	public boolean CamposParaCadastroEstaoPrenchidos() {
		List<Control> controles = new ArrayList<Control>(
				List.of(txtRazaoSocial, txtNomeFantasia, cmbTipoPessoa, EhPessoaFisica() ? txtCPF : txtCNPJ));
		boolean todosControlesPreenchidos = true;
		for (Control control : controles) {
			if (control instanceof ComboBox<?>)
				if (((ComboBox<?>) control).getSelectionModel().isEmpty())
					todosControlesPreenchidos = false;

			if (control instanceof TextField)
				if (((TextField) control).getText().isBlank() || ((TextField) control).getText().isEmpty())
					todosControlesPreenchidos = false;
		}
		return todosControlesPreenchidos;
	}
	
	public boolean CamposParaCadastroDeEnderecoEstaoPrenchidos() {
		List<Control> controles = new ArrayList<Control>(List.of(txtBairro, txtCEP, txtCidade, txtEndereco, txtNumero));
		boolean todosControlesPreenchidos = true;
		for (Control control : controles) {
			if (control instanceof ComboBox<?>)
				if (((ComboBox<?>) control).getSelectionModel().isEmpty())
					todosControlesPreenchidos = false;
			
			if (control instanceof TextField)
				if (((TextField) control).getText().isBlank() || ((TextField) control).getText().isEmpty())
					todosControlesPreenchidos = false;
		}
		return todosControlesPreenchidos;
	}

	public void SetInsertView() {
		cmbTipoPessoa.setDisable(false);
		btnCadastrarPessoa.setDisable(false);
		btnCancelarOperacao.setDisable(false);
		DesabilitarFormularioPessoa(false);
		DesabilitarFormularioEndereco(false);
		hBoxBotoesEndereco.setDisable(false);

		btnNovoCadastro.setDisable(true);
		pnlPesquisaPessoa.setDisable(true);
		btnRemoverPessoa.setDisable(true);
		btnAtualizarPessoa.setDisable(true);

		txtCodigoPessoa.setText("");
	}
	public void SetEditView() {
		cmbTipoPessoa.setDisable(true);
		btnNovoCadastro.setDisable(true);
		btnCadastrarPessoa.setDisable(true);
		txtCodigoPessoa.setDisable(true);
		pnlPesquisaPessoa.setDisable(true);
		DesabilitarFormularioPessoa(false);
		DesabilitarFormularioEndereco(false);
		hBoxBotoesEndereco.setDisable(false);

		btnRemoverPessoa.setDisable(false);
		btnAtualizarPessoa.setDisable(false);
		btnCancelarOperacao.setDisable(false);
	}

	public void SetSearchView() {
		cmbTipoPessoa.setDisable(true);
		btnNovoCadastro.setDisable(true);
		btnCadastrarPessoa.setDisable(true);
		pnlPesquisaPessoa.setDisable(true);
		DesabilitarFormularioPessoa(false);
		DesabilitarFormularioEndereco(false);
		hBoxBotoesEndereco.setDisable(false);

		btnRemoverPessoa.setDisable(false);
		btnAtualizarPessoa.setDisable(false);
		btnCancelarOperacao.setDisable(false);
	}

	public void SetDefaultView() {
		cmbTipoPessoa.setDisable(false);
		btnNovoCadastro.setDisable(false);
		btnCadastrarPessoa.setDisable(true);
		txtCodigoPessoa.setDisable(false);
		pnlPesquisaPessoa.setDisable(false);
		DesabilitarFormularioPessoa(true);
		DesabilitarFormularioEndereco(true);
		hBoxBotoesEndereco.setDisable(true);

		btnRemoverPessoa.setDisable(true);
		btnAtualizarPessoa.setDisable(true);
		btnCancelarOperacao.setDisable(true);
	}
}
