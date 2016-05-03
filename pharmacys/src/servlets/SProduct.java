package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnector;
import model.Product;

@WebServlet("/product")
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Limpiar los mensajes que hubiera anteriormente
		if(!this.msg.isEmpty()) 	this.msg.clear();
		if(!this.errors.isEmpty()) 	this.errors.clear();
		
		String submit = request.getParameter("action");
		
		int id, size;
		String urlImg, category, name, laboratory, units, expirationDate, lot, description;
		Product p;
		switch(submit){
			case "edit":
				id = Integer.parseInt(request.getParameter("editId"));
		        urlImg = request.getParameter("editImg");
		        category = request.getParameter("editCategory");
		        name = request.getParameter("editName");
		        laboratory = request.getParameter("editLaboratory");
		        units = request.getParameter("editUnits");
		        expirationDate = request.getParameter("editExpDate");
		        size = Integer.parseInt(request.getParameter("editSize"));
		        lot = request.getParameter("editLot");
		        description = request.getParameter("editDescr");
		        
		        p = dbc.getProductById(id);
		        p.setCategory(category);
		        p.setName(name);
		        p.setLaboratory(laboratory);
		        p.setUnits(units);
		        
		        // expiration date
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date date = null;
				try {
					date = formatter.parse(expirationDate);
				}
				catch (ParseException e) {
					e.printStackTrace();
				}
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				p.setExpirationDate(sqlDate);
		        
				p.setSize(size);
				p.setLot(lot);
				p.setDescription(description);
				p.setUrlImg(urlImg);
				
		        if(!dbc.updateProduct(p))
		        	this.msg.add("Product updated successfully");         	
		        else 
		        	this.errors.add("The product cannot be updated");		        
				break;
			
			case "delete":
				id = Integer.parseInt(request.getParameter("deleteId"));
				String option = request.getParameter("deleteOption");
			
				if(option.equals("yes")){
					p = dbc.getProductById(id);
				
					if(!dbc.deleteProduct(p))
						this.msg.add("Product deleted successfully");
					else
						this.errors.add("The product cannot be deleted");
				}
					
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
