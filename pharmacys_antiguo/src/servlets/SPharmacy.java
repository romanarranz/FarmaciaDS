package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnector;
import model.Pharmacy;
import model.UserRefinedAbstraction;

@WebServlet("/pharmacy")
public class SPharmacy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DBConnector dbc;
	private List<String> msg;
	private List<String> errors;
	
    public SPharmacy() {
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
		
		String cif, name, description;
		int phoneNumber, startSchedule, endSchedule;
		Pharmacy pharmacy;
		switch(submit){
			case "insert":
				cif = request.getParameter("insertCif");
				name = request.getParameter("insertName");
				description = request.getParameter("insertDescription");
				phoneNumber = Integer.parseInt(request.getParameter("insertPhone"));
				startSchedule = Integer.parseInt(request.getParameter("insertSched"));
				endSchedule = Integer.parseInt(request.getParameter("insertEsched"));
				
				pharmacy = new Pharmacy();
				pharmacy.setCif(cif);
				pharmacy.setName(name);
				pharmacy.setPhoneNumber(phoneNumber);
				pharmacy.setDescription(description);
				pharmacy.setStartSchedule(startSchedule);
				pharmacy.setEndSchedule(endSchedule);
				
				if(!dbc.insertPharmacy(pharmacy)){
					this.msg.add("Pharamcy inserted successfully");
					request.getSession().setAttribute("cif", cif);
					
					UserRefinedAbstraction user = dbc.getUserById(request.getSession().getAttribute("userEmail").toString());
					user.setCifPharmacy(cif);
					if(!dbc.updateUser(user))
						this.msg.add("Pharamcy linked to user successfully");
					else
						this.errors.add("Pharamcy cannot be linked to the user");					
				}
				else
					this.errors.add("The Pharmacy cannot be inserted");
				
				break;
			case "edit":
				cif = request.getParameter("editCif");
				name = request.getParameter("editName");
				description = request.getParameter("editDescription");
				phoneNumber = Integer.parseInt(request.getParameter("editPhone"));
				startSchedule = Integer.parseInt(request.getParameter("editSched"));
				endSchedule = Integer.parseInt(request.getParameter("editEsched"));
				
				pharmacy = dbc.getPharmacyByCIF(cif);
				pharmacy.setName(name);
				pharmacy.setPhoneNumber(phoneNumber);
				pharmacy.setDescription(description);
				pharmacy.setStartSchedule(startSchedule);
				pharmacy.setEndSchedule(endSchedule);
				
				if(!dbc.updatePharmacy(pharmacy))
					this.msg.add("Pharamcy updated successfully");
				else
					this.errors.add("The Pharmacy cannot be updated");
				
				break;
			default:
				request.getSession().setAttribute("msg", "Something was wrong");
				break;
		}
		request.getSession().setAttribute("msg", this.msg);
		request.getSession().setAttribute("errors", this.errors);				
		response.sendRedirect("/pharmacys/management/pharmacy.jsp");		
	}
}
