package edu.fae.mobile.revisao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockHttpServletResponse;
import br.com.caelum.vraptor.util.test.MockResult;
import edu.fae.mobile.revisao.controller.BibliotecaController;
import edu.fae.mobile.revisao.controller.LivroController;
import edu.fae.mobile.revisao.dao.BibliotecaDao;
import edu.fae.mobile.revisao.dao.LivroDao;
import edu.fae.mobile.revisao.models.Biblioteca;
import edu.fae.mobile.revisao.models.Livro;

public class RevisaoN1Test {

	private MockResult result;
	private LivroDao dao;
	private MockHttpServletResponse response;
	private LivroController controller;
	
	@Before
	public void setUp() {
		result = new MockResult();
		dao = new LivroDao();
		response = new MockHttpServletResponse();
		controller = new LivroController(result, dao, response);
	}
	
	
	
	@Test
	public void testInserirLivro() throws Throwable {
		controller.inserir(new Livro(0L, "titulo","descricao","autor", false, new Biblioteca(1L, null, null)));
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testBuscarTodos() throws Throwable{
		controller.buscarTodos();
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testBuscarReservados() throws Throwable{
		controller.buscarReservados();
		assertEquals(200, response.getStatus());
	}

	@Test
	public void testReservar() throws Throwable{
		controller.reservar(1L);
		assertEquals(200, response.getStatus());
	}
}
