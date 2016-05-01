package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.PharmacyDao;
import model.Pharmacy;

@Path("/pharmacy")
public class PharmacyService {

	private PharmacyDao pharmacydao = new PharmacyDao();
	
	@GET
	@Path("/getPharmacyByCIFXML/{cif}")
	@Produces(MediaType.APPLICATION_XML)
	public Pharmacy getPharmacyByIdXML(@PathParam("cif") String cif){
		return pharmacydao.getPharmacyByCIF(cif);
	}
	
	@GET
	@Path("/getPharmacyByCIFJSON/{cif}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pharmacy getPharmacyByIdJSON(@PathParam("cif") String cif){
		return pharmacydao.getPharmacyByCIF(cif);
	}
	
	@GET
	@Path("/getAllPharmaciesInXML")
	@Produces(MediaType.APPLICATION_XML)
	public List<Pharmacy> getAllPersoInXML(){
		return pharmacydao.getAllPharmacies();
	}
		
	@GET
	@Path("/getAllPharmaciesInJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pharmacy> getAllPersoInJSON(){
		return pharmacydao.getAllPharmacies();
	}
	
	// Insert new person method - return JSON for ok or not ok response
	/*@GET
	@Path("/savePharmacy/{fullname}/{age}")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveNewPerson(@PathParam("fullname") String fullname, @PathParam("age") int age){
		Pharmacy person = new Pharmacy();
		person.setFullName(fullname);
		person.setAge(age);
		
		if(!pharmacydao.savePharmacy(person)){
			return "{\"status\":\"ok\"}";
		}
		else {
			return "{\"status\":\"not ok\"}";
		}
	}
	
	// Update an already existing person - result JSON for ok or not ok response
	@GET
	@Path("/savePharmacy/{id}/{fullname}/{age}")
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
	}*/
}
