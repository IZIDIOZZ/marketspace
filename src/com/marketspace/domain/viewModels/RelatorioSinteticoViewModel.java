package com.marketspace.domain.viewModels;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RelatorioSinteticoViewModel {
	private SimpleIntegerProperty CodigoVenda;
	private SimpleFloatProperty ValorTotalVenda;
	private SimpleIntegerProperty QuantidadeTotalProdutosVendidos;
	private SimpleStringProperty DataVenda;

	public int getCodigoVenda() {
		return CodigoVenda.get();
	}

	public void setCodigoVenda(int codigoVenda) {
		CodigoVenda.set(codigoVenda);
	}
	

	public Float getValorTotalVenda() {
		return ValorTotalVenda.get();
	}

	public void setValorTotalVenda(Float valorTotalVenda) {
		ValorTotalVenda.set(valorTotalVenda);
	}

	public int getQuantidadeTotalProdutosVendidos() {
		return QuantidadeTotalProdutosVendidos.get();
	}

	public void setQuantidadeTotalProdutosVendidos(int quantidadeTotalProdutosVendidos) {
		QuantidadeTotalProdutosVendidos.set(quantidadeTotalProdutosVendidos);
	}

	public String getDataVenda() {
		return DataVenda.get();
	}

	public void setDataVenda(String dataVenda) {
		DataVenda.set(dataVenda);
	}

	public RelatorioSinteticoViewModel(int codigoVenda, Float valorTotalVenda,
			int quantidadeTotalProdutosVendidos, String dataVenda) {
		super();
		CodigoVenda = new SimpleIntegerProperty(codigoVenda);
		ValorTotalVenda = new SimpleFloatProperty(valorTotalVenda) ;
		QuantidadeTotalProdutosVendidos = new SimpleIntegerProperty(quantidadeTotalProdutosVendidos) ;
		DataVenda = new SimpleStringProperty(dataVenda) ;
	}
}
