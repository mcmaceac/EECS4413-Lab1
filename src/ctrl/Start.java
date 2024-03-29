package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

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
	
	//below variables are used so the client can recalculate payments with differing interest rates
	private double savedPrincipal, savedPeriod;
	
	private boolean varsInitialized = false;
       
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
		
		//System.out.println("doGet called!");
		//OSAP parameters
		double principal, period, interest;
		
		//initialize the parameters to their default values from the context-params
		if (varsInitialized) {
			principal = savedPrincipal;
			period = savedPeriod;
		}
		else {
			principal = Double.parseDouble(getServletContext().getInitParameter("principal"));
			period = Double.parseDouble(getServletContext().getInitParameter("period"));
		}
		interest = Double.parseDouble(getServletContext().getInitParameter("interest"));
		
		varsInitialized = true;
		
		principal = (request.getParameter("principal") == null) ? principal : Double.parseDouble(request.getParameter("principal"));
		period = (request.getParameter("period") == null) ? period : Double.parseDouble(request.getParameter("period"));
		interest = (request.getParameter("interest") == null) ? interest : Double.parseDouble(request.getParameter("interest"));

		savedPrincipal = principal;
		savedPeriod = period;
		
		PrintWriter p = response.getWriter();
		
		//p.append("Principal: ").append(request.getParameter("principal"));
		p.append("\nHello, World!");
		//p.append("\nServed at: ").append(request.getContextPath());
		p.append("\nClient IP: ").append(request.getRemoteAddr());
		p.append("\nClient Port: ").append("" + request.getRemotePort());
		
		//this is where you could filter ips 
		//if (request.getRemoteAddr() == someAddress) {
		//	abort();
		//}
		p.append("\nThis IP has been flagged!");
		p.append("\nClient Protocol: ").append(request.getProtocol());
		p.append("\nClient Method: ").append(request.getMethod());
		
		p.append("\nQuery String: ").append(request.getQueryString());
		p.append("\nQuery Param foo=").append(request.getParameter("foo"));
		p.append("\nRequest URI : ").append(request.getRequestURI());
		//p.append("\nContext: ").append(request.getContextPath());
		p.append("\nRequest Servlet path : ").append(this.getServletContext().getContextPath());
		
		if (request.getRequestURI().contains("/Startup/YorkBank")) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/Start");
		}
		
		//p.append("\nContext arg: ").append(this.getServletContext().getInitParameter("Param1"));
		
		p.append("\n---- Monthly Payments ----");
		p.append("\nBased on Principal=" + principal + " Period=" + period + 
				" Interest=" + interest);
		
		double monthlyInt = (interest / 12.0) * .01;
		//double monthlyPayments = principal * Math.pow(1+monthlyInt, period) * (monthlyInt)
		//		/ (Math.pow(1+monthlyInt, period) - 1);
		
		double monthlyPayments = monthlyInt * principal / (1 - Math.pow(1+monthlyInt, -period));
		
		DecimalFormat d = new DecimalFormat("##.0");
		p.append("\nMonthly payments: " + d.format(monthlyPayments));
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
