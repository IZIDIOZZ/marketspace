package com.marketspace.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	private String Nome;
	private String Email;
	
	public Pessoa() {
	}

	public Pessoa(int id, String nome, String email) {
		super();
		Id = id;
		Nome = nome;
		Email = email;
	}
	public Pessoa(String nome, String email) {
		super();
		Nome = nome;
		Email = email;
	}


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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "Pessoa [Id=" + Id + ", Nome=" + Nome + ", Email=" + Email + "]";
	}
	
	
}
