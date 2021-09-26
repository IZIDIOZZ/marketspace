package com.marketspace.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String CEP;
	private String Logradouro;
	private String Bairro;
	private String Cidade;
	private String Numero;
	private Date   DataCadastro;
	private Date   DataAtualizacao;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pessoa Pessoa;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public Date getDataCadastro() {
		return DataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		DataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return DataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		DataAtualizacao = dataAtualizacao;
	}

	public Pessoa getPessoa() {
		return Pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		Pessoa = pessoa;
	}
	public Endereco() {}
	public Endereco(String cEP, String logradouro, String bairro, String cidade, String numero,
			Date dataCadastro, Date dataAtualizacao, Pessoa pessoa) {
		super();
		CEP = cEP;
		Logradouro = logradouro;
		Bairro = bairro;
		Cidade = cidade;
		Numero = numero;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Endereco [CEP=" + CEP + ", Logradouro=" + Logradouro + ", Bairro=" + Bairro + ", Cidade="
				+ Cidade + ", Numero=" + Numero + ", DataCadastro=" + DataCadastro + ", DataAtualizacao="
				+ DataAtualizacao + ", Pessoa=" + Pessoa + "]";
	}

}
