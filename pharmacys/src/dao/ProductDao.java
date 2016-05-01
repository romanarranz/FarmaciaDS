package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Product;
import util.HibernateUtil;

public class ProductDao {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public Product getProductById(int id){
		Product product = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			product = (Product) session
					.createQuery("from Product p where p.id = :ID")
					.setParameter("ID", id)
					.uniqueResult();
			session.getTransaction().commit();
		}
		catch(Exception e){
			if(session != null)
				session.getTransaction().rollback();
		}
		finally {
			if(session != null)
				session.close();
		}
		
		return product;
	}
	
	public boolean saveProduct(Product p){
		Session session = null;
		boolean hasErrors = false;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(p);
			session.getTransaction().commit();
		}
		catch(Exception e){
			if(session != null)
				session.getTransaction().rollback();
			
			hasErrors = true;
		}
		finally {
			if(session != null)
				session.close();
		}
		
		return hasErrors;
	}
}
