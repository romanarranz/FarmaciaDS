package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ProductDao;
import model.Product;

@Path("/product")
public class ProductService {
	private ProductDao productdao = new ProductDao();
	
	@GET
	@Path("/getProductByIdXML/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Product getPharmacyByIdXML(@PathParam("id") String id){
		return productdao.getProductById(Integer.parseInt(id));
	}
	
	@GET
	@Path("/getProductByIdJSON/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getPharmacyByIdJSONL(@PathParam("id") String id){
		return productdao.getProductById(Integer.parseInt(id));
	}
	
	
}
