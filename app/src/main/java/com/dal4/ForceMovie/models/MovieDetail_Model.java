package com.dal4.ForceMovie.models;

import java.util.List;

public class MovieDetail_Model {
    String backdrop_path;
    List<GenresDetail> genres;
    String overview;
    String poster_path;
    String release_date;
    List<Spoken_languages_Detail> spoken_languages;
    String title;
    double vote_average;

    public MovieDetail_Model(String backdrop_path, List<GenresDetail> genres, String overview, String poster_path, String release_date, List<Spoken_languages_Detail> spoken_languages, String title, double vote_average) {
        this.backdrop_path = backdrop_path;
        this.genres = genres;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.spoken_languages = spoken_languages;
        this.title = title;
        this.vote_average = vote_average;
    }

    public class GenresDetail {
        String id;
        String name;

        public GenresDetail(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Spoken_languages_Detail {
        String english_name;

        public Spoken_languages_Detail(String english_name) {
            this.english_name = english_name;
        }

        public String getEnglish_name() {
            return english_name;
        }

        public void setEnglish_name(String english_name) {
            this.english_name = english_name;
        }
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<GenresDetail> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresDetail> genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Spoken_languages_Detail> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<Spoken_languages_Detail> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }
}
