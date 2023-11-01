package com.hauschildt.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Coordinates implements Serializable{
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t\tlatitude='" + latitude + '\'' +
                "\n\t\tlongitude='" + longitude + '\'' +
                "\n\t}";
    }
}