package br.com.marcio.model.factory;

import javax.persistence.EntityManager;

public interface IGerenciadorFactory {

	public EntityManager conectar();
	
	public void desconectar();
	
}
