package com.marketspace.domain.viewModels;

import java.util.Date;

import com.marketspace.data.mappings.Endereco;
import com.marketspace.data.mappings.Estado;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class EnderecoViewModel {
	private SimpleIntegerProperty Id;
	private SimpleStringProperty CEP;
	private SimpleStringProperty Logradouro;
	private SimpleStringProperty Bairro;
	private SimpleStringProperty Cidade;
	private SimpleStringProperty Numero;
	private ObjectProperty<Date> DataCadastro;
	private ObjectProperty<Date> DataAtualizacao;
	private SimpleStringProperty Estado;

	public int getId() {
		return Id.get();
	}

	public void setId(Integer id) {
		Id.set(id);
	}

	public String getCEP() {
		return CEP.get();
	}

	public void setCEP(String cEP) {
		CEP.set(cEP);
	}

	public String getLogradouro() {
		return Logradouro.get();
	}

	public void setLogradouro(String logradouro) {
		Logradouro.set(logradouro);
	}

	public String getBairro() {
		return Bairro.get();
	}

	public void setBairro(String bairro) {
		Bairro.set(bairro);
	}

	public String getCidade() {
		return Cidade.get();
	}

	public void setCidade(String cidade) {
		Cidade.set(cidade);
	}

	public String getNumero() {
		return Numero.get();
	}

	public void setNumero(String numero) {
		Numero.set(numero);
	}

	public Date getDataCadastro() {
		return DataCadastro.get();
	}

	public void setDataCadastro(Date dataCadastro) {
		DataCadastro.set(dataCadastro);
	}

	public Date getDataAtualizacao() {
		return DataAtualizacao.get();
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		DataAtualizacao.set(dataAtualizacao);
	}
	
	
	public String getEstado() {
		return Estado.get();
	}

	public void setEstado(String estado) {
		Estado.set(estado);
	}

	public EnderecoViewModel() {}
	public EnderecoViewModel(String cEP, String logradouro, String bairro, String cidade, String numero,
			Date dataCadastro, Date dataAtualizacao,String estado) {
		super();
		CEP = new SimpleStringProperty(cEP);
		Logradouro = new SimpleStringProperty(logradouro);
		Bairro = new SimpleStringProperty(bairro);
		Cidade = new SimpleStringProperty(cidade);
		Numero = new SimpleStringProperty(numero);
		DataCadastro = new SimpleObjectProperty<Date>(dataCadastro);
		DataAtualizacao = new SimpleObjectProperty<Date>(dataAtualizacao);
		Estado = new SimpleStringProperty(estado);
	}

	public EnderecoViewModel(int id, String cEP, String logradouro, String bairro, String cidade, String numero,
			Date dataCadastro, Date dataAtualizacao, String estado) {
		super();
		Id = new SimpleIntegerProperty(id);
		CEP = new SimpleStringProperty(cEP);
		Logradouro = new SimpleStringProperty(logradouro);
		Bairro = new SimpleStringProperty(bairro);
		Cidade = new SimpleStringProperty(cidade);
		Numero = new SimpleStringProperty(numero);
		DataCadastro = new SimpleObjectProperty<Date>(dataCadastro);
		DataAtualizacao = new SimpleObjectProperty<Date>(dataAtualizacao);
		Estado = new SimpleStringProperty(estado);
		
	}
	public EnderecoViewModel(int id, String cEP, String logradouro, String bairro, String cidade, String numero,
			Date dataCadastro, Date dataAtualizacao) {
		super();
		Id = new SimpleIntegerProperty(id);
		CEP = new SimpleStringProperty(cEP);
		Logradouro = new SimpleStringProperty(logradouro);
		Bairro = new SimpleStringProperty(bairro);
		Cidade = new SimpleStringProperty(cidade);
		Numero = new SimpleStringProperty(numero);
		DataCadastro = new SimpleObjectProperty<Date>(dataCadastro);
		DataAtualizacao = new SimpleObjectProperty<Date>(dataAtualizacao);
		
	}
}
