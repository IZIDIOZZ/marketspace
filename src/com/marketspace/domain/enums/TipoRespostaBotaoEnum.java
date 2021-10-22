package com.marketspace.domain.enums;

public enum TipoRespostaBotaoEnum {
	Default(0),
	YesOrNo(1), 
	OkCancel(2);

	int TipoTextoBotao;

	public int getTipoTextoBotao() {
		return TipoTextoBotao;
	}
	
	TipoRespostaBotaoEnum(int tipoPessoa) {
		TipoTextoBotao = tipoPessoa;
	}
}
