package com.lucaslouca.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.lucaslouca.connection.HibernateUtil;
import com.lucaslouca.model.AbstractTrade;
import com.lucaslouca.model.CTrade;

/**
 * ITradeDao implementation.
 * 
 * Generally this DAO is as light as possible and exists solely to provide a
 * connection to the DB.
 * 
 * @author Lucas Louca
 * 
 */
public class CTradeDaoImpl extends AbstractDao<CTrade> implements ITradeDao {

	public CTradeDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil);
	}

	@Override
	public List<AbstractTrade> findByIds(List<Long> ids) {
		return execute(session -> session.createCriteria(CTrade.class).add(Restrictions.in("id", ids)).list());
	}

	@Override
	public AbstractTrade findById(long id) {
		return execute(session -> (CTrade) session.get(CTrade.class, id));
	}
}
