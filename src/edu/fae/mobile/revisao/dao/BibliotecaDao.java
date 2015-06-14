package edu.fae.mobile.revisao.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.mysql.jdbc.Connection;

import edu.fae.mobile.revisao.models.Biblioteca;
import edu.fae.mobile.revisao.utils.DataBaseUtils;

@RequestScoped
public class BibliotecaDao {
	
	public boolean createTable() throws Throwable{
		String sql = "create table biblioteca ( idBiblioteca int not null auto_increment primary key, nome varchar(255), localizacao varchar(255));";
		
		Connection connection = DataBaseUtils.getConnection();
		Statement statement = connection.createStatement();
		int update = statement.executeUpdate(sql);
		
		statement.close();
		connection.close();
		
		return update == 0;
	}
	
	public boolean dropTable() throws Throwable{
		String sql = "drop table if exists biblioteca;";
		
		Connection connection = DataBaseUtils.getConnection();
		Statement statement = connection.createStatement();
		int update = statement.executeUpdate(sql);
		
		statement.close();
		connection.close();
		
		return update == 0;
	}
	
	public boolean inserir(Biblioteca biblioteca) throws Throwable {
		String sql = "insert into biblioteca(nome, localizacao) values (?,?);";
		
		Connection connection = DataBaseUtils.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, biblioteca.getNome());
		statement.setString(2, biblioteca.getLocalizacao());
		int update = statement.executeUpdate();
		
		statement.close();
		connection.close();

		return update == Statement.CLOSE_CURRENT_RESULT;
	}

	public boolean alterar(Biblioteca biblioteca) throws Throwable {
		String sql = "update biblioteca set nome = ?, localizacao = ? where idBiblioteca = ?";
		
		Connection connection = DataBaseUtils.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, biblioteca.getNome());
		statement.setString(2, biblioteca.getLocalizacao());
		statement.setLong(3, biblioteca.getId());
		int update = statement.executeUpdate();
		
		statement.close();
		connection.close();

		return update == Statement.CLOSE_CURRENT_RESULT;
	}

	public List<Biblioteca> findAll() throws Throwable {
		String sql = "select idBiblioteca, nome, localizacao from Biblioteca";
		Connection connection = DataBaseUtils.getConnection();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery(sql);

		List<Biblioteca> list = new ArrayList<Biblioteca>();

		while (set.next()) {
			list.add(new Biblioteca(set.getLong(1), set.getString(2), set.getString(3)));
		}
		
		set.close();
		statement.close();
		connection.close();
		return list;		
	}

}
