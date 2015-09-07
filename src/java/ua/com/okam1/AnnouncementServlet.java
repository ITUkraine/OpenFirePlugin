package ua.com.okam1;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.jivesoftware.admin.AuthCheckFilter;
import org.jivesoftware.openfire.user.User;
import org.jivesoftware.openfire.user.UserManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

@SuppressWarnings("serial")
public class AnnouncementServlet extends HttpServlet {

	private String senderUsername;
	private String senderPassword;

	private String serverIP;
	private String serverName;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		// Exclude this servlet from requering the user to login
		AuthCheckFilter.addExclude("status");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get variables from jsp
		String messageBody = request.getParameter("message");
		senderUsername = request.getParameter("senderUsername");
		senderPassword = request.getParameter("senderPassword");
		serverIP = "192.168.1.35";
		serverName = "pc";

		ConnectionConfiguration connConfig = new ConnectionConfiguration(serverIP, 5222);
		XMPPConnection connection = new XMPPConnection(connConfig);
		try {
			connection.connect();
			// User name and password of message sender
			connection.login(senderUsername, senderPassword);

			UserManager userManager = UserManager.getInstance();
			for (User u : userManager.getUsers()) {
				Message msg = new Message();
				msg.setBody(messageBody);
				msg.setFrom(senderUsername + "@" + serverName);
				msg.setTo(u.getUsername() + "@" + serverName);
				connection.sendPacket(msg);
			}

		} catch (XMPPException e) {
			e.printStackTrace();
		}
		// New location to be redirected
		String site = new String("/plugins/announcements/main.jsp");

		response.setStatus(HttpStatus.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}

	@Override
	public void destroy() {
		super.destroy();
		// Release the excluded URL
		AuthCheckFilter.removeExclude("status");
	}

}