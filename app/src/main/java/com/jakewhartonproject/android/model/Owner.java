package com.jakewhartonproject.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public Owner(String image)
    {
        avatarUrl = image;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
