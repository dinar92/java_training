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
 * A web page for update an exist user.
 */
public class UserUpdateServlet extends HttpServlet {

    /**
     * A logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserUpdateServlet.class);

    /**
     * A store of users.
     */
    private final Validate store = ValidateService.getInstance();

    /**
     * Creates a page with a form for update user's data.
     * Sends data from the form to save.
     * ID can't be changed.
     * @param request - a GET request from user.
     * @param response - sends a form for update user.
     * @throws ServletException - can throw when servlet encounters difficulty.
     * @throws IOException - signals that an I/O exception of some sort has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));
        User user = null;
        try {
            user = this.store.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<head>\n" +
                "<title>Create new user</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Edit user's data</h1>\n" +
                "<form action='" + req.getContextPath() + "/' method='post'>\n" +
                "<input type='hidden' name='action' value='update' />" +
                "ID:<br />\n" +
                "<input type='text' name='id' value='" + user.getId() + "' readonly /><br /><br />\n" +
                "Name:<br />\n" +
                "<input type='text' name='name' value='" + user.getName() + "' /><br /><br />\n" +
                "Login:<br />\n" +
                "<input type='text' name='login' value='" + user.getLogin() + "' /><br /><br />\n" +
                "Email:<br />\n" +
                "<input type='email' name='email' value='" + user.getEmail() + "' /><br /><br />\n" +
                "Create date:<br />\n" +
                "<input type='date' name='createDate' value='" + user.getCreateDate() + "' /><br /><br />\n" +
                "<input type='submit' value='EDIT' />\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
        out.flush();
    }
}
