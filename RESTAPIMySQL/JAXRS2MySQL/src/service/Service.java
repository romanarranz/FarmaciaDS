package service;

import javax.ws.rs.PathParam;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.PersonDao;
import model.Person;

@Path("/service")
public class Service {
	
	private PersonDao personDao = new PersonDao();
	
	// Method which should return a single person object in XML format
	@GET
	@Path("/getPersonByIdXML/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Person getPersonByIdXML(@PathParam("id") int id){
		return personDao.getPersonById(id);
	}
	
	// Method which should return a single person object in JSON format
	@GET
	@Path("/getPersonByIdJSON/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPersonByIdJSON(@PathParam("id") int id){
		return personDao.getPersonById(id);
	}
	
	// Method which should return a list of all person object in XML format
	@GET
	@Path("/getAllPersonInXML")
	@Produces(MediaType.APPLICATION_XML)
	public List<Person> getAllPersoInXML(){
		return personDao.getAllPerson();
	}
	
	// Insert new person method - return JSON for ok or not ok response
	@GET
	@Path("/savePerson/{fullname}/{age}")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveNewPerson(@PathParam("fullname") String fullname, @PathParam("age") int age){
		Person person = new Person();
		person.setFullName(fullname);
		person.setAge(age);
		
		if(!personDao.savePerson(person)){
			return "{\"status\":\"ok\"}";
		}
		else {
			return "{\"status\":\"not ok\"}";
		}
	}
	
	// Update an already existing person - result JSON for ok or not ok response
	@GET
	@Path("/savePerson/{id}/{fullname}/{age}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updatePerson(@PathParam("id") int id, @PathParam("fullname") String fullname, @PathParam("age") int age){
		Person person = new Person();
		person.setId(id);
		person.setFullName(fullname);
		person.setAge(age);
		
		if(!personDao.savePerson(person)){
			return "{\"status\":\"ok\"}";
		}
		else {
			return "{\"status\":\"not ok\"}";
		}
	}
}
