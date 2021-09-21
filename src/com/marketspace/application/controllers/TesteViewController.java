package com.marketspace.application.controllers;

import java.util.Date;

import javax.persistence.EntityManager;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.domain.entities.Pessoa;
import com.marketspace.domain.entities.TipoPessoa;

import javafx.fxml.FXML;

public class TesteViewController {

	EntityManager _dbmanager;

	public TesteViewController() {
		_dbmanager = new DbContextProvider().getEntityManager();
	}
	 
	@FXML
	public void initialize() throws Exception {

		    TipoPessoa tipoPessoa = new TipoPessoa();
		    tipoPessoa = _dbmanager.find(TipoPessoa.class, 1);
		    
			_dbmanager.getTransaction().begin();
			Pessoa daniel = new Pessoa("Daniel Izidio Lima", "Daniel", "49869180876", new Date(), new Date(),
					tipoPessoa);
			
			_dbmanager.persist(daniel);
			_dbmanager.getTransaction().commit();
			System.out.println("Pronto");
	}
}
