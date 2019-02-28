package com.jakewhartonproject.android.connection;


import android.content.Context;

import com.jakewhartonproject.android.constant.ApplicationConstant;
import com.jakewhartonproject.android.model.Response;

import java.util.ArrayList;

import io.reactivex.Observable;

public class ApiConnection {

    public static Observable<ArrayList<Response>> getGitHubResponse(Context context, int pageNo) {
        return RetrofitClient.getClient(context).create(ApiInterface.class).getGitHubResponse(pageNo, ApplicationConstant.PER_PAGE, "d25b45eedcb46fc4ba7b5a263925b35afd3ba841");
    }

}
