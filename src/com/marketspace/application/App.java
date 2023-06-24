package com.marketspace.application;

import com.marketspace.domain.enums.ImageEnum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	  public void start(Stage primaryStage) throws Exception{
	    	
	    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/marketspace/application/views/LoginView.fxml"));
	    	Parent root = loader.load();
	    	primaryStage.setTitle("MarketSpace");
	    	primaryStage.setMinWidth(1100);
	        primaryStage.setMinHeight(800);
	        primaryStage.setMaximized(true);
	        primaryStage.setScene(new Scene(root, 1028, 682));
	        primaryStage.setResizable(true);
	        primaryStage.getIcons().add(new Image(ImageEnum.icon_logo_app.getImage()));
	        primaryStage.show();
	    }	
	     
	    public static void main(String[] args) {
	        launch(args);
	    }
}
