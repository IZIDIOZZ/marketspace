package com.marketspace.domain.entities;

import java.util.List;

import com.marketspace.data.mappings.ItemVenda;
import com.marketspace.data.mappings.Produto;

public class ProdutoQuantidade {
		private Produto produto;
		private int QuantidadeVendida;
		
		public Produto getProduto() {
			return produto;
		}
		public void setProduto(Produto produto) {
			this.produto = produto;
		}
		public int getQuantidadeVendida() {
			return QuantidadeVendida;
		}
		public void setQuantidadeVendida(int quantidadeVendida) {
			QuantidadeVendida = quantidadeVendida;
		}
		
		public ProdutoQuantidade() {}
		
		public ProdutoQuantidade(Produto produto, int quantidadeVendida) {
			super();
			this.produto = produto;
			QuantidadeVendida = quantidadeVendida;
		}
		
		public static boolean ProdutoJaEstahNaColecao(List<ProdutoQuantidade> lista, Produto produto) {
			for(ProdutoQuantidade item: lista) {
				if(item.getProduto().getId() == produto.getId()) {
					return true;
				}
			}
			return false;
		}
		
		public static void AdicionaQuantidadeVendidaProduto(List<ProdutoQuantidade> lista, ItemVenda itemVenda) {
			for(ProdutoQuantidade item: lista) {
				if(item.getProduto().getId() == item.getProduto().getId()) {
					item.setQuantidadeVendida(item.getQuantidadeVendida()+itemVenda.getQuantidade());
					return;
				}
			}
		}
		
		public static Produto RetornarProdutoMaisVendido(List<ProdutoQuantidade> lista) {
			Produto produtoMaisVendido = new Produto();
			int quantidadeVendida = 0;
			for(ProdutoQuantidade quantidade: lista) {
				if(quantidade.getQuantidadeVendida() >= quantidadeVendida) {
					quantidadeVendida = quantidade.getQuantidadeVendida();
					produtoMaisVendido = quantidade.getProduto();
				}
			}
			return produtoMaisVendido;
		}
}
