package vn.edu.tdtu.lab4.servlets;

import vn.edu.tdtu.lab4.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import vn.edu.tdtu.lab4.model.ResponseDTO;

@WebServlet("/ProductService/*")
public class ProductServlet extends HttpServlet {
    private Gson _gson = null;
    private Map<Long, Product> productList;
    @Override
    public void init() {
        this._gson = new Gson();
        this.productList = new HashMap<Long, Product>();
        productList.put(1L, new Product(1L, "Iphone", 1000.0));
        productList.put(2L, new Product(2L, "Galaxy", 800.0));
        productList.put(3L, new Product(3L, "Xiaomi", 500.0));
    }

    private void sendAsJson(
            HttpServletResponse response,
            Object obj) throws IOException {

        response.setContentType("application/json");

        String res = _gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("id");
        ResponseDTO responseDTO;
        if(key == null){
            responseDTO = new ResponseDTO(0, "All the products", this.productList);
            sendAsJson(response, responseDTO);
            return;
        }

        Long productId = Long.valueOf(key);
        if(!this.productList.containsKey(productId)) {
            responseDTO = new ResponseDTO(HttpServletResponse.SC_NOT_FOUND, "The product is not found", null);
            sendAsJson(response, responseDTO);
            return;
        }
        responseDTO = new ResponseDTO(0, "Found the product!", productList.get(productId));
        sendAsJson(response, responseDTO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = this._gson.fromJson(request.getReader(), Product.class);
        this.productList.put(product.getId(), product);
        ResponseDTO responseDTO = new ResponseDTO(0, "The product is added", product);
        sendAsJson(response, responseDTO);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = this._gson.fromJson(request.getReader(), Product.class);
        ResponseDTO responseDTO;
        if (product.getId() == null) {
            responseDTO = new ResponseDTO(404, "The product is not found!", null);
            sendAsJson(response, responseDTO);
            return;
        }
        this.productList.put(product.getId(), product);
        responseDTO = new ResponseDTO(0, "The product is updated", product);
        sendAsJson(response, responseDTO);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = this._gson.fromJson(request.getReader(), Product.class);
        ResponseDTO responseDTO;
        if (product.getId() == null || !this.productList.containsKey(product.getId())) {
            responseDTO = new ResponseDTO(404, "The product is not found!", null);
            sendAsJson(response, responseDTO);
            return;
        }
        this.productList.remove(product.getId());
        responseDTO = new ResponseDTO(0, "The product is deleted", null);
        sendAsJson(response, responseDTO);
    }
}
