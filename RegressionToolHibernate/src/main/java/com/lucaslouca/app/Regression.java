package com.lucaslouca.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.Configuration;

import com.lucaslouca.connection.HibernateUtil;
import com.lucaslouca.dao.CTradeDaoImpl;
import com.lucaslouca.dao.STradeDaoImpl;
import com.lucaslouca.mapping.SFieldMatch;
import com.lucaslouca.model.CTrade;
import com.lucaslouca.model.STrade;
import com.lucaslouca.reader.AbstractTradeReader;
import com.lucaslouca.reader.CTradeReader;
import com.lucaslouca.reader.STradeReader;

public class Regression {
	public static void main(String[] args) {
		HibernateUtil sHibernateUtil = new HibernateUtil(new Configuration().configure("s.cfg.xml").buildSessionFactory());
		HibernateUtil cHibernateUtil = new HibernateUtil(new Configuration().configure("c.cfg.xml").buildSessionFactory());

		AbstractTradeReader cReader = new CTradeReader(new CTradeDaoImpl(cHibernateUtil));
		AbstractTradeReader sReader = new STradeReader(new STradeDaoImpl(sHibernateUtil));

		// Map that holds trade pairs that should be compared for equality.
		// Set the key to the STrade techkey and the corresponding value to the CTrade's id.
		Map<Long, Long> sKeys2CIds = new HashMap<Long, Long>();
		sKeys2CIds.put(122l, 132l);
		sKeys2CIds.put(124l, 134l);
		sKeys2CIds.put(125l, 15l);

		// Read C trades
		cReader.read(new ArrayList<Long>(sKeys2CIds.values()));

		// Read S trades
		sReader.read(new ArrayList<Long>(sKeys2CIds.keySet()));

		// Compare Trades
		boolean equal;
		STrade sTrade;
		CTrade cTrade;
		Long cKey;
		for (Long sKey : sKeys2CIds.keySet()) {
			sTrade = (STrade) sReader.getTradeWithKey(sKey);

			cKey = sKeys2CIds.get(sKey);
			cTrade = (CTrade) cReader.getTradeWithKey(cKey);

			System.out.print("Comparing: " + cTrade + " vs " + sTrade + "... ");
			equal = compareTrades(cTrade, sTrade);
			System.out.println(equal ? "match" : "no match");
		}
	}

	/**
	 * Compare a CTrade with an STrade. We search for fields with an SFieldMatch
	 * annotation in CTrade and read its name value. Then we search for the
	 * field in STrade that has that name and compare those two fields for
	 * equality.
	 * 
	 * @param cTrade
	 * @param sTrade
	 * @return true if the trades match. returns false otherwise.
	 */
	private static boolean compareTrades(CTrade cTrade, STrade sTrade) {
		boolean result = true;

		SFieldMatch sFieldMatch = null;
		C_FIELD_LOOP: for (Field cField : cTrade.getClass().getDeclaredFields()) {
			sFieldMatch = cField.getAnnotation(SFieldMatch.class);

			// Only compare CTrade fields that are annotated
			if (sFieldMatch != null) {

				// Find the corresponding field in the STrade based on the annotation's name value
				for (Field sField : sTrade.getClass().getDeclaredFields()) {
					if (sFieldMatch != null && sFieldMatch.name().equals(sField.getName())) {
						try {
							Object sValue = sField.get(sTrade);
							Object cValue = cField.get(cTrade);

							if (cValue != null && !cValue.equals(sValue)) {
								// Trades don't match. Break loop.
								result = false;
								break C_FIELD_LOOP;
							}

						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}

		return result;
	}
}
