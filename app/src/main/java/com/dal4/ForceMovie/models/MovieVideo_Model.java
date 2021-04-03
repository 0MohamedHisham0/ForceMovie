package com.dal4.ForceMovie.models;

import java.util.List;

public class MovieVideo_Model {
    String id;
    List<Results_Detail> results;

    public static class Results_Detail{
        String id;
        String key;
        String site;

        public Results_Detail(String id, String key, String site) {

            this.id = id;

            this.key = key;

            this.site = site;

        }


        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public MovieVideo_Model(String id, List<Results_Detail> results) {
        this.id = id;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Results_Detail> getResults() {
        return results;
    }

    public void setResults(List<Results_Detail> results) {
        this.results = results;
    }
}
