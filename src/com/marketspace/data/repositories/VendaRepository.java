package com.marketspace.data.repositories;

import javax.persistence.EntityManager;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.Venda;

public class VendaRepository {
	EntityManager _entityManager;

	public VendaRepository() {
		_entityManager = new DbContextProvider().getEntityManager();
	}
	
	public boolean InserirVenda(Venda venda) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.persist(venda);
			return true;
		}catch (Exception e) {
			return false;
		}finally {
			_entityManager.getTransaction().commit();
		}
	}
	
}
