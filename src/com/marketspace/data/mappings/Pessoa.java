package com.marketspace.data.mappings;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.marketspace.domain.viewModels.PessoaViewModel;

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String RazaoSocial;
	private String NomeFantasia;

	@Column(unique = true)
	private String Documento;

	private Date DataCadastro;
	private Date DataAtualizacao;

	@OneToOne(mappedBy = "Pessoa", fetch = FetchType.EAGER)
	private Usuario Usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	private TipoPessoa TipoPessoa;  

	@OneToMany(mappedBy = "Fornecedor", fetch = FetchType.EAGER)
	private Set<Produto> Produtos;
	
	@OneToMany(mappedBy = "Pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Endereco> Enderecos;
	
	public List<Endereco> getEnderecos() {
		return Enderecos;
	}
	
	public void setEnderecos(List<Endereco> enderecos) {
		Enderecos = enderecos;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRazaoSocial() {
		return RazaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return NomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		NomeFantasia = nomeFantasia;
	}
	
	public String getDocumento() {
		return Documento;
	}

	public void setDocumento(String documento) {
		Documento = documento;
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

	public TipoPessoa getTipoPessoa() {
		return TipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		TipoPessoa = tipoPessoa;
	}

	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	
	public void AdicionarEndereco(Endereco endereco) {
		this.Enderecos.add(endereco);
		endereco.setPessoa(this);
	}
	
	public void RemoverEndereco(Endereco endereco) {
		this.Enderecos.remove(endereco);
		endereco.setPessoa(null);
	}
	
	public Set<Produto> getProdutos() {
		return Produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		Produtos = produtos;
	}

	public Pessoa() {
	}

	public Pessoa(String razaoSocial, String nomeFantasia, String documento, Date dataCadastro, Date dataAtualizacao,
			Usuario usuario, TipoPessoa tipoPessoa, List<Endereco> enderecos) {
		super();
		RazaoSocial = razaoSocial;
		NomeFantasia = nomeFantasia;
		Documento = documento;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Usuario = usuario;
		TipoPessoa = tipoPessoa;
		Enderecos = enderecos;
	}

	public Pessoa(String razaoSocial, String nomeFantasia, String documento, Date dataCadastro, Date dataAtualizacao,
			TipoPessoa tipoPessoa) {
		super();
		RazaoSocial = razaoSocial;
		NomeFantasia = nomeFantasia;
		Documento = documento;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		TipoPessoa = tipoPessoa;
	}
	
	

	public Pessoa(int id, String razaoSocial, String nomeFantasia, String documento, Date dataCadastro,
			Date dataAtualizacao,Usuario usuario, TipoPessoa tipoPessoa, List<Endereco> enderecos, Set<Produto> produtos) {
		super();
		Id = id;
		RazaoSocial = razaoSocial;
		NomeFantasia = nomeFantasia;
		Documento = documento;
		DataCadastro = dataCadastro;
		DataAtualizacao = dataAtualizacao;
		Usuario = usuario;
		TipoPessoa = tipoPessoa;
		Enderecos = enderecos;
		Produtos = produtos;
	}

	public PessoaViewModel ConverterTo() {
		return new PessoaViewModel(
				this.getId(), 
				this.getRazaoSocial(), 
				this.getNomeFantasia(), 
				this.getDocumento(),
				this.getTipoPessoa().getTipoPessoa(), 
				false);
	}
	
	@Override
	public String toString() {
		return "Pessoa [Id=" + Id + ", RazaoSocial=" + RazaoSocial + ", NomeFantasia=" + NomeFantasia + ", Documento="
				+ Documento + ", DataCadastro=" + DataCadastro + ", DataAtualizacao=" + DataAtualizacao + ", Usuario="
				+ Usuario + ", TipoPessoa=" + TipoPessoa + ", Enderecos=" + "]";
	}

	
	
}
