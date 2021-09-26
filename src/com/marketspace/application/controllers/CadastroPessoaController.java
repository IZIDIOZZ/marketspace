package com.marketspace.application.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.marketspace.application.services.PessoaService;
import com.marketspace.domain.entities.Endereco;
import com.marketspace.domain.entities.Pessoa;
import com.marketspace.domain.viewModels.EnderecoViewModel;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastroPessoaController {

    @FXML
    private Button btnPrimeiroRegistro;

    @FXML
    private Button btnRegistroAnterior;

    @FXML
    private Button btnRegistroPosterior;

    @FXML
    private Button btnUltimoRegistro;

    @FXML
    private TextField txtCodigoPessoa;

    @FXML
    private TextField txtRazaoSocial;

    @FXML
    private TextField txtNomeFantasia;

    @FXML
    private ComboBox<?> cmbTipoPessoa;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private TableView<EnderecoViewModel> grdEndereco;

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
    private TableColumn<EnderecoViewModel, String> colEstado;

    @FXML
    private TextField txtCEP;

    @FXML
    private TextField txtLogradouro;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCidade;

    @FXML
    private ComboBox<?> cmbEstado;

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
    
    private PessoaService _pessoaService;
    
    public CadastroPessoaController(){
    	_pessoaService = new PessoaService();
    }
   
    @FXML
    public void initialize() {
    	ConfiguraTabelaEndereco();
    	PesquisarPessoa();
    }
 
    public void PesquisarPessoa() {
    	Pessoa p = _pessoaService.PesquisarPessoaPorId(1);
    	txtRazaoSocial.setText(p.getRazaoSocial());
    	txtNomeFantasia.setText(p.getNomeFantasia());
    	txtCPF.setText(p.getDocumento());
    	txtCNPJ.setText(p.getDocumento());
    	
    	List<EnderecoViewModel> Enderecos = new ArrayList<EnderecoViewModel>();
    	for(Endereco vm : p.getEnderecos())
    	{
    		EnderecoViewModel evm = new EnderecoViewModel(
    				vm.getId(),
    				vm.getCEP(),
    				vm.getLogradouro(),
    				vm.getCidade(),
    				vm.getBairro(),
    				vm.getNumero(),
    				vm.getDataCadastro(),
    				vm.getDataAtualizacao()
    				);
    		Enderecos.add(evm);
    	}
    	
    	//TabelaUsuarios.setItems(FXCollections.observableArrayList(_usuarioDAO.ObterTodosUsuarios()));
    	grdEndereco.setItems(FXCollections.observableArrayList(Enderecos));
    	
    	
    	System.out.println(p.getDocumento());
    }
    public void PreencherEndereco(String CEP, String logradouro, String bairro, String cidade, String numero) {
    	txtCEP.setText(CEP);
    	txtLogradouro.setText(logradouro);
    	txtBairro.setText(bairro);
    	txtCidade.setText(cidade);
    	txtNumero.setText(numero);
    }
    
    public void ConfiguraTabelaEndereco() {
		colBairro.setCellValueFactory(new PropertyValueFactory<>("Bairro"));
		colCEP.setCellValueFactory(new PropertyValueFactory<>("CEP"));
		colCidade.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
		colEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
		colLogradouro.setCellValueFactory(new PropertyValueFactory<>("Logradouro"));
		colNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
		
		grdEndereco.setRowFactory(rf -> {
			TableRow<EnderecoViewModel> row = new TableRow<EnderecoViewModel>();
			row.setOnMouseClicked(evento -> {
				EnderecoViewModel endereco = row.getItem();
				if (endereco != null) {
					PreencherEndereco(
							endereco.getCEP(),
							endereco.getLogradouro(),
							endereco.getBairro(),
							endereco.getCidade(),
							endereco.getNumero()
							);
				}
			});
			return row;
		});
	}
}
