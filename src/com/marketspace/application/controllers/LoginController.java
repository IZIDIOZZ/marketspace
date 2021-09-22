package com.marketspace.application.controllers;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.application.services.AuthService;
import com.marketspace.domain.entities.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
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
	public void LogarEvent(ActionEvent event) {
		Usuario usuario = _authService.Logar(txtUsuario.getText(), txtSenha.getText());
		DialogMessage dialog = new DialogMessage();
		
		if (usuario.getLogin() != null) {
			new DialogMessage("Login Realizado com Sucesso", "Sucesso","MarketSpace", AlertType.INFORMATION).Show();
		} else
			new DialogMessage("Login Realizado com Sucesso", "Sucesso","MarketSpace", AlertType.INFORMATION).Show();
	}
}
