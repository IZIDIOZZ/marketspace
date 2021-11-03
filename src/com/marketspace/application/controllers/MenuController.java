package com.marketspace.application.controllers;

import java.io.IOException;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.domain.enums.TipoRespostaBotaoEnum;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

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
    private Button btnRelatorioSintetico;
    
    @FXML
    private void SairEvent() {
		if (new DialogMessage("Deseja realmente sair?",
				"Sair do MarkerSpace.", AlertType.CONFIRMATION,
				TipoRespostaBotaoEnum.YesOrNo).Show().get() == ButtonType.OK) {
			Platform.exit();		
		}
    }
    @FXML
    private void IrParaTelaDeCadastroPessoaEvent(ActionEvent event) throws IOException {
    	new Navigation().NavigateTo(event, "/com/marketspace/application/views/CadastroPessoaView.fxml"); 
    }
    
    @FXML
    private void IrParaTelaDeCadastroUsuarioEvent(ActionEvent event) throws IOException {
    	new Navigation().NavigateTo(event, "/com/marketspace/application/views/CadastroUsuarioView.fxml"); 
    }
    
    @FXML
    private void IrParaTelaDeCadastroProdutoEvent(ActionEvent event) throws IOException {
    	new Navigation().NavigateTo(event, "/com/marketspace/application/views/CadastroProdutoView.fxml"); 
    }
    
    @FXML
    private void IrParaTelaDeCaixaEvent(ActionEvent event) throws IOException {
    	new Navigation().NavigateTo(event, "/com/marketspace/application/views/CaixaView.fxml"); 
    }
    
    @FXML
    private void IrParaTelaDeRelatorioAnalitico(ActionEvent event) throws IOException {
    	new Navigation().NavigateTo(event, "/com/marketspace/application/views/RelatorioAnaliticoView.fxml"); 
    }
    
    @FXML
    private void IrParaTelaDeRelatorioSintetico(ActionEvent event) throws IOException {
    	new Navigation().NavigateTo(event, "/com/marketspace/application/views/RelatorioSinteticoView.fxml"); 
    }
}
