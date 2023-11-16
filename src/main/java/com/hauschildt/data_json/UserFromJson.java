package com.hauschildt.data_json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserFromJson {
    @JsonProperty("results")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
}
