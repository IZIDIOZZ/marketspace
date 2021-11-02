package com.marketspace.application.controllers;

import com.marketspace.application.helpers.Navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RelatorioAnaliticoController extends Navigation {

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dtpInicial;

    @FXML
    private DatePicker dtpFinal;

    @FXML
    private Button btnGerarRelatórioAnalítico;

    @FXML
    private Button btnNovoRelatorio;

    @FXML
    private TableView<?> grdRelatorioAnalitico;

    @FXML
    private TableColumn<?, ?> colCodigoVenda;

    @FXML
    private TableColumn<?, ?> colCodigoProduto;

    @FXML
    private TableColumn<?, ?> colQuantidade;

    @FXML
    private TableColumn<?, ?> colValorProduto;

    @FXML
    private TextField txtTotalComprasPeriodo;

    @FXML
    private TextField txtProdutoMaisVendido;

    @FXML
    private TextField txtMediaComprasPeriodo;

    @FXML
    void GerarRelatórioAnalíticoEvent(ActionEvent event) {

    }

    @FXML
    void NovoRelatorioEvent(ActionEvent event) {

    }
}
