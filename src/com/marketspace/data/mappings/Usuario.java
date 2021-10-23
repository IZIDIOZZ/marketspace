package com.marketspace.data.mappings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(unique = true)
	private String Login;
	private String Senha;
	private Date DataCadastro;
	private Date DataAtualizacao;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Pessoa Pessoa;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private NivelUsuario NivelUsuario;

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

	public NivelUsuario getNivelUsuario() {
		return NivelUsuario;
	}

	public void setNivelUsuario(NivelUsuario nivelUsuario) {
		NivelUsuario = nivelUsuario;
	}
	public Usuario() {}
	public Usuario(String login, String senha, Date dataCadastro, Date dataAtualizacao,
			Pessoa pessoa, NivelUsuario nivelUsuario) {
		super();
		Login = login;
		Senha = senha;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Pessoa = pessoa;
		NivelUsuario = nivelUsuario;
	}

	public Usuario(int id, String login, String senha, Date dataCadastro, Date dataAtualizacao,
			Pessoa pessoa, NivelUsuario nivelUsuario) {
		super();
		Id = id;
		Login = login;
		Senha = senha;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Pessoa = pessoa;
		NivelUsuario = nivelUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [Id=" + Id + ", " + (Login != null ? "Login=" + Login + ", " : "")
				+ (Senha != null ? "Senha=" + Senha + ", " : "")
				+ (DataCadastro != null ? "DataCadastro=" + DataCadastro + ", " : "")
				+ (DataAtualizacao != null ? "DataAtualizacao=" + DataAtualizacao + ", " : "")
				+ (Pessoa != null ? "Pessoa=" + Pessoa + ", " : "")
				+ (NivelUsuario != null ? "NivelUsuario=" + NivelUsuario : "") + "]";
	}
}
