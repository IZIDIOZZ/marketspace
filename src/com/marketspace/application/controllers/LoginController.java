package com.marketspace.application.controllers;

import java.io.IOException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.helpers.Navigation;
import com.marketspace.application.services.AuthService;
import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnLogar;

	@FXML
	void Logar(ActionEvent event) {

	}

	AuthService _authService;

	public LoginController() {
		_authService = new AuthService();
	}

	@FXML
	public void initialize() throws Exception {
		
	}
	
	@FXML
	public void LogarEvent(ActionEvent event) throws IOException {
		
		Usuario usuario = _authService.Logar("123","123");
		if (usuario.getLogin() != null) {
			new Navigation().NavigateTo(event, "/com/marketspace/application/views/MenuView.fxml"); 
		} else
		  new DialogMessage("Usuário ou Senha estão incorretos", "Tente Novamente", AlertType.WARNING).Show();
	}
}
