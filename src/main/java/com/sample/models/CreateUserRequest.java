package com.sample.models;

import com.google.gson.annotations.SerializedName;

public class CreateUserRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("email_id")
    private String emailId;

    @SerializedName("phone_number")
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
