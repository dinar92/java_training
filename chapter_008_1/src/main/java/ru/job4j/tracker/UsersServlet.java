package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * View a table with users and save and edit user data.
 */
public class UsersServlet extends HttpServlet {

    /**
     * A logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UsersServlet.class);

    /**
     * A store of users.
     */
    private final Validate<User> userStore = ValidateService.getInstance();

    /**
     * Creates a table with users.
     * @param request - a GET request from user.
     * @param response - sends a table with users.
     * @throws ServletException - can throw when servlet encounters difficulty.
     * @throws IOException - signals that an I/O exception of some sort has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
            StringBuilder stringBuilder = new StringBuilder();
            try {
                for (User user : userStore.findAll()) {
                    stringBuilder.append("<tr><td>");
                    stringBuilder.append(user.toString());
                    stringBuilder.append("</td><td><form action='");
                    stringBuilder.append(req.getContextPath());
                    stringBuilder.append("/edit' method='get'><input type='hidden' name='id' value='");
                    stringBuilder.append(user.getId());
                    stringBuilder.append("'/><input type='submit' value='Edit'/></form>");
                    stringBuilder.append("<form action='");
                    stringBuilder.append(req.getContextPath());
                    stringBuilder.append("/' method='post'><input type='hidden' name='id' value='");
                    stringBuilder.append(user.getId());
                    stringBuilder.append("'/>");
                    stringBuilder.append("<input type='hidden' name='action' value='delete' />");
                    stringBuilder.append("<input type='submit' value='Delete'/></form></td></tr>");
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            writer.append("<!DOCTYPE html>\n" +
                    "<head>\n" +
                    "<title>The list of users</title>\n" +
                    "<style>\n" +
                    "table, th, td {border: 1px solid black;}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>The list of users</h1>\n" +
                    "<table>\n" +
                    "<tr><th><b>User name</b></th><th><b>Action</b></th></tr>\n" +
                    stringBuilder.toString() +
                    "</table>\n" +
                    "<form action='" + req.getContextPath() + "/create' method='get'>" +
                    "<input type='submit' value='Create user' />" +
                    "</form>" +
                    "</body>\n" +
                    "</html>");
            writer.flush();
    }
}
