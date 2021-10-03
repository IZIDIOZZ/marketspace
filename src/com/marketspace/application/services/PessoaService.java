package com.marketspace.application.services;

import java.util.ArrayList;
import java.util.List;

import com.marketspace.data.repositories.PessoaRepository;
import com.marketspace.domain.entities.Pessoa;
import com.marketspace.domain.entities.TipoPessoa;

public class PessoaService {
	PessoaRepository _pessoaRepository;

	public PessoaService() {
		_pessoaRepository = new PessoaRepository();
	}

	public Pessoa PesquisarPessoaPorId(int id) {
		return _pessoaRepository.BuscarPessoa(id);
	}

	public List<String> PesquisarTodosTiposDePessoas() {
		List<String> TiposPessoa = new ArrayList<String>();
		_pessoaRepository.ObterTiposDePessoa().forEach(x -> TiposPessoa.add(x.getTipoPessoa()));
		return TiposPessoa;
	}

	public boolean CadastrarPessoa(Pessoa pessoa) {
		_pessoaRepository.InserirPessoa(pessoa);
		return true;
	}

	public boolean AtualizarPessoa(Pessoa pessoa) {
		_pessoaRepository.AtualizarPessoa(pessoa);
		return true;
	}
	
	public boolean RemoverPessoa(int id) {
		_pessoaRepository.RemoverPessoa(id);
		return true;
	}


	public TipoPessoa RetornarTipoPessoa(String tipoPessoa) {
		return _pessoaRepository.BuscarTipoPessoa(tipoPessoa);
	}
}
