package com.marketspace.application.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.marketspace.domain.entities.Pessoa;

import javafx.fxml.FXML;

public class TesteViewController  {

	  @FXML
	  public void initialize() throws Exception {   
		  Pessoa p1 = new Pessoa("sdasdasdasd", "Daniel@dasdasdasd.com");
			Pessoa p2 = new Pessoa("adasd", "Joaquim@asdasd.com");
			Pessoa p3 = new Pessoa("sdasa", "Pedro@asdasd.com");

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("contexto");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(p1);
			em.persist(p2);
			em.persist(p3);
			em.getTransaction().commit();

			System.out.println("Pronto");
	  }
}
