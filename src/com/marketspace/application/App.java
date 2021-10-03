package com.marketspace.application;

import com.marketspace.domain.enums.ImageEnum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App  extends Application {

	  public void start(Stage primaryStage) throws Exception{
	    	
	        //Carrega a view inicial do Menu num Node Pai e define as configurações de tamanho, titulo e icone.
	    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/marketspace/application/views/CadastroPessoaView.fxml"));
	    	Parent root = loader.load();
	    	primaryStage.setTitle("MarketSpace");
	    	primaryStage.setMinWidth(800);
	        primaryStage.setMinHeight(500);
	        primaryStage.setScene(new Scene(root, 1028, 682));
	        primaryStage.setResizable(true);
	        //primaryStage.initStyle(StageStyle.UNIFIED);
	        primaryStage.getIcons().add(new Image(ImageEnum.icon_logo_app.getImage()));
	        //Exibe a View
	        primaryStage.show();
	    }	
	     
	    public static void main(String[] args) {
	    	//método responsável por iniciar o Jogo
	        launch(args);
	    }
}
