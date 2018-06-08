package main.com.cschool.komentarze.server.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.com.cschool.komentarze.server.model.Comment;

public class SessionManager {
	
	private final static ThreadLocal<Transaction> CURRENT_TRANSACTION = new ThreadLocal<>();
	private SessionFactory sessionFactory;
	
	public SessionManager() {
		sessionFactory = new Configuration()
				.addAnnotatedClass(Comment.class)
				.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
		        .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
		        .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/Comments")
		        .setProperty("hibernate.connection.username", "postgres")
		        .setProperty("hibernate.connection.password", "postgres")
		        .setProperty("hibernate.connection.pool_size", "5")
		        .setProperty("hibernate.show_sql", "true")
		        .setProperty("hibernate.hbm2ddl.auto", "create-drop")
		        .setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext")
		        .buildSessionFactory();
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Transaction getCurrentTransaction() {
		return CURRENT_TRANSACTION.get();
	}
	
	public Transaction beginTransaction() {
		Transaction transaction = getSession().beginTransaction();
		
		CURRENT_TRANSACTION.set(transaction);
		
		return transaction;
	}
	
	public void commitTransaction() {
		CURRENT_TRANSACTION.get()
			.commit();
		
		CURRENT_TRANSACTION.remove();
	}
	
	public void rollbackTransaction() {
		CURRENT_TRANSACTION.get()
			.rollback();
		
		CURRENT_TRANSACTION.remove();
	}
	
	public void close() {
		sessionFactory.close();
	}
}

