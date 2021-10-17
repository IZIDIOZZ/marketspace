package com.marketspace.application.controllers;

import java.util.ArrayList;
import java.util.List;

import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.PessoaService;
import com.marketspace.application.services.UsuarioService;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.domain.viewModels.PessoaViewModel;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CadastroUsuarioController extends Navigation {

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
    private TextField txtSenha;

    @FXML
    private TextField txtConfirmarSenha;

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
    private TableColumn<PessoaViewModel,String> colTipoPessoa;

    @FXML
    private TableColumn<PessoaViewModel, Boolean> colJaTemVinculo;

    private UsuarioService _usuarioService;
    
    private PessoaService _pessoaService;
    
    public CadastroUsuarioController() {
    	_usuarioService = new UsuarioService();
    	_pessoaService = new PessoaService();
	}
    
    @FXML
    void initialize() {
    	ConfiguraFormulario();
    }
    
    @FXML
    void AtualizarUsuarioEvent(ActionEvent event) {

    }

    @FXML
    void CadastrarNovoUsuarioEvent(ActionEvent event) {

    }

    @FXML
    void CadastrarUsuarioEvent(ActionEvent event) {

    }

    @FXML
    void CancelarOperacaoEvent(ActionEvent event) {

    }
    
    @FXML
    void PesquisarUsuarioPorIdEnvent(ActionEvent event){

    }
    
    @FXML
    void PesquisarPessoaEvent(ActionEvent event) {
    	AdicionarPessoaNoGridPessoa(_pessoaService.PesquisarPessoasPorDocumentoOuNome(txtPesquisarPessoa.getText()));
    }

    @FXML
    void RemoverUsuarioEvent(ActionEvent event) {

    }
    
    public void AdicionarPessoaNoGridPessoa(List<Pessoa> pessoas) {
    	List<PessoaViewModel> pessoaViewModel = new ArrayList<PessoaViewModel>();
    	for(Pessoa pessoa : pessoas) {
    		pessoaViewModel.add(pessoa.ConverterTo());
    	}
    	grdPessoa.setItems(FXCollections.observableArrayList(pessoaViewModel));
    }
    
    public void ConfiguraFormulario() {
    	SetCellsFactoryPessoa();
    	PreencheComboTipoDeUsuario();
    }
    
    public void PreencheComboTipoDeUsuario() {
    	cboTipoUsuario.getItems().addAll(_usuarioService.PesquisarTodosNiveisUsuario());
    }
     
    public void SetCellsFactoryPessoa() {
    	
		colCodigoPessoa.setCellValueFactory(new PropertyValueFactory<>("Id"));
		colDocumento.setCellValueFactory(new PropertyValueFactory<>("Documento"));
		colRazaoSocial.setCellValueFactory(new PropertyValueFactory<>("RazaoSocial"));
		colNomeFantasia.setCellValueFactory(new PropertyValueFactory<>("NomeFantasia"));
		colTipoPessoa.setCellValueFactory(new PropertyValueFactory<>("TipoPessoa"));
		colJaTemVinculo.setCellValueFactory(new PropertyValueFactory<>("JaEstahVinculadoComUsuario"));
    }
}
