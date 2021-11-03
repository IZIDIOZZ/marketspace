package com.marketspace.data.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.marketspace.data.configurations.DbContextProvider;
import com.marketspace.data.mappings.ItemVenda;
import com.marketspace.data.mappings.Venda;
import com.marketspace.domain.entities.DateHelper;

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
	
	public List<ItemVenda> BuscarItensVendaDetalhado(Date dataInicial, Date dataFinal) {
		try {
			dataInicial = DateHelper.SubtrairDiaDeData(dataInicial, 1);
			dataFinal = DateHelper.AdicionarDiaDeData(dataFinal, 1);
			
			CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
			CriteriaQuery<ItemVenda> ctr = cb.createQuery(ItemVenda.class);
			Root<ItemVenda> root = ctr.from(ItemVenda.class);
			Predicate DataVendaEstahEntreDatas = cb.and(cb.between(root.get("Venda").get("DataCadastro"), dataInicial,dataFinal));
			
			return _entityManager.createQuery(ctr.select(root).where(DataVendaEstahEntreDatas)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Venda> BuscarVendasResumido(Date dataInicial, Date dataFinal) {
		try {
			dataInicial = DateHelper.SubtrairDiaDeData(dataInicial, 1);
			dataFinal = DateHelper.AdicionarDiaDeData(dataFinal, 1);
			
			CriteriaBuilder cb = new DbContextProvider().getEntityManagerFactory().getCriteriaBuilder();
			CriteriaQuery<Venda> ctr = cb.createQuery(Venda.class);
			Root<Venda> root = ctr.from(Venda.class);
			Predicate DataVendaEstahEntreDatas = cb.and(cb.between(root.get("DataCadastro"), dataInicial,dataFinal));
			return _entityManager.createQuery(ctr.select(root).where(DataVendaEstahEntreDatas)).getResultList();
			
		} catch (Exception e) {
			return null;
		}
	}
	
}
