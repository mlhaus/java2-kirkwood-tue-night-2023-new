package com.hauschildt.controller;

import com.hauschildt.model.User;
import com.hauschildt.data_access.UserDAO;
import com.hauschildt.utilities.PasswordUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="loginServlet", value="/login")
public class LoginServlet extends HttpServlet {

    private static Map<String, String> results = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/demo/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("inputEmail1");
        String password1 = req.getParameter("inputPassword1");
        String[] rememberMe = req.getParameterValues("rememberMe");

        results.clear();
        results.put("email", email);
        results.put("password1", password1);
        if(rememberMe!= null && rememberMe[0].equals("true")) {
            results.put("rememberMe", rememberMe[0]);
        }  else {
            results.put("rememberMe", "");
        }

        try {
            User userFromDatabase = UserDAO.get(email);
            if(userFromDatabase == null) {
                // No email match
                results.put("loginFail", "No user found with that email and password combination.");
            } else {
                // Email found
                if(!PasswordUtility.checkpw(password1, String.valueOf(userFromDatabase.getPassword()))) {
                    // Passwords don't match
                    results.put("loginFail", "No user found with that email and password combination.");
                } else {
                    // Passwords match
                    results.put("loginSuccess", "Welcome back!");
                }
            }
        } catch(Exception e) {
            results.put("loginFail", "Cannot log you in. Try again later.");
        } 


        req.setAttribute("results", results);
        req.getRequestDispatcher("WEB-INF/demo/login.jsp").forward(req, resp);
    }
}
