package edu.fae.mobile.revisao.tests;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServlet;

import org.junit.Before;
import org.junit.Test;

import edu.fae.mobile.revisao.controller.BibliotecaController;
import edu.fae.mobile.revisao.dao.BibliotecaDao;
import edu.fae.mobile.revisao.models.Biblioteca;
import br.com.caelum.vraptor.util.test.MockHttpServletResponse;
import br.com.caelum.vraptor.util.test.MockResult;

public class BibliotecaControllerTest {

	private MockResult mockResult;
	private BibliotecaDao dao;
	private MockHttpServletResponse mockHttpServletResponse;
	private BibliotecaController controller;
	
	@Before
	public void setUp() {
		mockResult = new MockResult();
		dao = new BibliotecaDao();
		mockHttpServletResponse = new MockHttpServletResponse();
		
		controller = new BibliotecaController(mockResult, dao, mockHttpServletResponse);
	}
	@Test
	public void testInserir() throws Throwable {
		controller.inserir(new Biblioteca(0L, "Biblioteca Fae", "-25.438127,-49.273823"));
		int status = mockHttpServletResponse.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testAlterar() throws Throwable {
		controller.alterar(new Biblioteca(2, "Biblioteca Fae Alterada", "-25.438127,-49.273823"));
		int status = mockHttpServletResponse.getStatus();
		assertEquals(200, status);
	}

}
