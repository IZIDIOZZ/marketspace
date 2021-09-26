package com.marketspace.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String Login;
	private String Senha;
	private Date DataCadastro;
	private Date DataAtualizacao;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Pessoa Pessoa;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
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

	public Usuario() {}
	public Usuario(String login, String senha, Date dataCadastro, Date dataAtualizacao,
			Pessoa pessoa) {
		super();
		Login = login;
		Senha = senha;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Usuario [Id=" + Id + ", Login=" + Login + ", Senha=" + Senha + ", DataCadastro=" + DataCadastro
				+ ", DataAtualizacao=" + DataAtualizacao + ", Pessoa=" + Pessoa + "]";
	}
	
}
