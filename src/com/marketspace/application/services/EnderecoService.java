package com.marketspace.application.services;

import java.util.ArrayList;
import java.util.List;

import com.marketspace.data.mappings.Endereco;
import com.marketspace.data.mappings.Estado;
import com.marketspace.data.repositories.EnderecoRepository;

public class EnderecoService {
	EnderecoRepository _enderecoRepository;

	public EnderecoService() {
		_enderecoRepository = new EnderecoRepository();
	}

	public List<String> BuscarEstados() {
		List<String> Estados = new ArrayList<String>();
		_enderecoRepository.ObterEstados().forEach(x->Estados.add(x.getNome()));
		return Estados;
	}
	
	public Estado PesquisarEstadoPorNome(String nomeEstado) {
		return _enderecoRepository.ObterEstadoPorNome(nomeEstado);
	}
	
	public boolean AlterarEndereco(Endereco endereco) {
	    	return _enderecoRepository.AtualizarEndereco(endereco);
	}
	
	public boolean RemoverEndereco(int enderecoId) {
    	return _enderecoRepository.RemoverEndereco(enderecoId);
    }

}
