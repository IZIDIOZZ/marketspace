package com.marketspace.domain.viewModels;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemVendaViewModel {

	private SimpleIntegerProperty Codigo;
	private SimpleStringProperty Nome;
	private SimpleIntegerProperty Quantidade;
	private SimpleFloatProperty Preco;
	private SimpleStringProperty CodigoBarras;
	
	public int getCodigo() {
		return Codigo.get();
	}

	public void setCodigo(int codigo) {
		Codigo.set(codigo);
	}

	public String getNome() {
		return Nome.get();
	}

	public void setNome(String nome) {
		Nome.set(nome);
	}

	public int getQuantidade() {
		return Quantidade.get();
	}

	public void setQuantidade(int quantidade) {
		Quantidade.set(quantidade);
	}

	public Float getPreco() {
		return Preco.get();
	}

	public void setPreco(Float preco) {
		Preco.set(preco);
	}

	public String getCodigoBarras() {
		return CodigoBarras.get();
	}

	public void setCodigoBarras(String codigoBarras) {
		CodigoBarras.set(codigoBarras);
	}

	public ItemVendaViewModel(int codigo, String nome, int quantidade, Float preco, String codigoBarras) {
		super();
		Codigo = new SimpleIntegerProperty(codigo);
		Nome = new SimpleStringProperty(nome);
		Quantidade = new SimpleIntegerProperty(quantidade);
		Preco = new SimpleFloatProperty(preco);
		CodigoBarras = new SimpleStringProperty(codigoBarras);
	}
}
