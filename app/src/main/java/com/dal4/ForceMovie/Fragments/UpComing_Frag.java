package com.dal4.ForceMovie.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dal4.ForceMovie.Constance.AdapterMovies;
import com.dal4.ForceMovie.Constance.Constants;
import com.dal4.ForceMovie.Constance.ListCounterAsy;
import com.dal4.ForceMovie.Constance.ScrollOB;
import com.dal4.ForceMovie.R;
import com.dal4.ForceMovie.database.RetrofitClient;
import com.dal4.ForceMovie.models.MoviesLists_Model;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UpComing_Frag extends Fragment {
    //Views
    View view;
    ObservableRecyclerView RV_Upcoming;
    RecyclerView RV_PagesNumber;
    SpinKitView progress_circular_Upcoming;
    Button Search;
    //Var
    String api_key = "de57421802c26d3e6071cb02a0c0d7da";
    String CurrentPage = "1";
    String TotalPage = "0";
    List<MoviesLists_Model.MovieDetail> MovieList;
    Sprite progress_style = new Wave();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_upcoming, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
    }

    //initializing Views
    private void initViews() {
        RV_PagesNumber = view.findViewById(R.id.RV_Upcoming_Pages);
        RV_Upcoming = view.findViewById(R.id.RV_Upcoming);
        progress_circular_Upcoming = view.findViewById(R.id.progress_circular_Upcoming);
        Search = view.findViewById(R.id.Btn_Search_Upcoming);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), com.dal4.ForceMovie.ui.main.Search.class));
            }
        });
    }

    public void initAdapter(List<MoviesLists_Model.MovieDetail> list) {
        AdapterMovies adapterTopMovies = new AdapterMovies(list, getContext());
        RV_Upcoming.setAdapter(adapterTopMovies);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        RV_Upcoming.setScrollViewCallbacks(new ScrollOB(RV_PagesNumber));
        RV_Upcoming.setLayoutManager(layoutManager);

    }

    private void initRV_Pages() {
        RV_PagesNumber.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        new ListCounterAsy(RV_PagesNumber, getContext(), UpComing_Frag.this, "").execute(Integer.parseInt(TotalPage));
    }

    //initializing Data
    public void TopMoviesData(String page_num) {
        progress_circular_Upcoming.setIndeterminateDrawable(progress_style);
        progress_circular_Upcoming.setVisibility(View.VISIBLE);
        RV_Upcoming.setVisibility(View.GONE);
        Call<MoviesLists_Model> call = RetrofitClient.getInstance().GetUpcomingMovies(api_key, "en-US", page_num);
        call.enqueue(new retrofit2.Callback<MoviesLists_Model>() {
            @Override
            public void onResponse(Call<MoviesLists_Model> call, Response<MoviesLists_Model> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    MoviesLists_Model movies_Lists_model = response.body();
                    //Get Data
                    MovieList = movies_Lists_model.getMovieDetails();
                    CurrentPage = movies_Lists_model.getPage();

                    if (TotalPage.equals("0")) {
                        TotalPage = movies_Lists_model.getTotal_pages();
                        initRV_Pages();
                    }
                    //initAdapter
                    initAdapter(MovieList);

                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RV_Upcoming.setVisibility(View.VISIBLE);
                            progress_circular_Upcoming.setVisibility(View.GONE);
                        }
                    }, 50);


                } else {
                    Toast.makeText(getContext(), response.message() + "", Toast.LENGTH_SHORT).show();
                }


            }


            @Override
            public void onFailure(Call<MoviesLists_Model> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constants.checkInternetConnection(getContext())) {
            TopMoviesData("1");

        } else {
            progress_circular_Upcoming.setVisibility(View.GONE);
            Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "No Internet", Snackbar.LENGTH_INDEFINITE);
            snackBar.show();
            snackBar.setAction("CLOSE", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }

}
