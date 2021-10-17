package com.marketspace.application.helpers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Navigation {
	
	public void NavigateTo(ActionEvent event, String caminhoTela) throws IOException{
		Scene cena = new Scene(FXMLLoader.load(getClass().getResource(caminhoTela)));
		Stage currentStage= (Stage)((Node) event.getSource()).getScene().getWindow();
		Rectangle2D bounds = Screen.getPrimary().getBounds();
		currentStage.setScene(cena);
		currentStage.setX(bounds.getMinX());
		currentStage.setY(bounds.getMinY());
		currentStage.setWidth(bounds.getWidth());
		currentStage.setHeight(bounds.getHeight());
		currentStage.show();
	}
	
	@FXML
	void VoltarAoMenu(ActionEvent event) throws IOException {
		new Navigation().NavigateTo(event, "/com/marketspace/application/views/MenuView.fxml");
	}
	
}
