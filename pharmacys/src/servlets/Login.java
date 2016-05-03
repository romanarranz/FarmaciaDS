package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnector;
import model.UserRefinedAbstraction;
import util.SHA512;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DBConnector dbc;

    public Login() {
        super();
        this.dbc = new DBConnector();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logout  = request.getParameter("logout");
		
		if(logout.equals("yes")){
			request.getSession().invalidate();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submit = request.getParameter("action");
		
		switch(submit){
			case "login":
				String email = request.getParameter("email");
		        String password = request.getParameter("password");
		        
		        try {
		        	password = SHA512.hashText(password);
		        }
		        catch(Exception e) {
		        	e.getStackTrace();
		        }
		        
		        UserRefinedAbstraction user = dbc.getUserByEmailPassword(email,password);
		        
		        if (user != null) {
		            request.getSession().setAttribute("user", user.getName());
		            request.getSession().setAttribute("userEmail", user.getEmail());		            
		            request.getSession().setAttribute("cif", user.getCifPharmacy());
		            
		            response.sendRedirect("/pharmacys/management/pharmacy.jsp");
		        }
		        else {
		            request.getSession().setAttribute("error", "Unknown user, please try again");
		            response.sendRedirect("/pharmacys/index.jsp");
		        }
				break;
			
			case "logout":
				request.getSession().invalidate();
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				break;
				
			default:
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				break;
		}		       
    }
}
