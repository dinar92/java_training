package ru.job4j.tracker.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authentication of users.
 */
public class AuthenticationFilter implements Filter {

    public void destroy() {

    }

    /**
     * If user is not authenticated, then redirects to signin.
     * @param req - servlet request.
     * @param resp - servlet response.
     * @param chain - filters chain.
     * @throws IOException - IOException.
     * @throws ServletException - ServletException.
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)req).getSession();
        if (session.isNew() || session.getAttribute("login") == null) {
            req.getRequestDispatcher(String.format("%s/signin", ((HttpServletRequest) req).getContextPath())).forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
