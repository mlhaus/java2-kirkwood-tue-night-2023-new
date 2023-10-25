package com.hauschildt.calculators;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="calculatorServlet", value="/calculator")
public class CalculatorServlet extends HttpServlet {

    private static Map<String, String> results = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/calculators/calculator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // This is used to handle form requests when method="POST" is used.
        String num1 = req.getParameter("firstNum");
        String num2 = req.getParameter("secondNum");
        results.clear();
        results.put("num1", num1);
        results.put("num2", num2);
        add(num1, num2);// DONE
        req.setAttribute("results", results);
        req.getRequestDispatcher("WEB-INF/calculators/calculator.jsp").forward(req, resp);
    }

    private void add(String num1, String num2) {
        if(isANumber(num1) && isANumber(num2)) {
            BigDecimal n1 = new BigDecimal(num1);
            BigDecimal n2 = new BigDecimal(num2);
            BigDecimal sum = n1.add(n2);
            results.put("sum", String.format("%s + %s = %s", n1, n2, sum));
        }
    }

    private boolean isANumber(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch(NumberFormatException e) {
            results.put("invalidNumber", "Please input a valid number");
            return false;
        }
    }
}
