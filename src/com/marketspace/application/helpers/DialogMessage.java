package com.marketspace.application.helpers;

import java.util.Optional;

import com.marketspace.domain.enums.ImageEnum;
import com.marketspace.domain.enums.MarketSpaceEnum;
import com.marketspace.domain.enums.TipoRespostaBotaoEnum;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DialogMessage {
	
	private String mensagem;
	private String titulo;
	private String tituloMensagem;
	private Alert dialog;
	private TipoRespostaBotaoEnum textoBotao; 
	public DialogMessage() {}
	
	public DialogMessage(String mensagem, String titulo, AlertType tipoDoAlerta) {
		super();
		this.mensagem = mensagem;
		this.titulo = titulo;
		this.tituloMensagem = MarketSpaceEnum.nome.getNome();
		this.dialog = new Alert(tipoDoAlerta);
		this.textoBotao = TipoRespostaBotaoEnum.Default;
	}
	
	public DialogMessage(String mensagem, String titulo, AlertType tipoDoAlerta,TipoRespostaBotaoEnum textoBotao) {
		super();
		this.mensagem = mensagem;
		this.titulo = titulo;
		this.tituloMensagem = MarketSpaceEnum.nome.getNome();
		this.dialog = new Alert(tipoDoAlerta);
		this.textoBotao = textoBotao;  
	}
	
	public Optional<ButtonType> Show(){
		return ConfigureDialogVisualization();
	}
	
	private Optional<ButtonType> ConfigureDialogVisualization() {
		dialog.setContentText(mensagem);
		dialog.setHeaderText(titulo);
		dialog.setTitle(tituloMensagem);
	    if(textoBotao == TipoRespostaBotaoEnum.YesOrNo) SetDialogSimOuNao(dialog);
		SetDialogIcon(dialog);
		return dialog.showAndWait();
	}
	
	private void SetDialogIcon(Alert dialog) {
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			  stage.getIcons().add(new Image(this.getClass().getResource(ImageEnum.icon_logo_app.getImage()).toString()));
	}
	
	private void SetDialogSimOuNao(Alert dialog) {
		((Button) dialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Sim");
		((Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Não");
	}
}	
