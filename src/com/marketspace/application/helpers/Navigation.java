package com.marketspace.application.helpers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {
	
	public void NavigateTo(ActionEvent event, String caminhoTela) throws IOException{
		Scene cena = new Scene(FXMLLoader.load(getClass().getResource(caminhoTela)));
		Stage currentStage= (Stage)((Node) event.getSource()).getScene().getWindow();
		currentStage.setMinWidth(1000);
		currentStage.setMinHeight(1000);
		currentStage.setScene(cena);
		currentStage.show();
	}
	
}
