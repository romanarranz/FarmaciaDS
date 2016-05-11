package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DBConnector;
import model.Inventory;
import model.Product;

@Path("/inventory")
public class InventoryService {
	
	private DBConnector dbc = new DBConnector();
	
	@GET
	@Path("/getByIdJSON/{cif}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getByIdJSON(@PathParam("cif") String cif, @PathParam("id") String id){
		String json = "{\"status\":\"not ok\"}";
		
		Product product = dbc.getProductById(Integer.parseInt(id));
		Inventory inventory = dbc.getInventoryById(cif, Integer.parseInt(id));
			
		if(inventory != null && product != null){
			json = 
			"{"+
				"\"id\":"+product.getId()+","+				
				"\"name\":\""+product.getName()+"\","+
				"\"description\":\""+product.getDescription()+"\","+
				"\"laboratory\":\""+product.getLaboratory()+"\","+
				"\"units\":\""+product.getUnits()+"\","+
				"\"expirationDate\":\""+product.getExpirationDate()+"\","+
				"\"size\":"+product.getSize()+","+
				"\"lot\":\""+product.getLot()+"\","+
				"\"urlImg\":\""+product.getUrlImg()+"\","+
				"\"category\":"+
				"{"+
					"\"id\":"+product.getCategory().getId()+","+
					"\"name\":\""+product.getCategory().getName()+"\","+
					"\"urlImg\":\""+product.getCategory().getUrlImg()+"\""+
				"},"+
				"\"stock\":"+inventory.getStock()+","+
				"\"price\":"+inventory.getPrice()+
			"}";
		}
		return json; 
	}
	
	
	@GET
	@Path("/getTableBytes")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTableBytes(){
		String result = "{\"status\":\"not ok\"}";
		float size = dbc.getInventorySize();
		
		if(size > 0.0f)
			result = "{\"size\":\""+size+"\"}";
		
		return result;
	}
}
