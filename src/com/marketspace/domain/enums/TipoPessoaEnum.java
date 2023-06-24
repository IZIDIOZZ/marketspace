package com.marketspace.domain.enums;

public enum TipoPessoaEnum {
	Fisica("Fisica"), Juridica("Juridica");

	String TipoPessoa;

	public String getTipoPessoa() {
		return TipoPessoa;
	}

	TipoPessoaEnum(String tipoPessoa) {
		TipoPessoa = tipoPessoa;
	}
}
