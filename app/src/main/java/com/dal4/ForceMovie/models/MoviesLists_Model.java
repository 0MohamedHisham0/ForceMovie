package com.dal4.ForceMovie.models;

import androidx.annotation.Nullable;

import java.util.List;

public class MoviesLists_Model {
    @Nullable
    private String page;
    private List<MovieDetail> results;
    private String total_pages;

    public MoviesLists_Model(String page, List<MovieDetail> results, String total_pages) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
    }

    public List<MovieDetail> getResults() {
        return results;
    }

    public void setResults(List<MovieDetail> results) {
        this.results = results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<MovieDetail> getMovieDetails() {
        return results;
    }

    public void setMovieDetails(List<MovieDetail> movieDetails) {
        this.results = movieDetails;
    }

    public class MovieDetail {
        boolean adult;
        String overview;
        String release_date;
        int id;
        String original_language;
        String original_title;
        @Nullable
        String poster_path;
        String title;
        double vote_average;
        int vote_count;

        public MovieDetail(boolean adult, String overview, String release_date, int id, String original_language, String original_title, String poster_path, String title, double vote_average, int vote_count) {
            this.adult = adult;
            this.overview = overview;
            this.release_date = release_date;
            this.id = id;
            this.original_language = original_language;
            this.original_title = original_title;
            this.poster_path = poster_path;
            this.title = title;
            this.vote_average = vote_average;
            this.vote_count = vote_count;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        @Nullable
        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }
    }
}
