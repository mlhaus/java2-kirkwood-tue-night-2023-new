package com.hauschildt.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public
class Street implements Serializable {
    @JsonProperty("number")
    private String number;
    @JsonProperty("name")
    private String name;

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t\tnumber='" + number + '\'' +
                "\n\t\tname='" + name + '\'' +
                "\n\t}";
    }
}
