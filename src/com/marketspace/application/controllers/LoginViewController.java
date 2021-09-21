package com.marketspace.application.controllers;
import com.marketspace.application.services.AuthService;
import com.marketspace.domain.entities.Usuario;

import javafx.fxml.FXML;

public class LoginViewController {

		AuthService _authService;
		
		public LoginViewController() {
			_authService = new AuthService();
		}
		 
		@FXML
		public void initialize() throws Exception {
			Usuario usuario = _authService.Logar("daniel.teste", "123");
			
			if(usuario.getLogin() != null)
				System.out.println("Logado");
			else
				System.out.println("Usuario ou senha errado");
			
		}

}
