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
import edu.fae.mobile.revisao.dao.BibliotecaDao;
import edu.fae.mobile.revisao.models.Biblioteca;

@Controller
@Path("/biblioteca")
public class BibliotecaController implements Serializable {
	private static final long serialVersionUID = 6460105126424549642L;

	private Result result;
	private BibliotecaDao dao;
    private HttpServletResponse response;
	
    
    public BibliotecaController() {
		this(null, null, null);
	}
    
    
	@Inject
	public BibliotecaController(Result result, BibliotecaDao dao, HttpServletResponse response) {
		this.result = result;
		this.dao = dao;
		this.response = response;
	}

	@Get({"/"})
	public void findAll() throws Throwable {
		List<Biblioteca> list = dao.findAll();
		result.use(json()).from(list).serialize();
	}
	
	@Post({"/"})
	public void inserir(Biblioteca biblioteca) throws Throwable{
		boolean r = dao.inserir(biblioteca);
		response.setStatus(r ? 200 : 500);
		result.use(json()).from(r).serialize();
	}
	
	@Put({"/"})
	public void alterar(Biblioteca biblioteca) throws Throwable{
		boolean r = dao.alterar(biblioteca);
		response.setStatus(r ? 200 : 500);
		result.use(json()).from(r).serialize();
	}

}
