package edu.fae.mobile.revisao.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.mysql.jdbc.Connection;

import edu.fae.mobile.revisao.models.Biblioteca;
import edu.fae.mobile.revisao.models.Livro;
import edu.fae.mobile.revisao.utils.DataBaseUtils;

@RequestScoped
public class LivroDao {

	public boolean inserir(Livro livro) throws Throwable {
		String sql = "insert into livro (titulo, descricao, autor, idBiblioteca, reservado) VALUES(?,?,?,?,?);";

		Connection connection = DataBaseUtils.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, livro.getTitulo());
		statement.setString(2, livro.getDescricao());
		statement.setString(3, livro.getAutor());
		statement.setLong(4, livro.getIdBiblioteca());
		statement.setBoolean(5, false);

		int r = statement.executeUpdate();

		statement.close();
		connection.close();
		return r == 1;
	}

	public List<Livro> buscarTodos() throws Throwable {
		String sql = "select l.idLivro, l.titulo, l.descricao, l.autor, l.reservado, b.idBiblioteca, b.nome, b.localizacao from livro l inner join biblioteca b on l.idBiblioteca = b.idBiblioteca";
		Connection connection = DataBaseUtils.getConnection();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery(sql);

		List<Livro> list = new ArrayList<Livro>();

		while (set.next()) {
			list.add(new Livro(set.getLong(1), set.getString(2), set
					.getString(3), set.getString(4), set.getBoolean(5),
					new Biblioteca(set.getLong(6), set.getString(7), set
							.getString(8))));
		}
		
		set.close();
		statement.close();
		connection.close();
		return list;
	}

	public List<Livro> buscarReservados() throws Throwable {
		String sql = "select l.idLivro, l.titulo, l.descricao, l.autor, l.reservado, b.idBiblioteca, b.nome, b.localizacao from livro l inner join biblioteca b on l.idBiblioteca = b.idBiblioteca where l.reservado = true;";
		Connection connection = DataBaseUtils.getConnection();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery(sql);

		List<Livro> list = new ArrayList<Livro>();

		while (set.next()) {
			list.add(new Livro(set.getLong(1), set.getString(2), set
					.getString(3), set.getString(4), set.getBoolean(5),
					new Biblioteca(set.getLong(6), set.getString(7), set
							.getString(8))));
		}
		
		set.close();
		statement.close();
		connection.close();
		return list;
	}

	public boolean reservar(Long id) throws Throwable {
		String sql = "update livro set reservado = true where idLivro = ?";

		Connection connection = DataBaseUtils.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		int r = statement.executeUpdate();

		statement.close();
		connection.close();
		return r == 1;
	}

	public Livro findOneById(Long id) throws Throwable {
		String sql = "select l.idLivro, l.titulo, l.descricao, l.autor, l.reservado, b.idBiblioteca, b.nome, b.localizacao from livro l inner join biblioteca b on l.idBiblioteca = b.idBiblioteca where l.idLivro = ?;";
		Connection connection = DataBaseUtils.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		ResultSet set = statement.executeQuery();

		set.first();
		Livro livro = new Livro(set.getLong(1), set.getString(2), set
					.getString(3), set.getString(4), set.getBoolean(5),
					new Biblioteca(set.getLong(6), set.getString(7), set
							.getString(8)));
		
		
		set.close();
		statement.close();
		connection.close();
		return livro;
	}

}
