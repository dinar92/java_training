package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for creating new roles.
 */
public class RoleCreateServlet extends HttpServlet {
    /**
     * A logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleCreateServlet.class);

    /**
     * Redirects to the role create page.
     * @param req - servlet request.
     * @param resp - servlet response.
     * @throws ServletException - ServletException.
     * @throws IOException - ServletException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = "/WEB-INF/views/admin/roleCreate.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(uri);
        dispatcher.forward(req, resp);
    }

    /**
     * Adds a new role to the store and redirects back to page.
     * @param req - servlet request.
     * @param resp - servlet response.
     * @throws ServletException - ServletException.
     * @throws IOException - ServletException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            Validate<Role> store = RoleValidateService.getInstance();
            Role role = new Role();

            role.setId(id);
            role.setName(name);
            store.add(role);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        this.doGet(req, resp);
    }
}
