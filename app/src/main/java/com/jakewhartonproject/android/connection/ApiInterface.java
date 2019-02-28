package com.jakewhartonproject.android.connection;

import com.jakewhartonproject.android.model.Response;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String GITHUB_API = "/users/JakeWharton/repos";


    @GET(GITHUB_API)
    Observable<ArrayList<Response>> getGitHubResponse(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("access_token") String token

    );


}