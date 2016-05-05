package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DBConnector;
import model.Product;

@Path("/product")
public class ProductService {
	private DBConnector dbc = new DBConnector();
	
	@GET
	@Path("/getByIdXML/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Product getByIdXML(@PathParam("id") String id){
		return dbc.getProductById(Integer.parseInt(id));
	}
	
	@GET
	@Path("/getByIdJSON/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getByIdJSONL(@PathParam("id") String id){
		return dbc.getProductById(Integer.parseInt(id));
	}
	
	@GET
	@Path("/getAllInJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllInJSON(){
		return dbc.getAllProducts();
	}
	
	@GET
	@Path("/getAllInXML")
	@Produces(MediaType.APPLICATION_XML)
	public List<Product> getAllInXML(){
		return dbc.getAllProducts();
	}
	
	@GET
	@Path("/getLastInserted")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getLastInserted(){
		return dbc.getLastProductInserted();
	}
	
	@POST
	@Path("/insert/{category}/{name}/{lab}/{units}/{expDate}/{size}/{lot}")
	@Produces(MediaType.APPLICATION_JSON)
	public String insert(
			@PathParam("category") String cat,
			@PathParam("name") String name, 
			@PathParam("lab") String lab,
			@PathParam("units") String units,
			@PathParam("expDate") String expDate,
			@PathParam("size") int size,
			@PathParam("lot") String lot
			)
	{
		String result = "{\"status\":\"not ok\"}";
		Product product = new Product();
		product.setCategory(cat);
		product.setName(name);
		product.setDescription("");
		product.setLaboratory(lab);
		product.setUnits(units);
		
		// expiration date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = formatter.parse(expDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		product.setExpirationDate(sqlDate);
		
		product.setSize(size);
		product.setLot(lot);
		product.setUrlImg("");		
		
		if(!dbc.insertProduct(product))
			result = "{\"status\":\"ok\"}";
		
		return result;		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam("id") int id){
		String result = "{\"status\":\"not ok\"}";
		Product product = dbc.getProductById(id);
		
		if(!dbc.deleteProduct(product))
			result = "{\"status\":\"ok\"}";
		
		return result;	
	}
}
