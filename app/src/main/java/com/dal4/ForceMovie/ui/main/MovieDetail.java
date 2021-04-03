package com.dal4.ForceMovie.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.dal4.ForceMovie.Constance.Constants;
import com.dal4.ForceMovie.R;
import com.dal4.ForceMovie.database.RetrofitClient;
import com.dal4.ForceMovie.models.MovieDetail_Model;
import com.dal4.ForceMovie.models.MovieVideo_Model;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.snackbar.Snackbar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity {
    //Views
    ImageView PosterImage, BackDropPath, ImagePoster_MovieDetail;
    TextView MRate, MTitle, MDescription, MSpokenLn;
    RecyclerView RV_MGenres;
    Button Btn_Back;
    SpinKitView progress_circular_MovieDetail;
    ScrollView ScrollView_MovieDetail;
    RelativeLayout Relative_MovieDetail;
    YouTubePlayerView youTubePlayerView;
    //Var
    Sprite progress_style = new Wave();
    String api_key = "de57421802c26d3e6071cb02a0c0d7da";
    String Current_movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        initViews();
    }

    private void initViews() {
        PosterImage = findViewById(R.id.ImagePoster_MovieDetail);
        BackDropPath = findViewById(R.id.Image_BackDropPath_MovieDetail);
        MRate = findViewById(R.id.Rate_MovieDetail);
        MTitle = findViewById(R.id.MovieName_MovieDetail);
        MDescription = findViewById(R.id.Description_MovieDetail);
        MSpokenLn = findViewById(R.id.SpokenLan_MovieDetail);
        RV_MGenres = findViewById(R.id.RV_MovieGenres_MovieDetail);
        Btn_Back = findViewById(R.id.Btn_back_MovieDetail);
        progress_circular_MovieDetail = findViewById(R.id.progress_circular_MovieDetail);
        ScrollView_MovieDetail = findViewById(R.id.ScrollView_MovieDetail);
        Relative_MovieDetail = findViewById(R.id.Relative_MovieDetail);
        youTubePlayerView = findViewById(R.id.YouTubePlayer_MovieDetail);
        ImagePoster_MovieDetail = findViewById(R.id.ImagePoster_MovieDetail);

        getLifecycle().addObserver(youTubePlayerView);

        Btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        Current_movie_id = intent.getStringExtra("MovieID");

    }

    public void TopMoviesData(String movie_id) {
        progress_circular_MovieDetail.setIndeterminateDrawable(progress_style);
        progress_circular_MovieDetail.setVisibility(View.VISIBLE);
        ScrollView_MovieDetail.setVisibility(View.GONE);

        Call<MovieDetail_Model> call = RetrofitClient.getInstance().GetMovieDetail(api_key, movie_id);
        call.enqueue(new retrofit2.Callback<MovieDetail_Model>() {
            @Override
            public void onResponse(Call<MovieDetail_Model> call, Response<MovieDetail_Model> response) {
                if (response.isSuccessful() && response.code() == 200) {

                    MovieDetail_Model movieDetail_model = response.body();
                    List<MovieDetail_Model.Spoken_languages_Detail> SpokenLn = movieDetail_model.getSpoken_languages();
                    List<MovieDetail_Model.GenresDetail> genresDetails = movieDetail_model.getGenres();
                    //Fetch Data
                    SetImageIntoPicasso(movieDetail_model.getPoster_path(), PosterImage, R.drawable.ic_error);
                    SetImageIntoPicasso(movieDetail_model.getBackdrop_path(), BackDropPath, R.drawable.ic_error);
                    MRate.setText(movieDetail_model.getVote_average() + "");
                    MTitle.setText(movieDetail_model.getTitle());
                    MDescription.setText(movieDetail_model.getOverview());
                    MSpokenLn.setText("Spoken Language : " + SpokenLn.get(0).getEnglish_name());

                    RV_MGenres.setAdapter(new AdapterGenres(genresDetails));

                    GetMovieVideo(movie_id);

                    progress_circular_MovieDetail.setVisibility(View.GONE);
                    ScrollView_MovieDetail.setVisibility(View.VISIBLE);
                    ImagePoster_MovieDetail.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(getApplicationContext(), response.message() + "", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<MovieDetail_Model> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void GetMovieVideo(String movie_id) {
        Call<MovieVideo_Model> call = RetrofitClient.getInstance().GetMovieVideos(api_key, movie_id);
        call.enqueue(new retrofit2.Callback<MovieVideo_Model>() {
            @Override
            public void onResponse(Call<MovieVideo_Model> call, Response<MovieVideo_Model> response) {
                if (response.isSuccessful() && response.code() == 200) {

                    MovieVideo_Model movieVideo_model = response.body();
                    List<MovieVideo_Model.Results_Detail> results_detail = movieVideo_model.getResults();

                    youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            if (!results_detail.isEmpty() && results_detail.get(0).getSite().equals("YouTube")) {
                                String videoId = results_detail.get(0).getKey();
                                youTubePlayer.cueVideo(videoId, 0);
                            } else {
                                youTubePlayerView.setVisibility(View.GONE);
                                Toast.makeText(MovieDetail.this, "Trailer on this Movie is not Available", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), response.message() + "", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<MovieVideo_Model> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() + "", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void SetImageIntoPicasso(String ImageUrl, ImageView imageView, int ErrorImage) {
        String ImageURL = "https://image.tmdb.org/t/p/w500/" + ImageUrl;
        if (!ImageUrl.isEmpty()) {
            Picasso.get()
                    .load(ImageURL)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            imageView.setImageResource(R.drawable.ic_error);
                        }
                    });
        }

        if (ImageUrl.isEmpty()) {
            imageView.setImageResource(ErrorImage);
        }
    }

    public class AdapterGenres extends RecyclerView.Adapter<VH_Genres> {
        List<MovieDetail_Model.GenresDetail> list;

        public AdapterGenres(List<MovieDetail_Model.GenresDetail> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public VH_Genres onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_movie_genres, parent, false);
            return new VH_Genres(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH_Genres holder, int position) {
            MovieDetail_Model.GenresDetail genresDetail = list.get(position);

            holder.textView.setText(genresDetail.getName() + "");
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }
    }

    public class VH_Genres extends RecyclerView.ViewHolder {
        TextView textView;

        public VH_Genres(@NonNull View itemView) {
            super(itemView);
            if (textView == null)
                textView = itemView.findViewById(R.id.Text_ItemMovie_Genres);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constants.checkInternetConnection(getApplicationContext())) {
            TopMoviesData(Current_movie_id);

        } else {
            progress_circular_MovieDetail.setVisibility(View.GONE);
            Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content),
                    "No Internet", Snackbar.LENGTH_INDEFINITE);
            snackBar.show();
            snackBar.setAction("CLOSE", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        if (Current_movie_id == null) {
            progress_circular_MovieDetail.setVisibility(View.GONE);
            Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content),
                    "This Movie does not exist", Snackbar.LENGTH_INDEFINITE);
            snackBar.show();
            snackBar.setAction("CLOSE", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}