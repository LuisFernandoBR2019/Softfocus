package br.com.luis.softfocus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.luis.softfocus.dbconfig.ConnectionsFactory;
import br.com.luis.softfocus.idao.IComunicacaoPerda;
import br.com.luis.softfocus.model.ComunicacaoPerda;

@Repository
public class ComunicacaoPerdaDao implements IComunicacaoPerda {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<ComunicacaoPerda> readAll() {
		List<ComunicacaoPerda> list = new ArrayList<ComunicacaoPerda>();
		try {
			connection = ConnectionsFactory.getConnection();
			String sqlQuery = "select * from comunicacao_perda;";
			preparedStatement = connection.prepareStatement(sqlQuery);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ComunicacaoPerda comunicacaoPerda = new ComunicacaoPerda();
				comunicacaoPerda.setId(resultSet.getLong("id"));
				comunicacaoPerda.setCpf(resultSet.getString("cpf"));
				comunicacaoPerda.setData_colheita(resultSet.getString("data_colheita"));
				comunicacaoPerda.setEmail(resultSet.getString("email"));
				comunicacaoPerda.setEvento_ocorrido(resultSet.getString("evento_ocorrido"));
				comunicacaoPerda.setLatitude(resultSet.getDouble("latitude"));
				comunicacaoPerda.setLongitude(resultSet.getDouble("longitude"));
				comunicacaoPerda.setNome(resultSet.getString("nome"));
				comunicacaoPerda.setTipo_lavoura(resultSet.getString("tipo_lavoura"));
				list.add(comunicacaoPerda);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}
		return list;
	}

	@Override
	public Map<String, Object> create(ComunicacaoPerda entity) {

		Map<String, Object> map = new HashMap<String, Object>();
		connection = null;
		preparedStatement = null;
		try {
			connection = ConnectionsFactory.getConnection();
			String sqlQuery = "insert into comunicacao_perda (nome, email, cpf, latitude, longitude, tipo_lavoura, data_colheita, evento_ocorrido)"
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?) returning id;";
			connection.setAutoCommit(false);

			int count = 0;
			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setString(++count, entity.getNome());
			preparedStatement.setString(++count, entity.getEmail());
			preparedStatement.setString(++count, entity.getCpf());
			preparedStatement.setDouble(++count, entity.getLatitude());
			preparedStatement.setDouble(++count, entity.getLongitude());
			preparedStatement.setString(++count, entity.getTipo_lavoura());
			preparedStatement.setString(++count, entity.getData_colheita());
			preparedStatement.setString(++count, entity.getEvento_ocorrido());

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				entity.setId(resultSet.getLong("id"));
			}
			connection.commit();
			map.put("created", entity);
			return map;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public ComunicacaoPerda readById(long id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		ComunicacaoPerda comunicacaoPerda = null;
		try {
			connection = ConnectionsFactory.getConnection();
			String sqlQuery = "select * from comunicacao_perda as CP where CP.id = ?";
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				comunicacaoPerda = new ComunicacaoPerda();
				comunicacaoPerda.setId(resultSet.getLong("id"));
				comunicacaoPerda.setCpf(resultSet.getString("cpf"));
				comunicacaoPerda.setData_colheita(resultSet.getString("data_colheita"));
				comunicacaoPerda.setEmail(resultSet.getString("email"));
				comunicacaoPerda.setEvento_ocorrido(resultSet.getString("evento_ocorrido"));
				comunicacaoPerda.setLatitude(resultSet.getDouble("latitude"));
				comunicacaoPerda.setLongitude(resultSet.getDouble("longitude"));
				comunicacaoPerda.setNome(resultSet.getString("nome"));
				comunicacaoPerda.setTipo_lavoura(resultSet.getString("tipo_lavoura"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionsFactory.close(resultSet, preparedStatement, connection);
			;
		}
		return comunicacaoPerda;
	}

	@Override
	public boolean update(ComunicacaoPerda entity) {
		connection = null;
		preparedStatement = null;
		try {
			connection = ConnectionsFactory.getConnection();

			String sqlQuery = "update comunicacao_perda	SET nome=?, email=?, cpf=?, latitude=?, longitude=?, tipo_lavoura=?, data_colheita=?, evento_ocorrido=? "
					+ "	WHERE id = ?;";
			connection.setAutoCommit(false);
			int count = 0;

			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setString(++count, entity.getNome());
			preparedStatement.setString(++count, entity.getEmail());
			preparedStatement.setString(++count, entity.getCpf());
			preparedStatement.setDouble(++count, entity.getLatitude());
			preparedStatement.setDouble(++count, entity.getLongitude());
			preparedStatement.setString(++count, entity.getTipo_lavoura());
			preparedStatement.setString(++count, entity.getData_colheita());
			preparedStatement.setString(++count, entity.getEvento_ocorrido());

			preparedStatement.setLong(++count, entity.getId());

			preparedStatement.execute();
			connection.commit();
			return true;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			ConnectionsFactory.close(preparedStatement, connection);
		}
	}

	@Override
	public boolean deleteById(Long id) {
		connection = null;
		preparedStatement = null;
		try {
			connection = ConnectionsFactory.getConnection();

			String sql = "delete from comunicacao_perda where id = ?;";

			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			preparedStatement.execute();
			connection.commit();
			return true;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;

		} finally {
			ConnectionsFactory.close(preparedStatement, connection);
		}
	}

}
