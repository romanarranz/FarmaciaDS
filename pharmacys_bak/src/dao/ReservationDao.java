package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Reservation;
import util.HibernateUtil;

public class ReservationDao {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@SuppressWarnings("unchecked")
	protected List<Reservation> getAllReservationByCIF(String cif){
		List<Reservation> reservationList = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			reservationList = session.createQuery("select r from Reservation r where r.reservationPK.cif = :CIF")
					.setParameter("CIF", cif)
					.list();
			session.getTransaction().commit();
		}
		catch(HibernateException e){
			if(session != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			if(session != null)
				session.close();
		}
		
		return reservationList;
	}
}
