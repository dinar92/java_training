package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private final Validate<User> store = ValidateService.getInstance();

    /**
     * Creates a page with a form for update user's data.
     * Sends data from the form to save.
     * ID can't be changed.
     * @param req - a GET request from user.
     * @param resp - sends a form for update user.
     * @throws ServletException - can throw when servlet encounters difficulty.
     * @throws IOException - signals that an I/O exception of some sort has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String sessionLogin = (String) session.getAttribute("login");
        Integer id = Integer.parseInt(req.getParameter("id"));
        String login = null;
        String toPage = null;
        try {
            login = store.findById(id).getLogin();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (!session.isNew() &&
                sessionLogin != null &&
                login != null &&
                sessionLogin.equals(login)) {
            toPage = "WEB-INF/views/userUpdate.jsp";
        } else {
            toPage = "WEB-INF/views/index.jsp";
        }
        req.getRequestDispatcher(toPage).forward(req, resp);
    }
}
