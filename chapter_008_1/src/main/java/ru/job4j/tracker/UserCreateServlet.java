package ru.job4j.tracker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A web page for create new user.
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * Creates a page with a form for create a new user.
     * Sends data from the form to save.
     * @param request - a GET request from user.
     * @param response - sends a form for create user.
     * @throws ServletException - can throw when servlet encounters difficulty.
     * @throws IOException - signals that an I/O exception of some sort has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.append("<!DOCTYPE html>\n" +
                "<head>\n" +
                "<title>Create new user</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Create new user</h1>\n" +
                "<form action='" + request.getContextPath() + "/' method='post'>\n" +
                "<input type='hidden' name='action' value='add' />" +
                "ID:<br />\n" +
                "<input type='text' name='id' /><br /><br />\n" +
                "Name:<br />\n" +
                "<input type='text' name='name' /><br /><br />\n" +
                "Login:<br />\n" +
                "<input type='text' name='login' /><br /><br />\n" +
                "Email:<br />\n" +
                "<input type='email' name='email' /><br /><br />\n" +
                "Create date:<br />\n" +
                "<input type='date' name='createDate' /><br /><br />\n" +
                "<input type='submit' value='CREATE' />\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        out.flush();
    }
}
