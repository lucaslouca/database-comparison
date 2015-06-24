package com.lucaslouca.reader;

import java.util.List;

import com.lucaslouca.dao.ITradeDao;
import com.lucaslouca.model.AbstractTrade;
import com.lucaslouca.model.CTrade;

public class CTradeReader extends AbstractTradeReader {

	public CTradeReader(ITradeDao dao) {
		super(dao);
	}

	@Override
	public void read(List<Long> ids) {
		List<AbstractTrade> trades = dao.findByIds(ids);

		for (AbstractTrade trade : trades) {
			CTrade calypsoTrade = (CTrade) trade;
			tradesMap.put(calypsoTrade.id, calypsoTrade);
		}

	}
}
