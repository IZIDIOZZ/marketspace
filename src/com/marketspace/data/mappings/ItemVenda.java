package com.marketspace.data.mappings;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.marketspace.domain.viewModels.ItemVendaViewModel;

@Entity
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private int Quantidade;
	private Date DataCadastro;
	private Date DataAtualizacao;

	@OneToOne(fetch = FetchType.EAGER)
	private Produto Produto;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Venda Venda;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
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

	public ItemVenda() {
	}

	public ItemVenda(int id, Produto produto, Venda venda, int quantidade, Date dataCadastro, Date dataAtualizacao) {
		super();
		Id = id;
		Produto = produto;
		Venda = venda;
		Quantidade = quantidade;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
	}

	public ItemVenda(Produto produto, Venda venda, int quantidade, Date dataCadastro, Date dataAtualizacao) {
		super();
		Produto = produto;
		Venda = venda;
		Quantidade = quantidade;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
	}

	public ItemVenda(Produto produto, int quantidade, Date dataCadastro, Date dataAtualizacao) {
		super();
		Venda = null;
		Produto = produto;
		Quantidade = quantidade;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
	}

	@Override
	public String toString() {
		return "ItemVenda [Id=" + Id + ", " + (Produto != null ? "Produto=" + Produto + ", " : "")
				+ (Venda != null ? "Venda=" + Venda + ", " : "") + "Quantidade=" + Quantidade + ", "
				+ (DataCadastro != null ? "DataCadastro=" + DataCadastro + ", " : "")
				+ (DataAtualizacao != null ? "DataAtualizacao=" + DataAtualizacao : "") + "]";
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
		return new ItemVendaViewModel(this.getId(), this.getProduto().getNome(), this.getQuantidade(),
				this.getProduto().getPreco(), this.getProduto().getCodigoBarras());
	}

	
}
