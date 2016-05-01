package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.UserAbstraction;
import model.UserAdmin;
import model.UserNormal;
import model.UserRefinedAbstraction;
import util.HibernateUtil;

public class UserDao {
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public UserAbstraction getUserFuncById(String email){
		UserRefinedAbstraction user = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			user = (UserRefinedAbstraction) session
					.createQuery("from UserRefinedAbstraction u where u.email = :EMAIL")
					.setParameter("EMAIL", email)
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
		
		UserAbstraction uAbstraction = null;
		if(user.getActive() == 1) {
			switch(user.getRole()){
				case 1: // is Admin
					uAbstraction = new UserRefinedAbstraction(new UserAdmin());
					break;
					
				case 2: // is Normal user
					uAbstraction = new UserRefinedAbstraction(new UserNormal());
					break;
			}
		}
		
		return uAbstraction;
	}
	
	public UserRefinedAbstraction getUserById(String email){
		UserRefinedAbstraction user = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			user = (UserRefinedAbstraction) session
					.createQuery("from UserRefinedAbstraction u where u.email = :EMAIL")
					.setParameter("EMAIL", email)
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
		
		return user;
	}
	
	public List<UserRefinedAbstraction> getAllUsers(){
		List<UserRefinedAbstraction> users = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			users = session.createQuery("from UserRefinedAbstraction u").list();
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
		
		return users;
	}
}
