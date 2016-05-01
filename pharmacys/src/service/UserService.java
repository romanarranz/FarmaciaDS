package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.UserDao;
import model.UserRefinedAbstraction;

@Path("/user")
public class UserService {
	
	private UserDao userdao = new UserDao();
	
	@GET
	@Path("/getUserByEmailXML/{email}")
	@Produces(MediaType.APPLICATION_XML)
	public UserRefinedAbstraction getUserByEmailXML(@PathParam("email") String email){
		return userdao.getUserById(email);
	}
	
	@GET
	@Path("/getUserByEmailJSON/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserRefinedAbstraction getUserByEmailJSON(@PathParam("email") String email){
		System.out.println(email+" recibido");
		return userdao.getUserById(email);
	}
	
	@GET
	@Path("/getAllUsersInXML")
	@Produces(MediaType.APPLICATION_XML)
	public List<UserRefinedAbstraction> getAllUsersInXML(){
		return userdao.getAllUsers();
	}
	
	@GET
	@Path("/getAllUsersInJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserRefinedAbstraction> getAllUsersInJSON(){
		return userdao.getAllUsers();
	}
}
