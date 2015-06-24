package com.lucaslouca.reader;

import java.util.List;

import com.lucaslouca.dao.ITradeDao;
import com.lucaslouca.model.AbstractTrade;
import com.lucaslouca.model.STrade;

public class STradeReader extends AbstractTradeReader {

	public STradeReader(ITradeDao dao) {
		super(dao);
	}

	@Override
	public void read(List<Long> ids) {
		List<AbstractTrade> trades = dao.findByIds(ids);

		for (AbstractTrade trade : trades) {
			STrade spsTrade = (STrade) trade;
			tradesMap.put(spsTrade.techKey, spsTrade);
		}

	}

}
