package domain;

public class ProdutosDOMAIN {
	
	private int codigo;
	private String descricao;
	private Double valor;
	private Long quantidade;
	private FornecedoresDOMAIN fornecedor = new FornecedoresDOMAIN();


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public FornecedoresDOMAIN getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedoresDOMAIN fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		String saida = codigo + " - " + descricao + " - " + valor + " - " + quantidade + " - " + fornecedor.getDescricao();
		return saida;
	}
	
}
