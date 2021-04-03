package com.dal4.ForceMovie.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dal4.ForceMovie.Constance.Constants;
import com.dal4.ForceMovie.R;
import com.dal4.ForceMovie.database.RetrofitClient;
import com.dal4.ForceMovie.models.Search_Model;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {
    //Views
    SearchView searchView;
    RecyclerView RV_Search;
    SpinKitView progress_circular_Search;

    //Var
    Sprite progress_style = new Wave();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
    }

    private void initViews() {
        searchView = findViewById(R.id.SearchView_FragSearch);
        RV_Search = findViewById(R.id.RV_Search);
        progress_circular_Search = findViewById(R.id.progress_circular_Search);
        searchView.performClick();
        searchView.onActionViewExpanded();

    }

    public void GetSearchList(String movie_name, String page) {
        String api_key = "de57421802c26d3e6071cb02a0c0d7da";
        progress_circular_Search.setIndeterminateDrawable(progress_style);
        progress_circular_Search.setVisibility(View.VISIBLE);
        RV_Search.setVisibility(View.GONE);

        Call<Search_Model> call = RetrofitClient.getInstance().GetSearchList(api_key, movie_name, page);
        call.enqueue(new Callback<Search_Model>() {
            @Override
            public void onResponse(Call<Search_Model> call, Response<Search_Model> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    Search_Model search_models = response.body();
                    List<Search_Model.Results_Detail> results_details = search_models.getResults();

                    //Adapter
                    RV_Search.setAdapter(new AdapterSearch(results_details));

                    progress_circular_Search.setVisibility(View.GONE);
                    RV_Search.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(Search.this, response.message() + "", Toast.LENGTH_SHORT).show();
                    progress_circular_Search.setVisibility(View.GONE);
                    RV_Search.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Search_Model> call, Throwable t) {
                Toast.makeText(Search.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
                progress_circular_Search.setVisibility(View.GONE);
                RV_Search.setVisibility(View.VISIBLE);
            }
        });
    }

    public class AdapterSearch extends RecyclerView.Adapter<VH_Search> {

        List<Search_Model.Results_Detail> list;

        public AdapterSearch(List<Search_Model.Results_Detail> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public VH_Search onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_search, parent, false);
            return new VH_Search(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH_Search holder, int position) {
            Search_Model.Results_Detail search_model = list.get(position);

            holder.MName.setText(search_model.getName());
            holder.DName.setText(search_model.getOrigin_country());

            holder.CardViewItemSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Search.this, MovieDetail.class);
                    intent.putExtra("MovieID", search_model.getId() + "");
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }
    }

    public class VH_Search extends RecyclerView.ViewHolder {
        TextView MName, DName;
        CardView CardViewItemSearch;

        public VH_Search(@NonNull View itemView) {
            super(itemView);
            if (MName == null)
                MName = itemView.findViewById(R.id.MovieName_ItemSearch);
            if (DName == null)
                DName = itemView.findViewById(R.id.MovieDetail_ItemSearch);

            if (CardViewItemSearch == null)
                CardViewItemSearch = itemView.findViewById(R.id.CardViewItemSearch);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constants.checkInternetConnection(getApplicationContext())) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    GetSearchList(query, "1");
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            GetSearchList(newText, "1");
                        }
                    }, 300);
                    return true;

                }
            });
        } else {
            progress_circular_Search.setVisibility(View.GONE);
            Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content),
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