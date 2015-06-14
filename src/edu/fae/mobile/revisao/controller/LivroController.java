package edu.fae.mobile.revisao.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import edu.fae.mobile.revisao.dao.LivroDao;
import edu.fae.mobile.revisao.models.Livro;

@Controller
@Path("/livro")
public class LivroController implements Serializable {

	private static final long serialVersionUID = 7970380918835212542L;

	private Result result;
	private LivroDao dao;
	private HttpServletResponse response;

	
	public LivroController() {
		this(null, null, null);
	}
	
	@Inject
	public LivroController(Result result, LivroDao dao,
			HttpServletResponse response) {
		this.result = result;
		this.dao = dao;
		this.response = response;
	}

	@Post({"/",""})
	public void inserir(Livro livro) throws Throwable {
		boolean r = dao.inserir(livro);	
		response.setStatus(r ? 200 : 500);
		result.use(json()).from(r).serialize();
	}

	@Get({"/",""})
	public void buscarTodos() throws Throwable {
		List<Livro> livros = dao.buscarTodos();
		response.setStatus(200);
		result.use(json()).from(livros).include("biblioteca").serialize();
	}
	
	
	@Get({"/{id}","/{id}/"})
	public void findOneById(Long id) throws Throwable {
		Livro livro = dao.findOneById(id);
		response.setStatus(200);
		result.use(json()).from(livro).include("biblioteca").serialize();
	}
	
	
	@Get({"/reservados/","/reservados"})
	public void buscarReservados() throws Throwable {
		List<Livro> livros = dao.buscarReservados();
		response.setStatus(200);
		result.use(json()).from(livros).include("biblioteca").serialize();
	}
	
	@Put({"/{id}/reservar","/{id}/reservar/"})
	public void reservar(Long id) throws Throwable {
		boolean r = dao.reservar(id);	
		response.setStatus(r ? 200 : 500);
		result.use(json()).from(r).serialize();
	}

}
