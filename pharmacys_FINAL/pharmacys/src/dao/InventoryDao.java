package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class InventoryDao {
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@SuppressWarnings("unchecked")
	protected List<Integer> getAllProductsIdsByCif(String cif){
		List<Integer> productIds = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			productIds = session.createQuery("select i.pharmacyProduct.productId from Inventory i where i.pharmacyProduct.pharmacyId = :CIF")
					.setParameter("CIF", cif)
					.list();
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
		
		return productIds;
	}
}
