package ru.job4j.servlets;

import com.google.gson.Gson;
import ru.job4j.servlets.domain.Greeting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GreetingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Greeting greeting = new Greeting();
        greeting.setText("Nice to meet you " + name);
        String greetingJsonString = new Gson().toJson(greeting);
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        writer.println(greetingJsonString);
        writer.flush();
    }
}
