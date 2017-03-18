package br.com.marcio.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.marcio.model.classes.Mensagem;
import br.com.marcio.model.classes.Pessoa;
import br.com.marcio.model.classes.PessoaJPA;
import br.com.marcio.model.dao.DAOPessoa;
import br.com.marcio.model.factory.GerenciadorFactory;

@Path("/servico")
public class Servico {

	private final DAOPessoa daoPessoa = new DAOPessoa(GerenciadorFactory.getFactory().conectar());
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public Mensagem cadastrar(Pessoa pessoa){
		
		PessoaJPA pJPA = new PessoaJPA();
		
		try{
			pJPA.setNome(pessoa.getNome());
			pJPA.setEmail(pessoa.getEmail());
			pJPA.setTelefone(pessoa.getTelefone());
			pJPA.setSenha(pessoa.getSenha());
			
			daoPessoa.inserir(pJPA);
			
			return new Mensagem("Registro inserido com sucesso");
		}catch (Exception e){
			return new Mensagem("Erro ao inserir: " + e.getMessage());
		}
	}
	
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/atualizar")
	public Mensagem atualizar(Pessoa pessoa){
		
		PessoaJPA pJPA = new PessoaJPA();
		
		try{
			pJPA.setId(pessoa.getId());
			pJPA.setNome(pessoa.getNome());
			pJPA.setEmail(pessoa.getEmail());
			pJPA.setTelefone(pessoa.getTelefone());
			pJPA.setSenha(pessoa.getSenha());
			
			daoPessoa.atualizar(pJPA);
			
			return new Mensagem("Registro atualizado com sucesso");
		}catch (Exception e){
			return new Mensagem("Erro ao atualizar: " + e.getMessage());
		}
	}
	
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{id}")
	public Mensagem remover(@PathParam("id") Integer id){
		
		PessoaJPA pJPA = new PessoaJPA();
		pJPA.setId(id);
		Mensagem msg = new Mensagem();
		try{
			
			PessoaJPA pRemover = (PessoaJPA) daoPessoa.buscar(pJPA);
			
			if (pRemover != null){
				daoPessoa.remover(pRemover);
				msg.setMensagem("Registro removido com sucesso"); 
			}
			else {
				msg.setMensagem("Registro não encontrado, certifique-se");
			}
			
			return msg;
		}catch (Exception e){
			msg.setMensagem("Erro ao remover: " + e.getMessage());
			return msg;
		}
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/usuarios")
	public Collection<Pessoa> listar(){
		
		Collection<PessoaJPA> colecaoJPA;
		Collection<Pessoa> retorno = new ArrayList<>();
		
		try{
			colecaoJPA = daoPessoa.listar();
			
			if(colecaoJPA != null){
				for (PessoaJPA pJPA : colecaoJPA){
					retorno.add(new Pessoa(pJPA.getId(), 
							pJPA.getNome(), pJPA.getEmail(), pJPA.getTelefone() == null ? "" : pJPA.getTelefone(),
							pJPA.getSenha()));
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return retorno;
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getPessoa/{id}")
	public Pessoa buscar(@PathParam ("id") Integer id){
		
		Pessoa retorno = null;
		PessoaJPA pessoaJPA = new PessoaJPA();
		
		try{
			pessoaJPA.setId(id);
			PessoaJPA ret = (PessoaJPA) daoPessoa.buscar(pessoaJPA);
			
			retorno = new Pessoa(ret.getId(), ret.getNome(), 
					ret.getEmail(), ret.getTelefone(), ret.getSenha());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return retorno;
	}
}
