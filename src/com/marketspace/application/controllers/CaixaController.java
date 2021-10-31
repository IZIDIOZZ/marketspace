package com.marketspace.application.controllers;

import com.marketspace.application.helpers.Navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<?> grdProduto;

    @FXML
    private TableColumn<?, ?> colCodigoProduto;

    @FXML
    private TableColumn<?, ?> colNomeProduto;

    @FXML
    private TableColumn<?, ?> colPrecoProduto;

    @FXML
    private TableColumn<?, ?> colQuantidadeProduto;

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
    void AtualizarProdutoEvent(ActionEvent event) {

    }

    @FXML
    void PesquisarFornecedorEvent(ActionEvent event) {

    }
}
