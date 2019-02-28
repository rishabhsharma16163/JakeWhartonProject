package com.jakewhartonproject.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewhartonproject.android.databinding.ItemGitRepoBinding;
import com.jakewhartonproject.android.model.Response;

import java.util.ArrayList;
import java.util.List;

public class GitRepoRVAdapter extends RecyclerView.Adapter<GitRepoRVAdapter.GitRepoViewHolder> {

    private Context mContext;
    private ArrayList<Response> mData;

    public GitRepoRVAdapter(Context context, ArrayList<Response> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public GitRepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemGitRepoBinding itemBinding = ItemGitRepoBinding.inflate(layoutInflater, viewGroup, false);
        return new GitRepoViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GitRepoViewHolder gitRepoViewHolder, int i) {

        gitRepoViewHolder.itemGitRepoBinding.setData(mData.get(i));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addAll(List<Response> data) {
        mData.addAll(data);
    }

    class GitRepoViewHolder extends RecyclerView.ViewHolder {

        ItemGitRepoBinding itemGitRepoBinding;

        GitRepoViewHolder(ItemGitRepoBinding binding) {
            super(binding.getRoot());
            this.itemGitRepoBinding = binding;
        }

    }

}
