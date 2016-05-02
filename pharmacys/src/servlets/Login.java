package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.UserRefinedAbstraction;
import util.SHA512;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserDao userdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        this.userdao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logout  = request.getParameter("logout");
		
		if(logout.equals("yes")){
			request.getSession().invalidate();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		String somedata = "whatever";
		 out.print("\n<root>");
		 out.print("\n   <othertag>" + somedata + "</othertag>");
		 out.print("\n</root>");
	}*/
	
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
		        
		        UserRefinedAbstraction user = userdao.getUserByEmailPassword(email,password);
		        
		        if (user != null) {
		        	System.out.println("encontrado");
		            request.getSession().setAttribute("user", user.getName());
		            response.sendRedirect("/pharmacys/management/product.jsp");
		        }
		        else {
		        	System.out.println("no encontrado");
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
