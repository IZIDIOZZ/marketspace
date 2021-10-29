package com.marketspace.application.services;

import com.marketspace.data.mappings.Produto;
import com.marketspace.data.repositories.ProdutoRepository;

public class ProdutoService {
	ProdutoRepository _produtorepository;

	public ProdutoService() {
		_produtorepository = new ProdutoRepository();
	}

	public boolean AtualizarProduto(Produto produto) {
		try {
			_produtorepository.AtualizarProduto(produto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Produto PesquisarProdutoPorId(int ProdutoId) {
		return _produtorepository.BuscarProdutoPorId(ProdutoId);
	}

	public boolean RemoverProduto(int ProdutoId) {
		try {
			_produtorepository.RemoverProduto(ProdutoId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean InserirProduto(Produto produto) {
		try {
			_produtorepository.InserirProduto(produto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
