package com.marketspace.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String Nome;
	
	@OneToMany(mappedBy = "Estado",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Endereco> Enderecos;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public List<Endereco> getEnderecos() {
		return Enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		Enderecos = enderecos;
	}
	public Estado() {}
	public Estado(int id, String nome, List<Endereco> enderecos) {
		super();
		Id = id;
		Nome = nome;
		Enderecos = enderecos;
	}

	@Override
	public String toString() {
		return "Estado [Id=" + Id + ", " + (Nome != null ? "Nome=" + Nome + ", " : "")
				 + "]";
	} 
	
}
