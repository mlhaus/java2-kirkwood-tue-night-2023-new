package com.hauschildt.controller;

import com.hauschildt.data_access.UserDAO;
import com.hauschildt.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "userDBServlet", value = "/users-db")
public class UserDBServlet extends HttpServlet {
    private static List<User> users;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/demo/users-db.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        users = UserDAO.getAll();
    }
}