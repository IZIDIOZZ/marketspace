package com.marketspace.domain.enums;

public enum TipoPessoaEnum {
	Fisica("F�sica"), Juridica("Juridica");

	String TipoPessoa;

	public String getTipoPessoa() {
		return TipoPessoa;
	}

	TipoPessoaEnum(String tipoPessoa) {
		TipoPessoa = tipoPessoa;
	}
}
