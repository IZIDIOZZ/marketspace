package com.marketspace.application.services;

import com.marketspace.data.mappings.Usuario;
import com.marketspace.data.repositories.AuthRepository;

public class AuthService {
	AuthRepository _authRepository;

	public AuthService() {
		_authRepository = new AuthRepository();
	}
	
	public Usuario Logar(String login, String senha){
		
		Usuario usuario = _authRepository.BuscarUsuario(login, senha);		
		return usuario;
	}
}
