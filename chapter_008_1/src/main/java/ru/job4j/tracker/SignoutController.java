package ru.job4j.tracker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Signout request handler.
 */
public class SignoutController extends HttpServlet {

    /**
     * Invalidates session of current user.
     * @param req - servlet request.
     * @param resp - servlet response.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher("WEB-INF/views/signin.jsp").forward(req, resp);
    }
}
