package br.com.marcio.model.classes;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensagem {
	
	private String mensagem;

	public Mensagem() {	}
	
	public Mensagem(String msg){
		this.mensagem = msg;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}