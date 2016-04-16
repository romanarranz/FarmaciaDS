package dao;

import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import model.Person;

public class PersonDao {
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public Person getPersonById(int id){
		Person person = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			person = (Person) session
					.createQuery("from Person p where p.id = :ID")
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
		
		return person;
	}
	
	public List<Person> getAllPerson(){
		List<Person> persons = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			persons = session.createQuery("from Person p").list();
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
		
		return persons;
	}
}
