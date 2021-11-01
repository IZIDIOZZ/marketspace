package com.marketspace.data.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.TipoPessoa;
import com.marketspace.domain.enums.TipoPessoaEnum;


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
	
	public List<Pessoa> BuscarPessoasPorDocumentoOuNome(String pesquisa) {
		Predicate[] condicoes = new Predicate[3];
		CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<Pessoa> ctr = cb.createQuery(Pessoa.class);
		Root<Pessoa> root = ctr.from(Pessoa.class);
		
		condicoes[0] = (cb.like(root.get("RazaoSocial"), "%"+pesquisa+"%"));
		condicoes[1] = (cb.like(root.get("NomeFantasia"), "%"+pesquisa+"%"));
		condicoes[2] = (cb.equal(root.get("Documento"), (pesquisa.replace("-", "").replace(".", "").replace("/", "")).replaceAll("\\s+", "")));
		ctr.select(root).where(cb.or(condicoes));
		return _entityManager.createQuery(ctr).getResultList();
	}
	
	public List<Pessoa> BuscarFornecedorPorDocumentoOuNome(String pesquisa) {
		CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<Pessoa> ctr = cb.createQuery(Pessoa.class);
		Root<Pessoa> root = ctr.from(Pessoa.class);
		
		Predicate IgualRazaoSocial = cb.or(cb.like(root.get("RazaoSocial"), "%"+pesquisa+"%"));
		Predicate IgualNomeFantasia = cb.or(cb.like(root.get("NomeFantasia"), "%"+pesquisa+"%"));
		Predicate IgualDocumento= cb.or(cb.equal(root.get("Documento"), (pesquisa.replace("-", "").replace(".", "").replace("/", "")).replaceAll("\\s+", "")));
		Predicate condicaoPesquisa = cb.or(IgualRazaoSocial,IgualNomeFantasia,IgualDocumento);
		Predicate condicaoSeEhPessoa = cb.and(cb.equal(root.get("TipoPessoa").<String>get("TipoPessoa"), TipoPessoaEnum.Juridica.getTipoPessoa()));
		Predicate condicaoFinal = cb.and(condicaoSeEhPessoa,condicaoPesquisa);
		
		ctr.select(root).where(condicaoFinal);
		
		return _entityManager.createQuery(ctr).getResultList();
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
