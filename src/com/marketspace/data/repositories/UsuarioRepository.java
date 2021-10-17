package com.marketspace.data.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.NivelUsuario;
import com.marketspace.data.mappings.Pessoa;
import com.marketspace.data.mappings.Usuario;

public class UsuarioRepository {
	EntityManager _entityManager;

	public UsuarioRepository() {
		_entityManager = new DbContextProvider().getEntityManager();
	}
	
	public Usuario BuscarUsuarioPorId(int id){
		Usuario usuario = new Usuario();
		try {
			_entityManager.getTransaction().begin();
			usuario = _entityManager.find(Usuario.class, id);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			_entityManager.getTransaction().commit();
		}
		return usuario;
	}
	
	public boolean InserirUsuario(Usuario usuario) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.merge(usuario);
			return true;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			_entityManager.getTransaction().commit();
		}
	}
	
	public void AtualizarUsuario(Usuario usuario) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.merge(usuario);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			_entityManager.getTransaction().commit();
		}
	}
	
	public void RemoverUsuario(int usuarioId) {
		try {
			_entityManager.getTransaction().begin();
			_entityManager.remove(_entityManager.find(Pessoa.class, usuarioId));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			_entityManager.getTransaction().commit();
		}
	}
	public List<NivelUsuario> BuscarTodosNiveisUsuario() {
		CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<NivelUsuario> ctr = cb.createQuery(NivelUsuario.class);
		Root<NivelUsuario> root = ctr.from(NivelUsuario.class);
		ctr.select(root);
		return _entityManager.createQuery(ctr).getResultList();
	}
}
