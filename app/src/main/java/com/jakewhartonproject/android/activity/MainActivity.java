package com.jakewhartonproject.android.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jakewhartonproject.android.R;
import com.jakewhartonproject.android.adapter.GitRepoRVAdapter;
import com.jakewhartonproject.android.connection.ApiConnection;
import com.jakewhartonproject.android.constant.ApplicationConstant;
import com.jakewhartonproject.android.database.ResponseDatabase;
import com.jakewhartonproject.android.databinding.ActivityMainBinding;
import com.jakewhartonproject.android.model.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.setIsLoading(true);
        if (ApplicationConstant.isNetworkAvailable(this))
            getAPIResponse();
        else {
            ResponseDatabase responseDatabase = ResponseDatabase.getDatabase(this);
            if (responseDatabase.gitDao().getAllRepos() != null && responseDatabase.gitDao().getAllRepos().size() > 0) {
                setData(responseDatabase.gitDao().getAllRepos());
            }
        }

    }

    private void setData(List<com.jakewhartonproject.android.database.Response> allRepos) {

        Toast.makeText(this, getResources().getString(R.string.offline_data), Toast.LENGTH_SHORT).show();

        ArrayList<Response> list = new ArrayList<>();

        for (com.jakewhartonproject.android.database.Response response : allRepos) {
            Response response1 = new Response(response.getName(), response.getDescription(), response.getLanguage(), response.getOpenIssues(), response.getWatchersCount());
            list.add(response1);
        }

        mAdapter = new GitRepoRVAdapter(this, list);
        mBinding.repoRv.setAdapter(mAdapter);
        mBinding.setIsLoading(false);

    }

    int pageNo = 1;

    private void getAPIResponse() {


        ApiConnection.getGitHubResponse(this, pageNo++).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Response>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v("TAG", "1");
                    }

                    @Override
                    public void onNext(ArrayList<Response> response) {
                        Log.v("TAG", "2");
                        if (response != null && response.size() > 0)
                            setData(response);
                        else
                            mBinding.setIsLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("TAG", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.v("TAG", "4");
                    }
                });

    }

    ArrayList<Response> responseList = null;
    GitRepoRVAdapter mAdapter = null;

    private void setData(ArrayList<Response> response) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mBinding.repoRv.getLayoutManager();
        ResponseDatabase.getDatabase(this).addResponseList(response, this);

        if (responseList == null) {

            responseList = new ArrayList<>();
            responseList.addAll(response);

            mAdapter = new GitRepoRVAdapter(this, responseList);
            mBinding.repoRv.setAdapter(mAdapter);

            if (ApplicationConstant.isNetworkAvailable(this)) {


                mBinding.repoRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCount = layoutManager.getItemCount();
                        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                        if (((totalItemCount - lastVisibleItemPosition) == 2 && firstVisibleItemPosition >= 0) && !mBinding.getIsLoading()) {
                            Log.d("rvPos", lastVisibleItemPosition + " , " + totalItemCount);
                            mBinding.setIsLoading(true);
                            getAPIResponse();
                        }

                    }
                });
            }

        } else {
            int previousSize = layoutManager.getItemCount();
            mAdapter.addAll(response);
            mAdapter.notifyItemRangeInserted(previousSize, response.size());
        }
        mBinding.setIsLoading(false);

    }
}
