package com.marketspace.data.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.NivelUsuario;
import com.marketspace.data.mappings.Usuario;

public class UsuarioRepository {
	EntityManager _entityManager;

	public UsuarioRepository() {
		_entityManager = new DbContextProvider().getEntityManager();
	}

	public Usuario BuscarUsuarioPorId(int id) {
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

	public NivelUsuario BuscarNivelUsuario(String nivelUsuario) {
		CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<NivelUsuario> ctr = cb.createQuery(NivelUsuario.class);
		Root<NivelUsuario> root = ctr.from(NivelUsuario.class);
		ctr.select(root).where(cb.equal(root.get("Nivel"), nivelUsuario));
		return _entityManager.createQuery(ctr).getSingleResult();
	}

	public void InserirUsuario(Usuario usuario) {
		_entityManager.getTransaction().begin();
		_entityManager.persist(usuario);
		_entityManager.getTransaction().commit();
	}

	public void AtualizarUsuario(Usuario usuario) {
		_entityManager.getTransaction().begin();
		_entityManager.merge(usuario);
		_entityManager.getTransaction().commit();
	}

	public void RemoverUsuario(int usuarioId){
		_entityManager.getTransaction().begin();
		_entityManager.remove(_entityManager.find(Usuario.class, usuarioId));
		_entityManager.getTransaction().commit();
	}

	public List<NivelUsuario> BuscarTodosNiveisUsuario() {
		CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<NivelUsuario> ctr = cb.createQuery(NivelUsuario.class);
		Root<NivelUsuario> root = ctr.from(NivelUsuario.class);
		ctr.select(root);
		return _entityManager.createQuery(ctr).getResultList();
	}
}
