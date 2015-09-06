package ua.com.okam1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnouncementServlet extends HttpServlet {

	/**
	 * Auto-generated serial version ID
	 */
	private static final long serialVersionUID = 5912435534990140254L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/help.jsp").forward(request, response);
	}

}
