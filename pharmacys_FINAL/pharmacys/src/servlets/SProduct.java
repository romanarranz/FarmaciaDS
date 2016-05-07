package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnector;
import model.Category;
import model.Product;
import util.DateUtil;
import util.TextParser;
import util.UploadFile;

@WebServlet("/product")
@MultipartConfig
public class SProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private DBConnector dbc;
	private List<String> msg;
	private List<String> errors;
	private String redirect;
	
    public SProduct() {
        super();
        this.dbc = new DBConnector();
        this.msg = new ArrayList<String>();
        this.errors = new ArrayList<String>();
    }
    
    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int category, size;
		String name, laboratory, units, expirationDate, lot, description;
		SimpleDateFormat formatter;
		
		Product product;
		
		// get the input data
        name = request.getParameter("insertName");
        description = request.getParameter("insertDescr");
        laboratory = request.getParameter("insertLaboratory");
        units = request.getParameter("insertUnits"); 
        expirationDate = request.getParameter("insertExpDate");               
        size = Integer.parseInt(request.getParameter("insertSize"));
        lot = request.getParameter("insertLot");        
        category = Integer.parseInt(request.getParameter("insertCategory"));
        
        product = new Product();
		product.setName(TextParser.parseLatinToHTML(name));
		product.setDescription(TextParser.parseLatinToHTML(description));
		product.setLaboratory(TextParser.parseLatinToHTML(laboratory));
		product.setUnits(units);
		        
		// PARSE EXPIRATION_DATE
     	java.sql.Date sqlDate = DateUtil.toSQLDate(expirationDate);
     	if(sqlDate != null)
     		product.setExpirationDate(sqlDate);
     	else
     		this.errors.add("Cant insert your expiration date");
     	
     	product.setSize(size);
		product.setLot(lot);
		
		// UPLOAD IMAGE
		String resultUploadImg = UploadFile.upload(request, response, "insertImg");
		if(resultUploadImg == ""){
			this.errors.add("Cant upload your image");
		}
		else{
			this.msg.add("Image uploaded successfully");
			product.setUrlImg(resultUploadImg.replace("/Users/roman/Documents/workspace/pharmacys/WebContent/", "http://localhost:8080/pharmacys/"));
		}
		
		Category c = dbc.getCategoryById(category);		
		if(c != null){
			System.out.println(c.getName());
			product.setCategoryId(c);
		}
		else {
			this.errors.add("Cant get the category data");
		}
		
		if(!dbc.insertProduct(product)){
			this.msg.add("Product inserted successfully");
			
			//	INSERT PRODUCT TO INVENTORY TABLE
			
			/*if(!dbc.insertPharmacyProduct(pp))
				this.msg.add("Product linked to your pharmacy successfully");
			else
				this.errors.add("Error when product tried to link to your pharmacy");*/
		}
		else
			this.errors.add("The product cannot be inserted");
    }
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Limpiar los mensajes que hubiera anteriormente
		if(!this.msg.isEmpty()) 	this.msg.clear();
		if(!this.errors.isEmpty()) 	this.errors.clear();
		
		redirect = "";
		
		String submit = request.getParameter("actionProduct");
				
		switch(submit){
			case "insert":				
		    	insert(request, response);
				break;		
			case "edit":
				//edit(request, response);
				break;			
			case "delete":
				//delete(request, response);
				break;
				
			default:
				this.msg.add("Something was wrong");
				break;
		}
		this.redirect = "/pharmacys/management/product.jsp";
		request.getSession().setAttribute("msg", this.msg);
		request.getSession().setAttribute("errors", this.errors);
		response.sendRedirect(this.redirect);
	}
}
