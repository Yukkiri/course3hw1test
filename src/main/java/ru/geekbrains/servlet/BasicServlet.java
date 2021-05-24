package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BasicServlet", urlPatterns = "/basic_servlet")
public class BasicServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(ru.geekbrains.servlet.BasicServlet.class);

    private transient ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");
        ArrayList<Product> products = getProductList();
        for (int i = 0; i < products.size(); i++) {
            res.getWriter().println(products.get(i).getId() + " | " + products.get(i).getName() + " | " + String.format("%.2f", products.get(i).getCost()));
        }
    }

    @Override
    public String getServletInfo() {
        return "BasicServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }

    public ArrayList getProductList(){
        String[] products = new String[]{"Cucumber", "Zucchini", "Pepper", "Lemon",
                "Apple", "Tomato", "Grape", "Banana",
                "Cabbage", "Onion", "Pear", "Fennel",
                "Nectarine", "Plum", "Orange", "Passion fruit"};
        ArrayList<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int name = (int)Math.floor(Math.random() * products.length);
            double price = 100 + Math.random()*3000;
            Product product = new Product(i, products[name], price);
            productList.add(product);
        }
        return productList;
    }
}