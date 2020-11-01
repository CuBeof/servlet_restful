package nissan.procurement.servlet;

import nissan.procurement.dao.UserDAO;
import nissan.procurement.utils.Constant;
import nissan.procurement.utils.Authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Lay ra tk mk nguoi dung");
		String JWT = Authentication.createJWT("Hai");
		response.addHeader(Constant.HEADER_STRING, Constant.TOKEN_PREFIX + JWT);		
		response.getWriter().append("Served at: ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		
		boolean validate = new UserDAO().validate(username, password);
		String JWT = Authentication.createJWT(username);
		System.out.print(validate + " _ " + username);
		response.getWriter().append(Constant.TOKEN_PREFIX + JWT);
		//response.setStatus( 401 );
	}

}
