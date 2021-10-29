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
		_entityManager.getTransaction().begin();
		Produto produto = _entityManager.find(Produto.class, ProdutoId);
		_entityManager.getTransaction().commit();
		return produto;
	}

	public void InserirProduto(Produto produto) {
		_entityManager.getTransaction().begin();
		_entityManager.persist(produto);
		_entityManager.getTransaction().commit();
	}

	public void AtualizarProduto(Produto produto) {
		_entityManager.getTransaction().begin();
		_entityManager.merge(produto);
		_entityManager.getTransaction().commit();
	}

	public void RemoverProduto(int ProdutoId) {
		_entityManager.getTransaction().begin();
		_entityManager.merge(_entityManager.find(Produto.class, ProdutoId));
		_entityManager.getTransaction().commit();
	}
}
