package vn.edu.tdtu.lab4.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/images")
public class ImagesServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Images Servlet!!!");
    }

    @Override
    public void destroy() {
        System.out.println("Stopping Images Servlet!!!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/images.jsp").forward(request, response);
    }

}
