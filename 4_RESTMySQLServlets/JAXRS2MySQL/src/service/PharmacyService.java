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
	
}
