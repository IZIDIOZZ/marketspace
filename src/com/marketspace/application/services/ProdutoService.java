package com.marketspace.application.services;

import com.marketspace.data.mappings.Produto;
import com.marketspace.data.repositories.ProdutoRepository;

public class ProdutoService {
	ProdutoRepository _produtorepository;

	public ProdutoService() {
		_produtorepository = new ProdutoRepository();
	}

	public boolean AtualizarProduto(Produto produto) {
		return _produtorepository.AtualizarProduto(produto);
	}

	public Produto PesquisarProdutoPorId(int ProdutoId) {
		return _produtorepository.BuscarProdutoPorId(ProdutoId);
	}

	public boolean RemoverProduto(int ProdutoId) {
		return _produtorepository.RemoverProduto(ProdutoId);
	}

	public boolean InserirProduto(Produto produto) {
		return _produtorepository.InserirProduto(produto);
	}
}
