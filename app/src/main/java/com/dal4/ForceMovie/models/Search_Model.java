package com.dal4.ForceMovie.models;

import java.util.List;

public class Search_Model {
    int page;
    List<Results_Detail> results;
    int total_pages;
    int total_results;

    public Search_Model(int page, List<Results_Detail> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public static class Results_Detail {
        int id;
        String name;
        String logo_path;
        String origin_country;

        public Results_Detail(int id, String name, String logo_path, String origin_country) {
            this.id = id;
            this.name = name;
            this.logo_path = logo_path;
            this.origin_country = origin_country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(String logo_path) {
            this.logo_path = logo_path;
        }

        public String getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(String origin_country) {
            this.origin_country = origin_country;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Results_Detail> getResults() {
        return results;
    }

    public void setResults(List<Results_Detail> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
