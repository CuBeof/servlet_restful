package nissan.procurement.servlet;

import nissan.procurement.dao.UserDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/api/v1/auth/*")
public class RestfulAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestfulAPIServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = request.getPathInfo()!=null?request.getPathInfo():"";
		switch(pathInfo) {
		  case "/create":
			  _doGetCreate(request, response);
			  break;
		  case "/read":
			 //
			  break;
		  case "/update":
			  _doGetUpdate(request, response);
			  break;
		  default:
			  _doGetDashboard(request, response);
		}
	}

	
	private void _doGetDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		String listUser = userDao.getAllUser();
		response.getWriter().append(listUser);
	}
	
	private void _doGetCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at create: ").append(request.getPathInfo());
	}
	

	private void _doGetUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at update: ").append(request.getPathInfo());
	}
}
