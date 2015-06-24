package com.lucaslouca.reader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lucaslouca.model.STrade;

public class STradeReader extends AbstractTradeReader {

	public STradeReader(Connection connection) {
		super(connection);
	}

	@Override
	protected void parse(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			STrade trade = new STrade();
			trade.setTechKey(resultSet.getLong("TRD_TECHKEY"));
			trade.setIsin(resultSet.getString("TRD_ISIN"));
			trade.setInstrumentName(resultSet.getString("TRD_INSTR_NAME"));

			tradesMap.put(trade.getTechKey(), trade);
		}

	}

	@Override
	protected String getSelectQuery(List<Long> ids) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM t_trd_trade WHERE trd_techkey in(");
		for (int i = 0; i < ids.size(); i++) {
			sql.append("?");
			if (i + 1 < ids.size()) {
				sql.append(",");
			}
		}
		sql.append(")");

		return sql.toString();
	}
}
