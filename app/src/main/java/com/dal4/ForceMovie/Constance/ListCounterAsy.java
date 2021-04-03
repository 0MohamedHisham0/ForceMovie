package com.dal4.ForceMovie.Constance;

import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListCounterAsy extends AsyncTask<Integer, Void, List<Integer>> {
    RecyclerView recyclerView;
    Context context;
    Fragment fragment;
    String movie_name;

    public ListCounterAsy(RecyclerView recyclerView_pages, Context context, Fragment fragment, String movie_name) {
        this.recyclerView = recyclerView_pages;
        this.context = context;
        this.fragment = fragment;
        this.movie_name = movie_name;
    }

    @Override
    protected List<Integer> doInBackground(Integer... integers) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < integers[0]; i++) {
            list.add(i + 1);
        }
        return list;

    }

    @Override
    protected void onPostExecute(List<Integer> list) {
        super.onPostExecute(list);
        PagesAdapter mAdapter = new PagesAdapter(list, context, fragment, movie_name);
        recyclerView.setAdapter(mAdapter);

    }
}