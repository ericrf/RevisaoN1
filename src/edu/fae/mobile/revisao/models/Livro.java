package edu.fae.mobile.revisao.models;

public class Livro {

	private String titulo;
	private Biblioteca biblioteca;
	private String descricao;
	private String autor;
	private boolean reservado;
	private long id;

	public Livro(){}
	public Livro(long id, String titulo, String descricao, String autor, boolean reservado, Biblioteca biblioteca) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.autor = autor;
		this.reservado = reservado;
		this.biblioteca = biblioteca;
	}
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
	
	public long getIdBiblioteca(){
		return biblioteca.getId();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
