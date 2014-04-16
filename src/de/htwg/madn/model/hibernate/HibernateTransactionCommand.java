package de.htwg.madn.model.hibernate;

import org.hibernate.classic.Session;

public interface HibernateTransactionCommand {
	void execute(Session session);
}
