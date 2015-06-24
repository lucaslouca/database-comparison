package com.lucaslouca.reader;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lucaslouca.dao.ITradeDao;
import com.lucaslouca.model.AbstractTrade;

public abstract class AbstractTradeReader {
	protected Connection connection;
	protected Map<Long, AbstractTrade> tradesMap;
	protected ITradeDao dao;

	/**
	 * Constructor
	 * 
	 * @param dao
	 *            ITradeDao for accessing the database
	 */
	public AbstractTradeReader(ITradeDao dao) {
		if (dao == null) {
			throw new IllegalArgumentException("Dao cannot be null!");
		} else {
			this.dao = dao;
			tradesMap = new HashMap<Long, AbstractTrade>();
		}
	}

	/**
	 * Read all trades from the database and add them to the trade map using its
	 * id as a key.
	 * 
	 * @param List
	 *            <Integer> ids of the trades we want to fetch from the database
	 */
	public abstract void read(List<Long> ids);

	/**
	 * Returns the AbstractTrade for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public AbstractTrade getTradeWithKey(Long key) {
		return tradesMap.get(key);
	}

}
