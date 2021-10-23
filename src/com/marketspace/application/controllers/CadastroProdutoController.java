package com.marketspace.application.controllers;

import com.marketspace.application.helpers.Navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<?> grdFornecedor;

    @FXML
    private TableColumn<?, ?> colCodigoFornecedor;

    @FXML
    private TableColumn<?, ?> colRazaoSocial;

    @FXML
    private TableColumn<?, ?> colNomeFantasia;

    @FXML
    private TableColumn<?, ?> colJaTemVinculo;

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

    }

    @FXML
    void RemoverProdutoEvent(ActionEvent event) {

    }
}
