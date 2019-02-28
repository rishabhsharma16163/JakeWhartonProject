package com.jakewhartonproject.android.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "git_table")
public class Response extends BaseObservable {


    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "language")
    private String language;

    @NonNull
    @ColumnInfo(name = "openIssues")
    private String openIssues;

    @NonNull
    @ColumnInfo(name = "watchersCount")
    private String watchersCount;

    public Response(int id, String name, String description, String language, String openIssues, String watchersCount)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
        this.openIssues = openIssues;
        this.watchersCount = watchersCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
