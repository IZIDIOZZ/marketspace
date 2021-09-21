package com.marketspace.data.configurations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbContextProvider {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public DbContextProvider() {
		entityManagerFactory = Persistence.createEntityManagerFactory("contexto");
		entityManager = entityManagerFactory.createEntityManager();
	}

}
