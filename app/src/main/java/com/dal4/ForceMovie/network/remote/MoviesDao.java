package com.dal4.ForceMovie.network.remote;

import com.dal4.ForceMovie.models.MovieDetail_Model;
import com.dal4.ForceMovie.models.MovieVideo_Model;
import com.dal4.ForceMovie.models.MoviesLists_Model;
import com.dal4.ForceMovie.models.Search_Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesDao {

    @GET("3/movie/top_rated")
    Call<MoviesLists_Model> getTopRatedMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("3/movie/popular")
    Call<MoviesLists_Model> getPopularMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("3/movie/upcoming")
    Call<MoviesLists_Model> getUpcomingMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("3/movie/{movie_id}")
    Call<MovieDetail_Model> getMovieDetail(
            @Path("movie_id") String movie_id,
            @Query("api_key") String api_key
    );

    @GET("3/movie/{movie_id}/videos")
    Call<MovieVideo_Model> getMovieVideo(
            @Path("movie_id") String movie_id
            , @Query("api_key") String api_key

    );

    @GET("3/search/company")
    Call<Search_Model> getSearchList(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("page") String page

    );
}
