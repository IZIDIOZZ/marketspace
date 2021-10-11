package com.marketspace.application.services;

import java.util.ArrayList;
import java.util.List;

import com.marketspace.application.helpers.DialogMessage;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.TipoPessoa;
import com.marketspace.data.repositories.PessoaRepository;

import javafx.scene.control.Alert.AlertType;

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
		boolean cadastro = false;
		if(pessoa.getEnderecos() == null) new DialogMessage("Insira no mínimo um Endereco", "Endereço requerido", AlertType.WARNING).Show(); 
		
		if(!_pessoaRepository.InserirPessoa(pessoa)) 
			new DialogMessage("Ocorreu um erro ao cadastrar a pessoa.", "Falha ao cadastrar", AlertType.WARNING).Show();
		else cadastro = true;
		
		return cadastro;
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
