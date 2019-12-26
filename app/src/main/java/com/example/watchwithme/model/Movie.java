package com.example.watchwithme.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Movie {

    @PrimaryKey
    @NonNull
    private int id;
    private String url;
    private String title;
//    private ArrayList<Integer> genresIds;
    private double voteAverage;
    private String overview;
    private String releaseDate;

//    ArrayList<Integer> genresIds - ova e za vo konstruktorot

    public Movie(){}

    @Ignore
    public Movie(int id, String url, String title, double voteAverage, String overview, String releaseDate) {
        this.id = id;
        this.url = url;
        this.title = title;
//        this.genresIds = genresIds;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public ArrayList<Integer> getGenresIds() {
//        return genresIds;
//    }
//
//    public void setGenresIds(ArrayList<Integer> genresIds) {
//        this.genresIds = genresIds;
//    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
