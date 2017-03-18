package br.com.marcio.model.dao;

import br.com.marcio.model.classes.Generica;

public interface IDAOAbstract {

	public void inserir(Object obj);
	
	public void atualizar(Object obj);
	
	public void remover(Generica generica);
	
	public Generica buscar(Generica generica);
	
}
