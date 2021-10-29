package com.marketspace.domain.viewModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FornecedorViewModel {
	private final SimpleIntegerProperty Id;
	private final SimpleStringProperty CNPJ;
	private final SimpleStringProperty RazaoSocial;
	private final SimpleStringProperty NomeFantasia;
	
	public int getId() {
		return Id.get();
	}

	public void setId(int id) {
		Id.set(id);
	}

	public String getRazaoSocial() {
		return RazaoSocial.get();
	}

	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial.set(razaoSocial);
	}

	public String getNomeFantasia() {
		return NomeFantasia.get();
	}

	public void setNomeFantasia(String nomeFantasia) {
		NomeFantasia.set(nomeFantasia);
	}

	public String getCNPJ() {
		return CNPJ.get();
	}

	public void setCNPJ(String cnpj) {
		CNPJ.set(cnpj);
	}
	
	public FornecedorViewModel(int id, String razaoSocial, String nomeFantasia, String cnpj) {
		super();
		Id = new SimpleIntegerProperty(id);
		RazaoSocial= new SimpleStringProperty(razaoSocial);
		NomeFantasia= new SimpleStringProperty(nomeFantasia);
		CNPJ= new SimpleStringProperty(cnpj);
	}
}
