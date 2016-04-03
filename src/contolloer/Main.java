package contolloer;

import java.io.IOException;
import java.util.HashMap;

import model.StatusManage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String userName = req.getParameter("user_name");
		HashMap<String, String> userStatus = StatusManage.getUserStatus(userName);
		req.setAttribute("user_name", userName);
		req.setAttribute("role_name", userStatus.get("role"));
		if (userStatus.get("deadFlag") == "0") {
			req.setAttribute("status", "生存");
	    }
		else {
			req.setAttribute("status", "死亡");
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/main.jsp");
		rd.forward(req, res);
		
		
	}

}
