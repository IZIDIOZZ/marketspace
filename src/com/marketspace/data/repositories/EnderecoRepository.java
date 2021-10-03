package com.marketspace.data.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.domain.entities.Endereco;
import com.marketspace.domain.entities.Estado;

public class EnderecoRepository {
	EntityManager _entityManager;

	public EnderecoRepository() {
		_entityManager = new DbContextProvider().getEntityManager();
	}

	public List<Estado> ObterEstados() {
		CriteriaBuilder cb = _entityManager.getCriteriaBuilder();
		CriteriaQuery<Estado> query = cb.createQuery(Estado.class);
		Root<Estado> root = query.from(Estado.class);
		query.select(root).orderBy(cb.asc(root.get("Nome")));
		return _entityManager.createQuery(query).getResultList();
	}

	public Estado ObterEstadoPorNome(String nomeEstado) {
		CriteriaBuilder cb = _entityManager.getCriteriaBuilder();
		CriteriaQuery<Estado> query = cb.createQuery(Estado.class);
		Root<Estado> root = query.from(Estado.class);
		Predicate condicao= cb.equal(root.get("Nome"), nomeEstado);

		query.select(root).where(condicao);
		return _entityManager.createQuery(query).getSingleResult();
	}
	
	public boolean AtualizarEndereco(Endereco endereco) {
		boolean execucao = false;
		try {
			_entityManager.getTransaction().begin();
			_entityManager.merge(endereco);
			execucao =true;
			
		} catch (Exception e) {
			execucao = false;
		}
		finally {
			_entityManager.getTransaction().commit();
		}
	
		return execucao;
	}
	
	public boolean RemoverEndereco(int enderecoId) {
		boolean execucao = false;
		Endereco endereco = new Endereco();
		try {
			_entityManager.getTransaction().begin();
			endereco = _entityManager.find(Endereco.class, enderecoId);
			_entityManager.remove(endereco);
			execucao =true;
		} catch (Exception e) {
			execucao = false;
		}
		finally {
			_entityManager.getTransaction().commit();
		}
		return execucao;
	}
}
