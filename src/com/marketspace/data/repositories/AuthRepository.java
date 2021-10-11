package com.marketspace.data.repositories;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.Usuario;

public class AuthRepository {
	EntityManager _dbmanager;

	public AuthRepository() {
		_dbmanager = new DbContextProvider().getEntityManager();
	}
	
	public Usuario BuscarUsuario(String login, String senha){
		Usuario usuario = new Usuario();
		try {
			
			CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
			Predicate[] condicoes = new Predicate[2];
			
			CriteriaQuery<Usuario> ctr = cb.createQuery(Usuario.class);
			Root<Usuario> root = ctr.from(Usuario.class);
			condicoes[0] = cb.equal(root.get("Login"), login);
			condicoes[1] = cb.equal(root.get("Senha"), senha);
			ctr.select(root).where(condicoes);
			TypedQuery<Usuario> query= _dbmanager.createQuery(ctr);
			
			usuario = query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return usuario;
	}
}
