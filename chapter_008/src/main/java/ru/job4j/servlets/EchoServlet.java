package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The 'Hello world' servlet.
 */
public class EchoServlet extends HttpServlet {

    /**
     * Responses 'Hello world'.
     * @param req - response.
     * @param resp - request.
     * @throws ServletException - some difficulty.
     * @throws IOException - if an input or output exception occurred.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("Hello world!");
        writer.flush();
    }
}
