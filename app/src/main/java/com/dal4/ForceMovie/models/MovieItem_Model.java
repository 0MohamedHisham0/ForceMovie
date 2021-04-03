package com.dal4.ForceMovie.models;

import androidx.annotation.Nullable;

//Api Get Ratings
public class MovieItem_Model {
    @Nullable
    String id;
    @Nullable
    String title;
    @Nullable
    String titleType;
    int year;
    double rating;

    public MovieItem_Model(String id, String title, String titleType, int year, double rating) {
        this.id = id;
        this.title = title;
        this.titleType = titleType;
        this.year = year;
        this.rating = rating;
    }

    public MovieItem_Model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
