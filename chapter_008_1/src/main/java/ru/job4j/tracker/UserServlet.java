package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Handler of add, update and delete requests from the client.
 */
public class UserServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private final Validate<User> validate = ValidateService.getInstance();
    private final ActionDispatchPattern dispatch = new ActionDispatchPattern();

    /**
     * Shows a main page with table with users and any additional options for admins.
     *
     * @param req  - a GET request from user.
     * @param resp - a response for user.
     * @throws ServletException - can throw when servlet encounters difficulty.
     * @throws IOException      - signals that an I/O exception of some sort has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String mainPage = null;
        HttpSession session = req.getSession();
        if ("admin".equals(session.getAttribute("role"))) {
            mainPage = "/WEB-INF/views/admin/index.jsp";
        } else {
            mainPage = "/WEB-INF/views/index.jsp";
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(mainPage);
        dispatcher.forward(req, resp);
    }

    /**
     * Updates the data of users storage.
     *
     * @param req  an object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an object that
     *             contains the response the servlet sends
     *             to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the POST
     *                          could not be handled
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = Action.valueOf(req.getParameter("action").toUpperCase());
        resp.setContentType("text/html");
        this.dispatch.init();
        this.dispatch.identify(action, req);
        this.doGet(req, resp);
    }

    /**
     * Dispatcher of request action.
     */
    private class ActionDispatchPattern {

        private Map<Action, Consumer<ServletRequest>> dispatch = new HashMap<>();

        Consumer<ServletRequest> add() {
            return request -> {
                try {
                    User newUser = new User();
                    newUser.setName(request.getParameter("name"));
                    newUser.setEmail(request.getParameter("email"));
                    newUser.setLogin(request.getParameter("login"));
                    newUser.setPassword(request.getParameter("password"));
                    newUser.setId(Integer.parseInt(request.getParameter("id")));
                    newUser.setCreateDate(LocalDate.parse(request.getParameter("createDate")));
                    Integer roleID = Integer.valueOf(request.getParameter("role"));
                    Validate<Role> store = RoleValidateService.getInstance();
                    Role role = store.findById(roleID);
                    newUser.setRole(role);
                    validate.add(newUser);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            };
        }

        Consumer<ServletRequest> update() {
            return request -> {
                try {
                    User newUser = new User();
                    newUser.setName(request.getParameter("name"));
                    newUser.setEmail(request.getParameter("email"));
                    newUser.setLogin(request.getParameter("login"));
                    newUser.setPassword(request.getParameter("password"));
                    newUser.setId(Integer.parseInt(request.getParameter("id")));
                    newUser.setCreateDate(LocalDate.parse(request.getParameter("createDate")));
                    Integer roleID = Integer.valueOf(request.getParameter("role"));
                    Validate<Role> store = RoleValidateService.getInstance();
                    Role role = store.findById(roleID);
                    newUser.setRole(role);
                    validate.update(newUser);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            };
        }

        Consumer<ServletRequest> delete() {
            return request -> {
                try {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    validate.delete(id);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            };
        }

        ActionDispatchPattern init() {
            this.load(Action.ADD, add());
            this.load(Action.DELETE, delete());
            this.load(Action.UPDATE, update());
            return this;
        }

        void load(Action action, Consumer<ServletRequest> handle) {
            this.dispatch.put(action, handle);
        }

        void identify(Action action, ServletRequest request) {
            this.dispatch.get(action).accept(request);
        }
    }
}
