package com.marketspace.application.services;

import java.util.ArrayList;
import java.util.List;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.data.mappings.NivelUsuario;
import com.marketspace.data.mappings.Usuario;
import com.marketspace.data.repositories.UsuarioRepository;

import javafx.scene.control.Alert.AlertType;

public class UsuarioService {
	UsuarioRepository _usuarioRepository;

	public UsuarioService() {
		_usuarioRepository = new UsuarioRepository();
	}

	public boolean InserirUsuario(Usuario usuario) {
		try {
			_usuarioRepository.InserirUsuario(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Usuario PesquisarUsuario(int usuarioId) {
		return _usuarioRepository.BuscarUsuarioPorId(usuarioId);
	}

	public boolean RemoverUsuario(int usuarioId) {
		try {
			_usuarioRepository.RemoverUsuario(usuarioId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean AtualizarUsuario(Usuario usuario) {
		try {
			_usuarioRepository.AtualizarUsuario(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<String> PesquisarTodosNiveisUsuario() {
		List<String> listaNiveis = new ArrayList<String>();
		for (NivelUsuario nivel : _usuarioRepository.BuscarTodosNiveisUsuario()) {
			listaNiveis.add(nivel.getNivel());
		}
		return listaNiveis;
	}
	
	public NivelUsuario PesquisarNivelUsuario(String nivelUsuario) {
		return _usuarioRepository.BuscarNivelUsuario(nivelUsuario);
	}
}
