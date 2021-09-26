package com.marketspace.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String RazaoSocial;
	private String NomeFantasia;
	private String Documento;
	private Date DataCadastro;
	private Date DataAtualizacao;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Usuario Usuario;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TipoPessoa TipoPessoa;
	
	@OneToMany(mappedBy="Pessoa",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Endereco> Enderecos;
	
	public List<Endereco> getEnderecos() {
		return Enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		Enderecos = enderecos;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRazaoSocial() {
		return RazaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return NomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		NomeFantasia = nomeFantasia;
	}

	public String getDocumento() {
		return Documento;
	}

	public void setDocumento(String documento) {
		Documento = documento;
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

	public TipoPessoa getTipoPessoa() {
		return TipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		TipoPessoa = tipoPessoa;
	}
	
	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}

	public Pessoa() {}

	public Pessoa(String razaoSocial, String nomeFantasia, String documento, Date dataCadastro,
			Date dataAtualizacao, Usuario usuario,
			TipoPessoa tipoPessoa, List<Endereco> enderecos) {
		super();
		RazaoSocial = razaoSocial;
		NomeFantasia = nomeFantasia;
		Documento = documento;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Usuario = usuario;
		TipoPessoa = tipoPessoa;
		Enderecos = enderecos;
	}

	@Override
	public String toString() {
		Enderecos.forEach(x->System.out.println(x.toString())); 
		return "Pessoa [Id=" + Id + ", RazaoSocial=" + RazaoSocial + ", NomeFantasia=" + NomeFantasia + ", Documento="
				+ Documento + ", DataCadastro=" + DataCadastro + ", DataAtualizacao=" + DataAtualizacao + ", Usuario="
				+ Usuario + ", TipoPessoa=" + TipoPessoa + ", Enderecos=" + "]";
		
		
	}

}
