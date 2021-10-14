package com.marketspace.application.controllers;

import java.io.IOException;

import com.marketspace.application.helpers.Navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button btnCaixa;

    @FXML
    private Button btnPessoa;

    @FXML
    private Button btnUsuario;

    @FXML
    private Button btnProduto;

    @FXML
    private Button btnRelatorioGerenciais;

    @FXML
    private Button btnCentroPessoa;

    @FXML
    private Button btnCentroUsuario;

    @FXML
    private Button btnLogbtnCentroProduto;

    @FXML
    private Button btnRelatorioGerencial;

    @FXML
    private Button btnSair;
    
    @FXML
    private void IrParaTelaDeCadastroPessoaEvent(ActionEvent event) throws IOException {
    	new Navigation().NavigateTo(event, "/com/marketspace/application/views/CadastroPessoaView.fxml"); 
    }
}
