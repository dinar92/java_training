package ru.job4j.tracker.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.Credential;
import ru.job4j.tracker.RoleValidateService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authorization for administration functions.
 */
public class AdminFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Verifies for user's role is admin. If not admin, then redirects to
     * error page.
     * @param request - servlet request.
     * @param response - servlet response.
     * @param chain - filters chain.
     * @throws IOException - IOException.
     * @throws ServletException - ServletException.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        String adminRole = "admin";
        Credential<String, String> credential = RoleValidateService.getInstance();
        String login = (String) session.getAttribute("login");
        try {
            if (credential.isCredential(login, adminRole)) {
                chain.doFilter(request, response);
            } else {
                servletResponse.sendError(403, "You don't have permission to access on this server!");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {

    }
}
