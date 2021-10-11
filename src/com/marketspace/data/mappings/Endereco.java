package com.marketspace.data.mappings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.marketspace.domain.viewModels.EnderecoViewModel;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String CEP;
	private String Endereco;
	private String Bairro;
	private String Cidade;
	private String Numero;
	private Date DataCadastro;
	private Date DataAtualizacao;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pessoa Pessoa;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Estado Estado;

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
	
	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
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

	public Estado getEstado() {
		return Estado;
	}

	public void setEstado(Estado estado) {
		Estado = estado;
	}

	public Endereco() {
	}

	public Endereco(int id, String cEP, String endereco, String bairro, String cidade, String numero,
			Date dataCadastro, Date dataAtualizacao, Pessoa pessoa, Estado estado) {
		super();
		Id = id;
		CEP = cEP;
		Endereco = endereco;
		Bairro = bairro;
		Cidade = cidade;
		Numero = numero;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Pessoa = pessoa;
		Estado = estado;
	}

	public Endereco(String cEP, String endereco, String bairro, String cidade, String numero, Date dataCadastro,
			Date dataAtualizacao, Pessoa pessoa, Estado estado) {
		super();
		CEP = cEP;
		Endereco = endereco;
		Bairro = bairro;
		Cidade = cidade;
		Numero = numero;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Pessoa = pessoa;
		Estado = estado;
	}
	
	public Endereco(String cEP, String endereco, String bairro, String cidade, String numero, Date dataCadastro,
			Date dataAtualizacao, Estado estado) {
		super();
		CEP = cEP;
		Endereco = endereco;
		Bairro = bairro;
		Cidade = cidade;
		Numero = numero;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Estado = estado;
	}
	
	public Endereco(int id, String cEP, String endereco, String bairro, String cidade, String numero,
			Date dataCadastro, Date dataAtualizacao,Estado estado) {
		super();
		Id = id;
		CEP = cEP;
		Endereco = endereco;
		Bairro = bairro;
		Cidade = cidade;
		Numero = numero;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Estado = estado;
	}
	
	@Override
	public String toString() {
		return "Endereco [CEP=" + CEP + ", Endereco=" + Endereco + ", Bairro=" + Bairro + ", Cidade=" + Cidade
				+ ", Numero=" + Numero + ", DataCadastro=" + DataCadastro + ", DataAtualizacao=" + DataAtualizacao
				+ ", Pessoa=" + Pessoa + "]";
	}

	public EnderecoViewModel ConverterTo() {
		EnderecoViewModel evm = new EnderecoViewModel(this.getId(), this.getCEP(), this.getEndereco(),
				this.getBairro(), this.getCidade(), this.getNumero(), this.getDataCadastro(), this.getDataAtualizacao(),
				this.getEstado().getNome());
		return evm;
	}

}
