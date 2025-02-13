package vn.edu.tdtu.lab4.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/images/image1")
public class Image1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();
        System.out.println(getServletContext().getRealPath("/"));
        FileInputStream fin = new FileInputStream(getServletContext().getRealPath("/WEB-INF/images/cat.jpeg"));
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int ch =0; ;
        while((ch=bin.read())!=-1)
        {
            bout.write(ch);
        }
        bin.close();
        fin.close();
        bout.close();
        out.close();
    }
}
