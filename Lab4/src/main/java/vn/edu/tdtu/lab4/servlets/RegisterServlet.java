package vn.edu.tdtu.lab4.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private HashMap<String, String> userList;

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Register Servlet!!!");
    }

    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("name", name);
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        String birthday = request.getParameter("birthday");
        request.setAttribute("birthday", birthday);
        String birthtime = request.getParameter("birthtime");
        request.setAttribute("birthtime", birthtime);
        String gender = request.getParameter("gender");
        request.setAttribute("gender", gender);
        String country = request.getParameter("country");
        request.setAttribute("country", country);
        String ides[] = request.getParameterValues("ide");
        request.setAttribute("ides", Arrays.asList(ides));
        String toeic = request.getParameter("toeic");
        request.setAttribute("toeic", toeic);
        String description = request.getParameter("description");
        request.setAttribute("description", description);
        request.getRequestDispatcher("/WEB-INF/jsp/show.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }
}