package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.UserRefinedAbstraction;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.print("SERVLET CONTESTANDO AL GET");
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
		        
		        UserRefinedAbstraction user = userdao.getUserById(email);
		        
		        if (user != null) {
		            request.getSession().setAttribute("user", user.getName());
		            response.sendRedirect("/pharmacys/index.jsp");
		        }
		        else {
		            request.setAttribute("error", "Unknown user, please try again");
		            request.getRequestDispatcher("/pharmacys/index.jsp").forward(request, response);
		        }
				break;
			
			case "logout":
				request.getSession().invalidate();
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				break;
				
			default:
				request.getRequestDispatcher("/pharmacys/index.jsp").forward(request, response);
				break;
		}		       
    }
}
