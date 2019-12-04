package com.sample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class User {
    @Expose
    private final String id;
    @Expose
    @SerializedName("name")
    private final String name;
    @Expose
    @SerializedName("email_id")
    private final String emailId;
    @Expose
    @SerializedName("phone_number")
    private final String phoneNumber;
    @Expose
    @SerializedName("created_at")
    private final Timestamp createdAt;
    @Expose(serialize = false, deserialize = false)
    private final Timestamp updatedAt;

    public User(String id, String name, String emailId, String phoneNumber, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
