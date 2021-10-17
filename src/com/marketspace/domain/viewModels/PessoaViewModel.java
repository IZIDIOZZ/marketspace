package com.marketspace.domain.viewModels;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PessoaViewModel {
	private final SimpleIntegerProperty Id;
	private final SimpleStringProperty Documento;
	private final SimpleStringProperty RazaoSocial;
	private final SimpleStringProperty NomeFantasia;
	private final SimpleStringProperty TipoPessoa;
	private final SimpleBooleanProperty JaEstahVinculadoComUsuario;

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

	public String getDocumento() {
		return Documento.get();
	}

	public void setDocumento(String documento) {
		Documento.set(documento);
	}

	public String getTipoPessoa() {
		return TipoPessoa.get();
	}

	public void setTipoPessoa(String tipoPessoa) {
		TipoPessoa.set(tipoPessoa);
	}

	public boolean getJaEstahVinculadoComUsuario() {
		return JaEstahVinculadoComUsuario.get();
	}

	public void setJaEstahVinculadoComUsuario(boolean jaEstahVinculadoComUsuario) {
		JaEstahVinculadoComUsuario.set(jaEstahVinculadoComUsuario);
	}
	
	public PessoaViewModel(int id, String razaoSocial,
			String nomeFantasia, String documento, String tipoPessoa,
			boolean jaEstahVinculadoComUsuario) {
		super();
		Id = new SimpleIntegerProperty(id);
		RazaoSocial= new SimpleStringProperty(razaoSocial);
		NomeFantasia= new SimpleStringProperty(nomeFantasia);
		Documento= new SimpleStringProperty(documento);
		TipoPessoa= new SimpleStringProperty(tipoPessoa);
		JaEstahVinculadoComUsuario= new SimpleBooleanProperty(jaEstahVinculadoComUsuario);
	}
}
