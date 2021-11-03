package com.marketspace.domain.viewModels;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RelatorioAnaliticoViewModel {
	private SimpleIntegerProperty CodigoVenda;
	private SimpleIntegerProperty CodigoProduto;
	private SimpleIntegerProperty QuantidadeVendida;
	private SimpleFloatProperty ValorProduto;
	private SimpleStringProperty NomeProduto;
	

	public String getNomeProduto() {
		return NomeProduto.get();
	}

	public void setNomeProduto(String nomeProduto) {
		NomeProduto.set(nomeProduto);
	}

	public int getCodigoVenda() {
		return CodigoVenda.get();
	}

	public void setCodigoVenda(int codigoVenda) {
		CodigoVenda.set(codigoVenda);
	}

	public int getCodigoProduto() {
		return CodigoProduto.get();
	}

	public void setCodigoProduto(int codigoProduto) {
		CodigoProduto.get();
	}

	public int getQuantidadeVendida() {
		return QuantidadeVendida.get();
	}

	public void setQuantidadeVendida(int quantidadeVendida) {
		QuantidadeVendida.set(quantidadeVendida);
	}

	public Float getValorProduto() {
		return ValorProduto.get();
	}

	public void setValorProduto(Float valorProduto) {
		ValorProduto.set(valorProduto);
	}

	public RelatorioAnaliticoViewModel(int codigoVenda, int codigoProduto,
			int quantidadeVendida, Float valorProduto, String nomeProduto) {
		super();
		CodigoVenda = new SimpleIntegerProperty(codigoVenda);
		CodigoProduto = new SimpleIntegerProperty(codigoProduto);
		QuantidadeVendida = new SimpleIntegerProperty(quantidadeVendida);
		ValorProduto = new SimpleFloatProperty(valorProduto);
		NomeProduto = new SimpleStringProperty(nomeProduto);
	}
}
