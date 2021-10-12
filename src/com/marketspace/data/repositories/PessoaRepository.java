package com.marketspace.data.repositories;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.TipoPessoa;
import com.marketspace.domain.enums.NavegacaoEnum;

public class PessoaRepository {
	EntityManager _entityManager;

	public PessoaRepository() {
		_entityManager = new DbContextProvider().getEntityManager();
	}

	public Pessoa BuscarPessoa(int id) {
		Pessoa pessoa = new Pessoa();
		try {
			_entityManager.getTransaction().begin();
			pessoa = _entityManager.find(Pessoa.class, id);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			_entityManager.getTransaction().commit();
		}
		return pessoa;
	}
	
	public void BuscarPessoaPorNavegacao(int id, NavegacaoEnum navegacao) {
		try {
			String baseHql = "Select top 1 * from Pessoa %s";
			switch (navegacao) {
				case Primeiro:
					baseHql = String.format(baseHql, ""+id);
					break;
				case Anterior:
					baseHql = String.format(baseHql,  "where id < '"+ id +"'");
					break;
				case Proximo:
					baseHql = String.format(baseHql, "where id > '"+ id +"'");
					break;
				case Ultimo:
					baseHql = String.format(baseHql, "order by id desc");
					break;
			}
			Pessoa pessoa = _entityManager.createQuery(baseHql,Pessoa.class).getSingleResult();
			
		} catch (Exception e) {
			
		}
	}
	
	public List<TipoPessoa> ObterTiposDePessoa() {

		CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<TipoPessoa> ctr = cb.createQuery(TipoPessoa.class);
		Root<TipoPessoa> root = ctr.from(TipoPessoa.class);
		return _entityManager.createQuery(ctr.select(root)).getResultList();
	}

	public boolean InserirPessoa(Pessoa pessoa) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.merge(pessoa);
			return true;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			_entityManager.getTransaction().commit();
		}
	}

	public void AtualizarPessoa(Pessoa pessoa) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.merge(pessoa);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			_entityManager.getTransaction().commit();
		}
	}

	public void RemoverPessoa(int id) {
		Pessoa pessoa = new Pessoa();
		try {
			_entityManager.getTransaction().begin();
			pessoa = _entityManager.find(Pessoa.class, id);
			_entityManager.remove(pessoa);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			_entityManager.getTransaction().commit();
		}
	}

	public TipoPessoa BuscarTipoPessoa(String tipoPessoa) {
		CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<TipoPessoa> ctr = cb.createQuery(TipoPessoa.class);
		Root<TipoPessoa> root = ctr.from(TipoPessoa.class);
		ctr.select(root).where(cb.equal(root.get("TipoPessoa"), tipoPessoa));
		return _entityManager.createQuery(ctr).getSingleResult();
	}
}
