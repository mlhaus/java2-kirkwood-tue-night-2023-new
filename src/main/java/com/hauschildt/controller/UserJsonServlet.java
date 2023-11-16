package com.hauschildt.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hauschildt.data_json.JsonReader;
import com.hauschildt.data_json.User;
import com.hauschildt.data_json.UserFromJson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name="userJsonServlet", value="/user-json")
public class UserJsonServlet extends HttpServlet {
    private static List<User> users = new ArrayList<>();
    private static List<String> states = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");
        String s = req.getParameter("sort");
        String state = req.getParameter("state");
        req.setAttribute("q", q);
        req.setAttribute("s", s);
        req.setAttribute("state", state);
        String query = q != null ? q : "";
        String sort = s != null ? s : "";
        String state2 = state != null ? state : "";
        List<User> copy = new ArrayList<>(users);
//        for(User user: users) {
//            try {
//                deepCopy.add((User)user.clone());
//            } catch (CloneNotSupportedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        if(!query.equals("")) {
            copy.removeIf(user -> !user.getName().fullName().toLowerCase().contains(query.toLowerCase()));
        }
        if(!state2.equals("")) {
            copy.removeIf(user -> !user.getLocation().getState().equals(state2));
        }
        if(!sort.equals("")) {
            if(sort.equals("az")) {
                Collections.sort(copy);
            } else {
                copy.sort((a, b) -> b.compareTo(a));
            }
        }
        req.setAttribute("users", copy);
        req.setAttribute("states", states);
        req.getRequestDispatcher("WEB-INF/demo/user-json.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        try {
            JSONObject json = JsonReader.readJsonFromUrl("https://randomuser.me/api/?format=json&seed=abc&results=20&nat=us&noinfo");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserFromJson userFromJson = mapper.readValue(json.toString(), UserFromJson.class);
//            userFromJson.getUsers().forEach(System.out::println);
            users = userFromJson.getUsers();

            for(User user: users) {
                String state = user.getLocation().getState();
                if(!states.contains(state)) {
                    states.add(state);
                }
            }
            Collections.sort(states);
        } catch(IOException e) {
            // TODO: Forward data error to jsp
        }

    }
}
