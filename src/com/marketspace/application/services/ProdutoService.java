package com.marketspace.application.services;

import com.marketspace.data.mappings.Produto;
import com.marketspace.data.repositories.ProdutoRepository;

public class ProdutoService {
	ProdutoRepository _produtoRepository;

	public ProdutoService() {
		_produtoRepository = new ProdutoRepository();
	}

	public boolean AtualizarProduto(Produto produto) {
		return _produtoRepository.AtualizarProduto(produto);
	}

	public Produto PesquisarProdutoPorId(int ProdutoId) {
		return _produtoRepository.BuscarProdutoPorId(ProdutoId);
	}

	public Produto PesquisarProdutoPorCodigoDeBarras(String codigoBarras) {
		return _produtoRepository.BuscarProdutoPorCodigoDeBarras(codigoBarras);
	}

	public boolean RemoverProduto(int ProdutoId) {
		return _produtoRepository.RemoverProduto(ProdutoId);
	}

	public boolean InserirProduto(Produto produto) {
		return _produtoRepository.InserirProduto(produto);
	}

	public boolean CodigoDeBarrasJaExiste(String codigoBarras, int codigoProduto) {
		Produto produto = _produtoRepository.BuscarProdutoPorCodigoDeBarras(codigoBarras);
		if(produto == null) return false;
		if(produto.getId() == codigoProduto) return false;
		return true;
	}
	
	public boolean CodigoDeBarrasJaExiste(String codigoBarras) {
		return !(_produtoRepository.BuscarProdutoPorCodigoDeBarras(codigoBarras) == null);
	}
	
}
