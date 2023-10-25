package com.hauschildt.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserFromJson {
    @JsonProperty("results")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
}

class User {
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("cell")
    private String cell;

    @JsonProperty("nat")
    private String nat;

    @Override
    public String toString() {
        return "User{" +
                "gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", nat='" + nat + '\'' +
                '}';
    }
}
