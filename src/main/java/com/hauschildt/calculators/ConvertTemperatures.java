package com.hauschildt.calculators;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="convertTemperatures", urlPatterns = {"/convert-temps", "/convert-temp"})
public class ConvertTemperatures extends HttpServlet {

    private static Map<String, String> results = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/calculators/temp-converter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String conversion = req.getParameter("conversion");
        String temperature = req.getParameter("temperature");
        results.clear();
        results.put("conversion", conversion);
        results.put("temperature", temperature);
        convertTemp(conversion, temperature);
        req.setAttribute("results", results);
        req.getRequestDispatcher("WEB-INF/calculators/temp-converter.jsp").forward(req, resp);
    }

    private void convertTemp(String conversion, String temperature) {
        if(conversion == null || (!conversion.equals("FtoC") && !conversion.equals("CtoF"))) {
            results.put("conversionError", "Select a conversion type");
        }
        if (!isANumber(temperature)) {
            results.put("temperatureError", "Please input a valid temperature");
        }
        if(!results.containsKey("conversionError") && !results.containsKey("temperatureError")){
            double temperatureD = Double.parseDouble(temperature);
            if (conversion.equals("FtoC")) {
                double convertedTemp = (temperatureD - 32) * 5.0 / 9.0;
                results.put("tempConverted", String.format("%s°F is %s°C", temperatureD, convertedTemp));
            }
            if (conversion.equals("CtoF")) {
                double convertedTemp = temperatureD * (9.0 / 5.0) + 32;
                results.put("tempConverted", String.format("%s°C is %s°F", temperatureD, convertedTemp));
            }
        }
    }

    public static boolean isANumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
