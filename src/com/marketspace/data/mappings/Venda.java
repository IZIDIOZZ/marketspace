package com.marketspace.data.mappings;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@OneToMany(mappedBy = "Venda", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemVenda> ItensVenda;

	private String CPF;

	private Float ValorTotal;

	private Date DataCadastro;
	private Date DataAtualizacao;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<ItemVenda> getItensVenda() {
		return ItensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		ItensVenda = itensVenda;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Float getValorTotal() {
		return ValorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		ValorTotal = valorTotal;
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

	public Venda() {
	}

	public Venda(int id, List<ItemVenda> itensVenda, String cPF, Float valorTotal, Date dataCadastro,
			Date dataAtualizacao) {
		super();
		Id = id;
		ItensVenda = itensVenda;
		CPF = cPF;
		ValorTotal = valorTotal;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
	}

	public Venda(List<ItemVenda> itensVenda, String cPF, Float valorTotal, Date dataCadastro, Date dataAtualizacao) {
		super();
		ItensVenda = itensVenda;
		CPF = cPF;
		ValorTotal = valorTotal;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
	}

	public Venda(String cPF, Float valorTotal, Date dataCadastro, Date dataAtualizacao) {
		super();
		CPF = cPF;
		ValorTotal = valorTotal;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
	}

	public Venda(List<ItemVenda> itensVenda) {
		super();
		ItensVenda = itensVenda;
		ValorTotal = 0F;
	}

	public void CalcularTotalVenda() {
		float totalVenda = 0F;
		for (ItemVenda iv : this.getItensVenda()) {
			totalVenda += (iv.getQuantidade() * iv.getProduto().getPreco());
		}
		this.setValorTotal(totalVenda);
	}

	@Override
	public String toString() {
		return "Venda [Id=" + Id + ", " + (ItensVenda != null ? "ItensVenda=" + ItensVenda + ", " : "")
				+ (CPF != null ? "CPF=" + CPF + ", " : "")
				+ (ValorTotal != null ? "ValorTotal=" + ValorTotal + ", " : "")
				+ (DataCadastro != null ? "DataCadastro=" + DataCadastro + ", " : "")
				+ (DataAtualizacao != null ? "DataAtualizacao=" + DataAtualizacao : "") + "]";
	}

	public void RemoverItemVenda(String codigoBarras) {
		for (int i = 0; i < this.getItensVenda().size(); i++) {
			if (this.getItensVenda().get(i).getProduto().getCodigoBarras().equals(codigoBarras)) {
				this.getItensVenda().remove(i);
			}
		}
		CalcularTotalVenda();
	}
}
