package dao;

import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Employee;
import model.Employee;

public class EmployeeDao {

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public Employee getEmployeeById(int id){
		Employee employee = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			employee = (Employee) session
					.createQuery("from Employee e where e.id = :ID")
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
		
		return employee;
	}
	
	public boolean saveEmployee(Employee employee){
		Session session = null;
		boolean hasErrors = false;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(employee);
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
