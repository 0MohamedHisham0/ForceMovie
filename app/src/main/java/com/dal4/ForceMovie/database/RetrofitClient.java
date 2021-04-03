package com.dal4.ForceMovie.database;

import com.dal4.ForceMovie.models.MovieDetail_Model;
import com.dal4.ForceMovie.models.MovieVideo_Model;
import com.dal4.ForceMovie.models.MoviesLists_Model;
import com.dal4.ForceMovie.models.Search_Model;
import com.dal4.ForceMovie.network.remote.MoviesDao;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private final static String BASE_URL = "https://api.themoviedb.org/";
    private MoviesDao moviesDao;

    private static RetrofitClient retrofitClient;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        moviesDao = retrofit.create(MoviesDao.class);
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public Call<MoviesLists_Model> GetTopRatedMovies(String api_key, String language, String page_number) {

        return moviesDao.getTopRatedMovies(api_key, language, page_number);
    }

    public Call<MoviesLists_Model> GetPopularMovies(String api_key, String language, String page_number) {

        return moviesDao.getPopularMovies(api_key, language, page_number);
    }

    public Call<MoviesLists_Model> GetUpcomingMovies(String api_key, String language, String page_number) {

        return moviesDao.getUpcomingMovies(api_key, language, page_number);
    }

    public Call<MovieDetail_Model> GetMovieDetail(String api_key, String movie_id) {

        return moviesDao.getMovieDetail(movie_id, api_key);
    }

    public Call<MovieVideo_Model> GetMovieVideos(String api_key, String movie_id) {

        return moviesDao.getMovieVideo(movie_id, api_key);
    }

    public Call<Search_Model> GetSearchList(String api_key, String movie_name, String page) {

        return moviesDao.getSearchList(api_key, movie_name, page);
    }
}
