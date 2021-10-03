package com.marketspace.domain.enums;

public enum MarketSpaceEnum {
	nome("marketSpace");

	String Nome;

	public String getNome() {
		return Nome;
	}

	MarketSpaceEnum(String nome) {
		Nome = nome;
	}
}
