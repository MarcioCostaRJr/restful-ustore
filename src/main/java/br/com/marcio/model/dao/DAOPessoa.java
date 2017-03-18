package br.com.marcio.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marcio.model.classes.PessoaJPA;

public class DAOPessoa extends DAOAbstract{

	private EntityManager em;
	
	public DAOPessoa(EntityManager entityManager) {
		super(entityManager);
		em = entityManager;
	}
	
	public Collection<PessoaJPA> listar() throws Exception {
		try {
			String query = "SELECT p FROM PessoaJPA p";
			TypedQuery<PessoaJPA> queryTpd = this.em.createQuery(query, PessoaJPA.class);
			Collection<PessoaJPA> colecaoPessoas = queryTpd.getResultList();
			return colecaoPessoas;
		} catch(Exception e){
			throw new Exception("Erro ao listar as pessoas " + e.getMessage());
		}
	}
	
}
