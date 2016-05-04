package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnector;
import model.Product;
import util.TextParser;
import util.UploadFile;

@WebServlet("/product")
@MultipartConfig
public class SProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;	
    
	private DBConnector dbc;
	private List<String> msg;
	private List<String> errors;
	
    public SProduct() {
        super();
        this.dbc = new DBConnector();
        this.msg = new ArrayList<String>();
        this.errors = new ArrayList<String>();
    }
    
    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int size;
		String category, name, laboratory, units, expirationDate, lot, description;
		SimpleDateFormat formatter;
		Date date = null;
		java.sql.Date sqlDate;
		
		Product product;
		
        category = request.getParameter("insertCategory");
        name = request.getParameter("insertName");
        laboratory = request.getParameter("insertLaboratory");
        units = request.getParameter("insertUnits"); 
        expirationDate = request.getParameter("insertExpDate");               
        size = Integer.parseInt(request.getParameter("insertSize"));
        lot = request.getParameter("insertLot");
        description = request.getParameter("insertDescr");
        
        product = new Product();
        product.setCategory(TextParser.parseLatinToHTML(category));
		product.setName(TextParser.parseLatinToHTML(name));
		product.setDescription(TextParser.parseLatinToHTML(description));
		product.setLaboratory(TextParser.parseLatinToHTML(laboratory));
		product.setUnits(units);
		
        // expiration date
     	formatter = new SimpleDateFormat("dd-MM-yyyy");
     	date = null;
     	try {
     		date = formatter.parse(expirationDate);
     	}
     	catch (ParseException e) {
     		e.printStackTrace();
     	}
     	sqlDate = new java.sql.Date(date.getTime());
     	product.setExpirationDate(sqlDate);
     	
     	product.setSize(size);
		product.setLot(lot);
		
		// subir la imagen
		String resultUploadImg = UploadFile.upload(request, response, "insertImg");
		if(resultUploadImg == "")
			System.out.println("error de subida de imagen");
		else{
			System.out.println("EXITO de subida de imagen");
			product.setUrlImg(resultUploadImg.replace("/Users/roman/Documents/workspace/pharmacys/WebContent/", "http://localhost:8080/pharmacys/"));
		}
		
		product.setQueryCount(0);
		
		if(!dbc.insertProduct(product))
			this.msg.add("Product inserted successfully");
		else
			this.errors.add("The product cannot be inserted");
		
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id, size, queryCount;
		String category, name, laboratory, units, expirationDate, lot, description;
		SimpleDateFormat formatter;
		Date date = null;
		java.sql.Date sqlDate;
		
		Product product;
		
    	id = Integer.parseInt(request.getParameter("editId"));
        category = request.getParameter("editCategory");
        name = request.getParameter("editName");
        laboratory = request.getParameter("editLaboratory");
        units = request.getParameter("editUnits");
        expirationDate = request.getParameter("editExpDate");
        size = Integer.parseInt(request.getParameter("editSize"));
        lot = request.getParameter("editLot");
        description = request.getParameter("editDescr");
        queryCount = Integer.parseInt(request.getParameter("editQueryCount"));
        
        product = dbc.getProductById(id);
        product.setCategory(TextParser.parseLatinToHTML(category));
        product.setName(TextParser.parseLatinToHTML(name));
        product.setLaboratory(TextParser.parseLatinToHTML(laboratory));
        product.setUnits(units);        

        // expiration date
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		date = null;
		try {
			date = formatter.parse(expirationDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		sqlDate = new java.sql.Date(date.getTime());
		product.setExpirationDate(sqlDate);
        
		product.setSize(size);
		product.setLot(lot);
		product.setDescription(TextParser.parseLatinToHTML(description));
		
		// subir la imagen
		String resultUploadImg = UploadFile.upload(request, response, "editImg");
		if(resultUploadImg == "")
			System.out.println("error de subida de imagen");
		else{
			System.out.println("EXITO de subida de imagen");
			product.setUrlImg(resultUploadImg.replace("/Users/roman/Documents/workspace/pharmacys/WebContent/", "http://localhost:8080/pharmacys/"));
		}
		
		product.setQueryCount(queryCount);
		
        if(!dbc.updateProduct(product))
        	this.msg.add("Product updated successfully");         	
        else 
        	this.errors.add("The product cannot be updated");    	
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("deleteId"));
		String option = request.getParameter("deleteOption");
		Product product;
		
		if(option.equals("yes")){
			product = dbc.getProductById(id);
		
			if(!dbc.deleteProduct(product))
				this.msg.add("Product deleted successfully");
			else
				this.errors.add("The product cannot be deleted");
		}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=iso-8859-1");
		
		// Limpiar los mensajes que hubiera anteriormente
		if(!this.msg.isEmpty()) 	this.msg.clear();
		if(!this.errors.isEmpty()) 	this.errors.clear();
		
		String submit = request.getParameter("actionProduct");
				
		switch(submit){
			case "insert":				
		    	insert(request, response);
				break;		
			case "edit":
				edit(request, response);
				break;			
			case "delete":
				delete(request, response);
				break;
				
			default:
				this.msg.add("Something was wrong");
				break;
		}
		
		request.getSession().setAttribute("msg", this.msg);
		request.getSession().setAttribute("errors", this.errors);	
		response.sendRedirect("/pharmacys/management/product.jsp");
	}
}
