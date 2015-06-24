package com.lucaslouca.dao;

import java.util.function.Function;

import org.hibernate.Session;

import com.lucaslouca.connection.HibernateUtil;

public class AbstractDao<E> {
	private HibernateUtil hibernateUtil;

	public AbstractDao(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	<R> R execute(final Function<Session, R> func) {
		R result = null;

		Session session = hibernateUtil.openSession();
		try {
			session.beginTransaction();

			result = func.apply(session);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return result;
	}
}
