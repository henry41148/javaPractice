package vn.edu.tdtu.lab4.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private HashMap<String, String> userList;

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Login Servlet!!!");
        System.out.println("Initializing user list....");
        userList = new HashMap<>();
        userList.put("pele", "abc123");
        userList.put("maradona", "abc123");
        userList.put("ronaldo", "abc12");
        userList.put("messi", "abc123");
        userList.put("beckham", "abc123");
    }

    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        if (userList.get(user).equals(pass))
            pw.println("Login Success...!");
        else
            pw.println("Login Failed...!");
        pw.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}