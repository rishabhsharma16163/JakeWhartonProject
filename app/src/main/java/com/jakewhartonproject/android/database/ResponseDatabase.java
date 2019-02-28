package com.jakewhartonproject.android.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jakewhartonproject.android.model.Response;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {com.jakewhartonproject.android.database.Response.class}, version = 1, exportSchema = false)
public abstract class ResponseDatabase extends RoomDatabase {

    public abstract GitDao gitDao();

    private static volatile ResponseDatabase INSTANCE;

    public static ResponseDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ResponseDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ResponseDatabase.class, "room_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public void addResponseList(ArrayList<Response> responses, Context context) {
        getDatabase(context);
        for (Response response : responses) {
            com.jakewhartonproject.android.database.Response r = new com.jakewhartonproject.android.database.Response(response.getId(), response.isName(), response.getDescription(), response.getLanguage(), response.getOpenIssues(), response.getWatchersCount());
            INSTANCE.gitDao().insert(r);
        }
    }

//    public List<Response> getGits(Context context) {
//        getDatabase(context);
//        return INSTANCE.gitDao().getAllRepos();
//    }

}