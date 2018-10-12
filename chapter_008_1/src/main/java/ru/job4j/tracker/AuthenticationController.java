package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authenticator of users.
 */
public class AuthenticationController extends HttpServlet {

    /**
     * A logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    /**
     * Redirects to signin page.
     * @param req - servlet request.
     * @param resp - servlet response.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/signin.jsp").forward(req, resp);
    }

    /**
     * Authenticates the client.
     * Sets role to the client and redirects to specified page or
     * sets error of authentication.
     * @param req - servlet request.
     * @param resp - servlet response.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String redirectTo = "WEB-INF/views/signin.jsp";
        Credential<String, String> credential = ValidateService.getInstance();
        HttpSession session = req.getSession();
        try {
            if (credential.isCredential(login, password)) {
                req.removeAttribute("error");
                credential = RoleValidateService.getInstance();
                if (credential.isCredential(login, "admin")) {
                    session.setAttribute("role", "admin");
                    redirectTo = "WEB-INF/views/admin/index.jsp";
                } else {
                    session.setAttribute("role", "user");
                    redirectTo = "WEB-INF/views/index.jsp";
                }
                    session.setAttribute("login", login);
            } else {
                req.setAttribute("error", "Incorrect login or password");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        req.getRequestDispatcher(redirectTo).forward(req, resp);
    }
}
