package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.dao.FornecedoresDAO;
import util.JSFUTIL;
import domain.FornecedoresDOMAIN;

@ManagedBean (name = "MBFornecedores")
@ViewScoped 
public class FornecedoresBEAN {
	

	private FornecedoresDOMAIN fornecedoresDOMAIN;
	
	public FornecedoresDOMAIN getFornecedoresDOMAIN() {
		return fornecedoresDOMAIN;
	}

	public void setFornecedoresDOMAIN(FornecedoresDOMAIN fornecedoresDOMAIN) {
		this.fornecedoresDOMAIN = fornecedoresDOMAIN;
	}
	
	//ITENS
	private ArrayList<FornecedoresDOMAIN>itens;
	
	public ArrayList<FornecedoresDOMAIN> getItens() {
		return itens;
	}

	public void setItens(ArrayList<FornecedoresDOMAIN> itens) {
		this.itens = itens;
	}
	
	//ITENSFILTRADOS
	private ArrayList<FornecedoresDOMAIN>itensFiltrados;
	
	public ArrayList<FornecedoresDOMAIN> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<FornecedoresDOMAIN> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDAO dao = new FornecedoresDAO();	
			ArrayList<FornecedoresDOMAIN>lista = dao.listar();
			itens = new ArrayList<FornecedoresDOMAIN>(lista);				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepararNovo () {
		fornecedoresDOMAIN = new FornecedoresDOMAIN();
	}
	
	public void novo() {
		try {
			FornecedoresDAO dao = new FornecedoresDAO();
			dao.salvar(fornecedoresDOMAIN);
			
			itens = dao.listar();
			
			JSFUTIL.adicionarMensagemSucesso("Fornecedor Cadastrado com Sucesso!");

		} catch (SQLException e) {
			JSFUTIL.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
		
	public void editar() {
		try {
			FornecedoresDAO dao = new FornecedoresDAO();
			dao.editar(fornecedoresDOMAIN);
			
			itens = dao.listar();
			
			JSFUTIL.adicionarMensagemSucesso("Fornecedor Editado com Sucesso!");

		} catch (SQLException e) {
			JSFUTIL.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			FornecedoresDAO dao = new FornecedoresDAO();
			dao.excluir(fornecedoresDOMAIN);
			
			itens = dao.listar();
			
			JSFUTIL.adicionarMensagemSucesso("Fornecedor Excluído com Sucesso!");

		} catch (SQLException e) {
			JSFUTIL.adicionarMensagemErro("Não é possivel excluir um fornecedor com produto associado a ele");
			e.printStackTrace();
		}
	}

}