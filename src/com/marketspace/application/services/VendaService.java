package com.marketspace.application.services;

import java.util.Date;
import java.util.List;

import com.marketspace.data.mappings.ItemVenda;
import com.marketspace.data.mappings.Venda;
import com.marketspace.data.repositories.VendaRepository;

public class VendaService {
	VendaRepository _vendaRepository;

	public VendaService() {
		_vendaRepository = new VendaRepository();
	}

	public boolean FinalizarVenda(Venda venda) {
		return _vendaRepository.InserirVenda(venda);
	}
	
	public List<ItemVenda> BuscarItensVendaDetalhado(Date dataInicial, Date dataFinal) {
		return _vendaRepository.BuscarItensVendaDetalhado(dataInicial, dataFinal);
	}
	
	public List<Venda> BuscarVendasResumidas(Date dataInicial, Date dataFinal) {
		return _vendaRepository.BuscarVendasResumido(dataInicial, dataFinal);
	}
}
