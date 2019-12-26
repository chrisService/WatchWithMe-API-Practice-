package com.example.watchwithme.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.watchwithme.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<Movie> movies);

    @Query("SELECT * FROM Movie")
    List<Movie> getAllMovies();
}
