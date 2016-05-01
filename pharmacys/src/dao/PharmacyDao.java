package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Pharmacy;
import util.HibernateUtil;

public class PharmacyDao {
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public Pharmacy getPharmacyByCIF(String cif){
		Pharmacy pharmacy = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			pharmacy = (Pharmacy) session
					.createQuery("from Pharmacy p where p.cif = :CIF")
					.setParameter("CIF", cif)
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
		
		return pharmacy;
	}
	
	public List<Pharmacy> getAllPharmacies(){
		List<Pharmacy> pharmacies = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			pharmacies = session.createQuery("from Pharmacy p").list();
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
		
		return pharmacies;
	}
}
