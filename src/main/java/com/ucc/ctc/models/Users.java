package com.ucc.ctc.models;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String name;
    @SerializedName("email")
    private String email;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
