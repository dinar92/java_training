package ru.job4j.tracker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * View a table with users and save and edit user data.
 */
public class UsersServlet extends HttpServlet {

    /**
     * Creates a table with users.
     * @param req - a GET request from user.
     * @param resp - sends a table with users.
     * @throws ServletException - can throw when servlet encounters difficulty.
     * @throws IOException - signals that an I/O exception of some sort has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
        dispatcher.forward(req, resp);
    }
}
