package com.lucaslouca.dao;

import java.util.List;

import com.lucaslouca.model.AbstractTrade;

/**
 * Interface for an DAO.
 * 
 * @author Lucas Louca
 * 
 */
public interface ITradeDao {
	/**
	 * Find by id. Search and return the trade that has the specified id.
	 * 
	 * @param id
	 *            The id of the trade
	 * @return the found AbstractTrade instance or null if the does not exist
	 * 
	 * @author Lucas Louca
	 */
	public AbstractTrade findById(long id);

	/**
	 * Find by ids. Search and return the trades whos ids are contained in the
	 * lsit of specified ids.
	 * 
	 * @param ids
	 *            List of ids
	 * @return List of found AbstractTrade instances
	 * 
	 * @author Lucas Louca
	 */
	public List<AbstractTrade> findByIds(List<Long> ids);

}
