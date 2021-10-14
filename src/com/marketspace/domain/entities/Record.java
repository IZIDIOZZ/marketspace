package com.marketspace.domain.entities;

public class Record {
	private int idRegistroAtual;
	private int posicaoAtual;
	private long tamanhoColecao;
	private boolean ehUltimoRegistro;
	private boolean ehPrimeiroRegistro;
	
	public void IrParaRegistroProximo() {
		posicaoAtual++;
	}
	public void IrParaRegistroAnterior() {
		posicaoAtual--;
	}
	
}
