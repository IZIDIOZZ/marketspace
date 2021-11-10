package com.marketspace.data.mappings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.marketspace.domain.viewModels.ItemVendaViewModel;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	private String Nome;
	
	@Column(unique = true)
	private String CodigoBarras;
	private Float Preco;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Pessoa Fornecedor;
	
	private Date DataCadastro;
	private Date DataAtualizacao;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCodigoBarras() {
		return CodigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		CodigoBarras = codigoBarras;
	}

	public Float getPreco() {
		return Preco;
	}

	public void setPreco(Float preco) {
		Preco = preco;
	}

	public Pessoa getFornecedor() {
		return Fornecedor;
	}

	public void setFornecedor(Pessoa fornecedor) {
		Fornecedor = fornecedor;
	}

	public Date getDataCadastro() {
		return DataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		DataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return DataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		DataAtualizacao = dataAtualizacao;
	}

	public Produto() {
	}

	public Produto(int id, String nome, String codigoBarras, Float preco, Pessoa fornecedor, Date dataCadastro,
			Date dataAtualizacao) {
		super();
		Id = id;
		Nome = nome;
		CodigoBarras = codigoBarras;
		Preco = preco;
		Fornecedor = fornecedor;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
	}
	
	public Produto(String nome, String codigoBarras, Float preco, Pessoa fornecedor, Date dataCadastro,
			Date dataAtualizacao) {
		super();
		Nome = nome;
		CodigoBarras = codigoBarras;
		Preco = preco;
		Fornecedor = fornecedor;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
	}
	
	public Produto(int id, String nome, String codigoBarras, Float preco) {
		super();
		Id = id;
		Nome = nome;
		CodigoBarras = codigoBarras;
		Preco = preco;
	}
	
	public ItemVendaViewModel CreateItemVenda() {
		return new ItemVendaViewModel(
					this.getId(),
					this.getNome(),
					1,
					this.getPreco(),
					this.getCodigoBarras()
				);
	}
}
