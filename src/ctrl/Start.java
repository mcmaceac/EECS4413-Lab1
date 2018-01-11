package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start", "/Startup", "/Startup/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("Hello, Got a GET request!");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter p = response.getWriter();
		p.append("Hello, World!");
		p.append("\nServed at: ").append(request.getContextPath());
		p.append("\nClient IP: ").append(request.getRemoteAddr());
		p.append("\nProtocol: ").append(request.getProtocol());
		p.append("\nMethod: ").append(request.getMethod());
		
		p.append("\nParameter: ").append(request.getParameter("param1"));
		p.append("\nURI: ").append(request.getRequestURI());
		p.append("\nContext: ").append(request.getContextPath());
		p.append("\nServlet path: ").append(request.getServletPath());
		
		if (request.getRequestURI().contains("/Startup/YorkBank")) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/Start");
		}
		
		p.append("\nContext arg: ").append(this.getServletContext().getInitParameter("Param1"));
	
		//The below code is to generate an exception to test the exception page
		//int[] ex = {1, 2, 3};
		//int genException = ex[5];
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
