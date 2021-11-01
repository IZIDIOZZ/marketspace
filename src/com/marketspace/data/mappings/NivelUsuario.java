package com.marketspace.data.mappings;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NivelUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(unique = true)
	private String Nivel;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	public String getNivel() {
		return Nivel;
	}

	public void setNivel(String nivel) {
		Nivel = nivel;
	}

	public NivelUsuario(int id, String nivel) {
		super();
		Id = id;
		Nivel = nivel;
	}
	public NivelUsuario() {}
	public NivelUsuario( String nivel) {
		super();
		Nivel = nivel;
	}

	@Override
	public String toString() {
		return "NivelUsuario [Id=" + Id + ", " + (Nivel != null ? "Nivel=" + Nivel : "") + "]";
	}

}
