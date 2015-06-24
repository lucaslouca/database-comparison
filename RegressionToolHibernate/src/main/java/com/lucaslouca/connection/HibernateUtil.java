package com.lucaslouca.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	private SessionFactory sessionFactory;

	public HibernateUtil(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session openSession() {
		return sessionFactory.openSession();
	}
}
