package edu.fae.mobile.revisao.models;

public class Biblioteca {
	private long id;
	private String nome;
	private String localizacao;
	
	public Biblioteca(){}
	public Biblioteca(long id, String nome, String localizacao){
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
}
