package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DBConnector;
import model.Pharmacy;
import model.UserRefinedAbstraction;

@Path("/user")
public class UserService {
	
	private DBConnector dbc = new DBConnector();
	
	@GET
	@Path("/getByEmailXML/{email}")
	@Produces(MediaType.APPLICATION_XML)
	public UserRefinedAbstraction getByEmailXML(@PathParam("email") String email){
		return dbc.getUserById(email);
	}
	
	@GET
	@Path("/getByEmailJSON/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserRefinedAbstraction getByEmailJSON(@PathParam("email") String email){
		System.out.println(email+" recibido");
		return dbc.getUserById(email);
	}
	
	@GET
	@Path("/getAllInXML")
	@Produces(MediaType.APPLICATION_XML)
	public List<UserRefinedAbstraction> getAllInXML(){
		return dbc.getAllUsers();
	}
	
	@GET
	@Path("/getAllInJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserRefinedAbstraction> getAllInJSON(){
		return dbc.getAllUsers();
	}
	
	@PUT
	@Path("/updatePharmacies/{email}/{cif}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updatePharmacies(@PathParam("userIde") String email, @PathParam("cif") String cif){
		String result = "{\"status\":\"not ok\"}";
		UserRefinedAbstraction u = dbc.getUserById(email);
		u.setCifPharmacy(cif);
		
		if(!dbc.updateUser(u))
			result = "{\"status\":\"ok\"}";
		
		return result;
	}
	
}
