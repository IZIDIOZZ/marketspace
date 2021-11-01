package com.marketspace.application.services;

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
}
