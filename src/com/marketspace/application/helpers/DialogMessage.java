package com.marketspace.application.helpers;

import java.util.List;
import java.util.Optional;

import com.marketspace.domain.enums.ImageEnum;
import com.marketspace.domain.enums.MarketSpaceEnum;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DialogMessage {
	
	private String mensagem;
	private String titulo;
	private String tituloMensagem;
	private Alert dialog;
	public DialogMessage() {}
	
	public DialogMessage(String mensagem, String titulo, AlertType tipoDoAlerta) {
		super();
		this.mensagem = mensagem;
		this.titulo = titulo;
		this.tituloMensagem = MarketSpaceEnum.nome.getNome();
		this.dialog = new Alert(tipoDoAlerta);
	}
	
	public Optional<ButtonType> Show(){
		return ConfigureDialogVisualization();
	}
	
	public Optional<ButtonType> Show(String mensagem, String titulo,AlertType tipoDoAlerta ){
		
		return ConfigureDialogVisualization(dialog,mensagem,titulo);
	}

	public Optional<ButtonType> Show(String mensagem, String titulo, AlertType tipoDoAlerta, List<ButtonType> buttons){
		
		if (buttons != null) dialog.getButtonTypes().setAll(buttons);
		return ConfigureDialogVisualization(dialog,mensagem,titulo);
	}
	
	private static Optional<ButtonType> ConfigureDialogVisualization(Alert dialog,String mensagem, String titulo) {
		dialog.setContentText(mensagem);
		dialog.setHeaderText(titulo);
		dialog.setTitle(MarketSpaceEnum.nome.getNome());
		return dialog.showAndWait();
	}
	
	private Optional<ButtonType> ConfigureDialogVisualization() {
		dialog.setContentText(mensagem);
		dialog.setHeaderText(titulo);
		dialog.setTitle(tituloMensagem);
		SetDialogIcon(dialog);
		return dialog.showAndWait();
	}
	
	private void SetDialogIcon(Alert dialog) {
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			  stage.getIcons().add(new Image(this.getClass().getResource(ImageEnum.icon_logo_app.getImage()).toString()));
	}
	
}	
