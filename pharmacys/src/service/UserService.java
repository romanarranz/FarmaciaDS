package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DBConnector;
import model.Product;
import model.UserRefinedAbstraction;
import util.DateUtil;
import util.SHA512;
import util.SendEmailUsingGMAILSMTP;

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
	
	@GET
	@Path("/resetPassword/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String resetPassword(@PathParam("email") String email){
		String result = "{\"status\":\"not ok\"}";						
		
		String currentDate = DateUtil.getCurrentDateTime();
		String resetHash = "";
		try {
        	resetHash = SHA512.hashText(currentDate);
        }
        catch(Exception e) {
        	e.getStackTrace();
        }
		
		resetHash = resetHash.substring(0, 20);
		
		if(resetHash != null && !resetHash.equals(null) && resetHash != ""){
			UserRefinedAbstraction user = dbc.getUserById(email);
			
			if(user != null){
				user.setResetHash(resetHash);
				
				if(!dbc.updateUser(user)){
					System.out.println(email+" requested to reset his password");
					
					SendEmailUsingGMAILSMTP smtp = new SendEmailUsingGMAILSMTP();
					
					String link = "http://localhost:8080/pharmacys/login?action=resetPassword&hash="+resetHash;
					String msgContent = "Please clic on the next link to reset your password: "+link;
					smtp.setContent(msgContent);
					smtp.setRecipient(email);
					smtp.send();
					
					result = "{\"status\":\"ok\"}";
				}
				else
					System.out.println(email+" requested to reset his password but failed");
			}			
		}
		
		return result;	
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
