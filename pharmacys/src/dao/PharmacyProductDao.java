package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.PharmacyProduct;
import util.HibernateUtil;

public class PharmacyProductDao {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	protected PharmacyProduct getById(int id){
		PharmacyProduct p = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			p = (PharmacyProduct) session
					.createQuery("from PharmacyProduct p where p.id = :ID")
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
		
		return p;
	}
	
	@SuppressWarnings("unchecked")
	protected List<PharmacyProduct> getTopProducts(int n, String cif){
		List<PharmacyProduct> pp = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			pp = session.createQuery("from PharmacyProduct p where p.pharmacyId = :CIF order by p.queryCount desc")
					.setParameter("CIF", cif)
					.setMaxResults(n)
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
		
		return pp;
	}
	
	@SuppressWarnings("unchecked")
	protected List<PharmacyProduct> getAllProductsByPharmacy(String cif){
		List<PharmacyProduct> pp = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			pp = session.createQuery("from PharmacyProduct p where p.pharmacyId = :CIF")
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
		
		return pp;
	}
	
	protected PharmacyProduct getByPharmacyProduct(String pharmacyId, int productId){
		PharmacyProduct p = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			p = (PharmacyProduct) session
					.createQuery("from PharmacyProduct p where p.pharmacyId = :id1 and p.productId = :id2")
					.setParameter("id1", pharmacyId)
					.setParameter("id2", productId)
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
		
		return p;
	}
	
	protected boolean insertPharmacyProduct(PharmacyProduct p){
		Session session = null;
		boolean hasErrors = false;				
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
		}
		catch (Exception e){
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
	
	protected boolean updatePharmacyProduct(PharmacyProduct p){
		Session session = null;
		boolean hasErrors = false;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(p);
			session.getTransaction().commit();
		}
		catch (Exception e){
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
	
	protected boolean deletePharmacyProduct(PharmacyProduct p){
		Session session = null;
		boolean hasErrors = false;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(p);
			session.getTransaction().commit();
		}
		catch (Exception e){
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
