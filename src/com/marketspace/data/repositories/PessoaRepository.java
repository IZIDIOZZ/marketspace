package com.marketspace.data.repositories;

import javax.persistence.EntityManager;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.domain.entities.Pessoa;

public class PessoaRepository {
	EntityManager _dbmanager;

	public PessoaRepository() {
		_dbmanager = new DbContextProvider().getEntityManager();
	}
	
	public Pessoa BuscarPessoa(int id) {
		Pessoa pessoa;
		_dbmanager.getTransaction().begin();
		pessoa = _dbmanager.find(Pessoa.class,id);
		_dbmanager.getTransaction().commit();
		return pessoa;
	}
}
