package com.dal4.ForceMovie.Constance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dal4.ForceMovie.R;
import com.dal4.ForceMovie.models.MoviesLists_Model;
import com.dal4.ForceMovie.ui.main.MovieDetail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMovies extends RecyclerView.Adapter<VH_TopMovies> {
    List<MoviesLists_Model.MovieDetail> TopMovies_List;
    Context context;
    View view;
    final static String Tag_MovieID = "MovieID";

    public AdapterMovies(List<MoviesLists_Model.MovieDetail> topMovies_List, Context context) {
        this.TopMovies_List = topMovies_List;
        this.context = context;
    }


    @NonNull
    @Override
    public VH_TopMovies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new VH_TopMovies(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH_TopMovies holder, int position) {

        MoviesLists_Model.MovieDetail Movie = TopMovies_List.get(position);

        String S_MName = Movie.getTitle();
        String S_MRate = Movie.getVote_average() + "";
        String S_MYear = Movie.getRelease_date() + "";
        String S_Poster = Movie.getPoster_path() + "";
        holder.MName.setText(S_MName);
        holder.MRate.setText(S_MRate);
        holder.MDetail.setText(S_MYear);

        if (S_Poster != "")
            SetImageIntoPicasso(S_Poster, holder.MImage, R.drawable.ic_error);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra(Tag_MovieID, Movie.getId() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return TopMovies_List == null ? 0 : TopMovies_List.size();
    }

    //More Fun
    private void SetImageIntoPicasso(String ImageUrl, ImageView imageView, int ErrorImage) {
        String ImageURL = "https://image.tmdb.org/t/p/w200/" + ImageUrl;
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
        } else {
            imageView.setImageResource(ErrorImage);
        }
    }

}

class VH_TopMovies extends RecyclerView.ViewHolder {

    TextView MName, MRate, MDetail;
    ImageView MImage;

    public VH_TopMovies(@NonNull View itemView) {
        super(itemView);
        if (MName == null)
            MName = itemView.findViewById(R.id.Movie_Name_MI);

        if (MRate == null)
            MRate = itemView.findViewById(R.id.MovieRate_MI);

        if (MDetail == null)
            MDetail = itemView.findViewById(R.id.Movie_Detail_MI);

        if (MImage == null)
            MImage = itemView.findViewById(R.id.Movie_Image_MI);

    }


}