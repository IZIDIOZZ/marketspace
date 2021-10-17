package com.marketspace.application.services;

import java.util.ArrayList;
import java.util.List;

import com.marketspace.data.mappings.NivelUsuario;
import com.marketspace.data.mappings.Usuario;
import com.marketspace.data.repositories.UsuarioRepository;

public class UsuarioService {
	UsuarioRepository _usuarioRepository;

	public UsuarioService() {
		_usuarioRepository = new UsuarioRepository();
	}

	public void InserirUsuario(Usuario usuario) {

	}

	public Usuario PesquisarUsuario(int usuarioId) {
		return _usuarioRepository.BuscarUsuarioPorId(usuarioId);
	}

	public void RemoverUsuario(int usuarioId) {
		_usuarioRepository.RemoverUsuario(usuarioId);
	}

	public void AtualizarUsuario(Usuario usuario) {
		_usuarioRepository.AtualizarUsuario(usuario);
	}

	public List<String> PesquisarTodosNiveisUsuario() {
		List<String> listaNiveis = new ArrayList<String>();
		for (NivelUsuario nivel : _usuarioRepository.BuscarTodosNiveisUsuario()) {
			listaNiveis.add(nivel.getNivel());
		}
		return listaNiveis;
	}
}
