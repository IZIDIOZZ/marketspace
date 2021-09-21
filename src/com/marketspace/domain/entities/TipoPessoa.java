package com.marketspace.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoPessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String TipoPessoa;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTipoPessoa() {
		return TipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		TipoPessoa = tipoPessoa;
	}

	public TipoPessoa() {}
	public TipoPessoa(String tipoPessoa) {
		super();
		TipoPessoa = tipoPessoa;
	}

	@Override
	public String toString() {
		return "TipoPessoa [Id=" + Id + ", TipoPessoa=" + TipoPessoa + "]";
	}
	
	
	
}
