package service;

import javax.ws.rs.PathParam;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/emp")
public class EmployeeService {
/*
	private EmployeeDao employeeDao = new EmployeeDao();
	
	@GET
	@Path("/get/{empID}")
	@Produces(MediaType.APPLICATION_XML)
	public Employee getEmployee(@PathParam("empID") int empID){
		return employeeDao.getEmployeeById(empID);
	}
	
	/*
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Employee createEmployee(Employee employee){
		return employeeDao.getEmployeeById(empID);
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Employee updateEmployee(Employee employee){
		return employeeDao.getEmployeeById(empID);
	}
	
	@DELETE
	@Path("/delete/{empID}")
	public Response deleteEmployee(@PathParam("empID") int empID) throws URISyntaxException {
		return Response.status(200).entity("Employee with " + empID + " is deleted successfully").build();
	}
	*/
}
