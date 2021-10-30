package com.marketspace.data.repositories;

import javax.persistence.EntityManager;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.Produto;

public class ProdutoRepository {
	EntityManager _entityManager;

	public ProdutoRepository() {
		_entityManager = new DbContextProvider().getEntityManager();
	}

	public Produto BuscarProdutoPorId(int ProdutoId) {
		try {
			_entityManager.getTransaction().begin();
			Produto produto = _entityManager.find(Produto.class, ProdutoId);
			return produto;
		}catch (Exception e) {	
			return null;
		}
		finally {
			_entityManager.getTransaction().commit();
		}
	}

	public boolean InserirProduto(Produto produto) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.persist(produto);
			return true;
		}catch (Exception e) {	
			return false;
		}
		finally {
			_entityManager.getTransaction().commit();
		}

	}

	public boolean AtualizarProduto(Produto produto) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.merge(produto);
			return true;
		}catch (Exception e) {	
			return false;
		}
		finally {
			_entityManager.getTransaction().commit();
		}
	}

	public boolean RemoverProduto(int ProdutoId) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.remove(_entityManager.find(Produto.class, ProdutoId));
			return true;
		}catch (Exception e) {	
			return false;
		}
		finally {
			_entityManager.getTransaction().commit();
		}
	}
}
