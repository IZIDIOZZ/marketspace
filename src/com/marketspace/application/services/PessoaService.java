package com.marketspace.application.services;

import com.marketspace.data.repositories.PessoaRepository;
import com.marketspace.domain.entities.Pessoa;

public class PessoaService {
	PessoaRepository _pessoaRepository;

	public PessoaService() {
		_pessoaRepository = new PessoaRepository();
	}
	
	public Pessoa PesquisarPessoaPorId(int id) {
		return _pessoaRepository.BuscarPessoa(id);
	}
	
}
