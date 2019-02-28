package com.jakewhartonproject.android.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

public class Response extends BaseObservable {


    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @NonNull
    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("open_issues_count")
    @Expose
    private String openIssues;

    @SerializedName("watchers_count")
    @Expose
    private String watchersCount;

    public Response(String name, String description, String language, String openIssues, String watchersCount)
    {
        this.name = name;
        this.description = description;
        this.language = language;
        this.openIssues = openIssues;
        this.watchersCount = watchersCount;
    }

    public String isName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        if (description == null)
            return "";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        if (language == null) {
            return "";
        }
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(String openIssues) {
        this.openIssues = openIssues;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(String watchersCount) {
        this.watchersCount = watchersCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
