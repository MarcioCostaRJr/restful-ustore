package br.com.marcio.model.dao;

import javax.persistence.EntityManager;

import br.com.marcio.model.classes.Generica;

public abstract class DAOAbstract implements IDAOAbstract {

	private EntityManager em;
	
	public DAOAbstract(EntityManager entityManager) {
		em = entityManager;
	}
	
	@Override
	public void inserir(Object obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
	}

	@Override
	public void atualizar(Object obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
	}

	@Override
	public void remover(Generica generica) {
		em.getTransaction().begin();
		em.remove(generica);
		em.getTransaction().commit();
	}

	@Override
	public Generica buscar(Generica generica) {
		Generica retorno = (Generica) em.find(generica.getClass(),
				generica.getChavePrimaria());
		return retorno;
	}

}
