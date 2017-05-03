package com.apppartner.androidtest.chat;

import com.google.gson.annotations.SerializedName;

public class ChatLogMessageModel {
    @SerializedName("user_id")
    private int userId;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("username")
    private String username;

    @SerializedName("message")
    private String message;

    public ChatLogMessageModel(int userId, String avatarUrl, String username, String message) {
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.username = username;
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
