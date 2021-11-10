package com.marketspace.data.mappings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.marketspace.domain.entities.ProdutoQuantidade;
import com.marketspace.domain.viewModels.ItemVendaViewModel;
import com.marketspace.domain.viewModels.RelatorioAnaliticoViewModel;

@Entity
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private int Quantidade;
	private Date DataCadastro;
	private Date DataAtualizacao;

	private int ProdutoId;
	private String Nome;
	private String CodigoBarras;

	@ManyToOne(fetch = FetchType.EAGER)
	private Venda Venda;

	private Float Valor;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Venda getVenda() {
		return Venda;
	}

	public void setVenda(Venda venda) {
		Venda = venda;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
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

	public Float getValor() {
		return Valor;
	}

	public void setValor(Float valor) {
		Valor = valor;
	}

	public ItemVenda() {
	}

	public int getProdutoId() {
		return ProdutoId;
	}

	public void setProdutoId(int produtoId) {
		ProdutoId = produtoId;
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

	public ItemVenda(int id, int produtoId, Venda venda, int quantidade, Date dataCadastro, Date dataAtualizacao,
			Float valor, String nome, String codigoBarras) {
		super();
		Id = id;
		ProdutoId = produtoId;
		Venda = venda;
		Quantidade = quantidade;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Valor = valor;
		Nome = nome;
		CodigoBarras = codigoBarras;

	}

	public ItemVenda(int produtoId, Venda venda, int quantidade, Date dataCadastro, Date dataAtualizacao, Float valor,
			String nome, String codigoBarras) {
		super();
		ProdutoId = produtoId;
		Venda = venda;
		Quantidade = quantidade;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Valor = valor;
		Nome = nome;
		CodigoBarras = codigoBarras;
	}

	public ItemVenda(int produtoId, int quantidade, Date dataCadastro, Date dataAtualizacao, Float valor, String nome,
			String codigoBarras) {
		super();
		Venda = null;
		ProdutoId = produtoId;
		Quantidade = quantidade;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Valor = valor;
		Nome = nome;
		CodigoBarras = codigoBarras;
	}

	public void AdicionarUmaUnidadeAoItem() {
		this.setQuantidade(this.getQuantidade() + 1);
		this.getVenda().CalcularTotalVenda();
	}

	public void RemoverUmaUnidadeAoItem() {
		this.setQuantidade(this.getQuantidade() - 1);
		this.getVenda().CalcularTotalVenda();
	}

	public ItemVendaViewModel ConvertTo() {
		return new ItemVendaViewModel(this.getProdutoId(), this.getNome(), this.getQuantidade(), this.getValor(),
				this.getCodigoBarras());
	}

	public RelatorioAnaliticoViewModel ConvertToRelatorioAnaliticoViewModel() {
		return new RelatorioAnaliticoViewModel(this.getVenda().getId(), this.getProdutoId(), this.getQuantidade(),
				this.getValor(), this.getNome());
	}

	public ProdutoQuantidade ToProdutoQuantidade() {
		return new ProdutoQuantidade(
				new Produto(this.getProdutoId(), this.getNome(), this.getCodigoBarras(), this.getValor()),
				this.getQuantidade());
	}
}
