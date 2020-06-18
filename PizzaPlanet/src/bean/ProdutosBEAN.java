package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.dao.FornecedoresDAO;
import model.dao.ProdutosDAO;
import util.JSFUTIL;
import domain.FornecedoresDOMAIN;
import domain.ProdutosDOMAIN;

@ManagedBean (name = "MBProdutos")
@ViewScoped 
public class ProdutosBEAN {
		
	//COMBOFORNECEDORES
	private ArrayList<FornecedoresDOMAIN>comboFornecedores;
	
	public ArrayList<FornecedoresDOMAIN> getComboFornecedores() {
		return comboFornecedores;
	}
	
	public void setComboFornecedores(ArrayList<FornecedoresDOMAIN> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	private ProdutosDOMAIN produtosDOMAIN;
	
	public ProdutosDOMAIN getProdutosDOMAIN() {
		return produtosDOMAIN;
	}

	public void setProdutosDOMAIN(ProdutosDOMAIN produtosDOMAIN) {
		this.produtosDOMAIN = produtosDOMAIN;
	}
	
	//ITENS
	private ArrayList<ProdutosDOMAIN>itens;
	
	public ArrayList<ProdutosDOMAIN> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ProdutosDOMAIN> itens) {
		this.itens = itens;
	}
	
	//ITENSFILTRADOS
	private ArrayList<ProdutosDOMAIN>itensFiltrados;
	
	public ArrayList<ProdutosDOMAIN> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<ProdutosDOMAIN> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutosDAO dao = new ProdutosDAO();	
			ArrayList<ProdutosDOMAIN>lista = dao.listar();
			itens = new ArrayList<ProdutosDOMAIN>(lista);				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepararNovo () {
		try {
			
			produtosDOMAIN = new ProdutosDOMAIN();
			
			FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.listar();
			
		} catch (SQLException e) {
			JSFUTIL.adicionarMensagemErro("ex.getMessage()");			
			e.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			ProdutosDAO dao = new ProdutosDAO();
			dao.salvar(produtosDOMAIN);
			
			itens = dao.listar();
			
			JSFUTIL.adicionarMensagemSucesso("Produto Cadastrado com Sucesso!");

		} catch (SQLException e) {
			JSFUTIL.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
		
	public void editar() {
		try {
			ProdutosDAO dao = new ProdutosDAO();
			dao.editar(produtosDOMAIN);
			
			itens = dao.listar();
			
			JSFUTIL.adicionarMensagemSucesso("Produto Editado com Sucesso!");

		} catch (SQLException e) {
			JSFUTIL.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			ProdutosDAO dao = new ProdutosDAO();
			dao.excluir(produtosDOMAIN);
			
			itens = dao.listar();
			
			JSFUTIL.adicionarMensagemSucesso("Produto Excluído com Sucesso!");

		} catch (SQLException e) {
			JSFUTIL.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

}