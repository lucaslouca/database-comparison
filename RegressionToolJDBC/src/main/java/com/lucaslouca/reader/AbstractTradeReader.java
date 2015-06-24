package com.lucaslouca.reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lucaslouca.model.AbstractTrade;

public abstract class AbstractTradeReader {
	protected Connection connection;
	protected Map<Long, AbstractTrade> tradesMap;

	/**
	 * Constructor
	 * 
	 * @param connection
	 *            Connection to the database
	 */
	public AbstractTradeReader(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("Connection cannot be null!");
		} else {
			this.connection = connection;
			tradesMap = new HashMap<Long, AbstractTrade>();
		}
	}

	/**
	 * Read all trades from the database and parse them to Java Objects
	 * 
	 * @param List
	 *            <Integer> ids of the trades we want to fetch from the database
	 */
	public void read(List<Long> ids) {
		String queryString = getSelectQuery(ids);

		PreparedStatement stm;
		try {
			stm = connection.prepareStatement(queryString);
			for (int i = 0; i < ids.size(); i++) {
				stm.setLong(i + 1, ids.get(i));
			}

			ResultSet resultSet = stm.executeQuery();

			try {
				parse(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the AbstractTrade for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public AbstractTrade getTradeWithKey(Long key) {
		return tradesMap.get(key);
	}

	/**
	 * Abstract method that parses the ResultSet and adds them to the map
	 * 
	 * @param resultSet
	 * @throws SQLException
	 */
	protected abstract void parse(ResultSet resultSet) throws SQLException;

	/**
	 * Abstract method that provides the select statement.
	 * 
	 * @param ids
	 * @return
	 */
	protected abstract String getSelectQuery(List<Long> ids);

}
