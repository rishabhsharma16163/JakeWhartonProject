package com.jakewhartonproject.android.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;

@Dao
public interface GitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Response response);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllRepos(List<Response> responseList);


    @Query("DELETE FROM git_table")
    void deleteAll();

    @Query("SELECT * from git_table")
    List<Response> getAllRepos();

}