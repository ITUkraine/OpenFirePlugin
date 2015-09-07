package ua.com.okam1;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jivesoftware.admin.AuthCheckFilter;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

@SuppressWarnings("serial")
public class AnnouncementServlet extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		// Exclude this servlet from requering the user to login
		AuthCheckFilter.addExclude("status");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set response content type
		response.setContentType("text/html");

		// New location to be redirected
		String site = new String("https://github.com/okam1/AnnouncementPlugin");

		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageBody = request.getParameter("message");

		String username = "admin";
		String password = "123123123";

		String server = "192.168.1.35";

		ConnectionConfiguration connConfig = new ConnectionConfiguration(server, 5222);
		XMPPConnection connection = new XMPPConnection(connConfig);
		try {
			connection.connect();
			connection.login(username, password); // User name and password of
													// user whose message we
													// want to get
			Message msg = new Message();
			msg.setBody(messageBody);
			msg.setFrom(username + "@pc");// !!!!!!!!!!!!!!!!!!!!!!!
			msg.setTo("krion@pc");
			connection.sendPacket(msg);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set response content type
		response.setContentType("text/html");

		// New location to be redirected
		String site = new String("/plugins/announcements/main.jsp");

		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}

	@Override
	public void destroy() {
		super.destroy();
		// Release the excluded URL
		AuthCheckFilter.removeExclude("status");
	}
}