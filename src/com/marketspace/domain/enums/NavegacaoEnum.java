package com.marketspace.domain.enums;

public enum NavegacaoEnum {
	Primeiro(0),
	Anterior(1),
	Proximo(2), 
	Ultimo(3);

	int ordemNavegacao;
	public int getTipoPessoa() {
		return ordemNavegacao;
	}
	NavegacaoEnum(int ordemNavegacao) {
		this.ordemNavegacao = ordemNavegacao;
	}
}
