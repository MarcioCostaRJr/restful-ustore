package br.com.marcio.model.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GerenciadorFactory implements IGerenciadorFactory {

	private static GerenciadorFactory gerenciadorFactory;
	private EntityManagerFactory emf;
	
	private GerenciadorFactory() {
		emf = Persistence.createEntityManagerFactory("restful");
	}
	
	public static GerenciadorFactory getFactory(){
		if(gerenciadorFactory == null){
			gerenciadorFactory = new GerenciadorFactory();
		}
		return gerenciadorFactory;
	}
	
	@Override
	public EntityManager conectar() {
		return emf.createEntityManager();
	}

	@Override
	public void desconectar() {
		emf.close();
	}

}
