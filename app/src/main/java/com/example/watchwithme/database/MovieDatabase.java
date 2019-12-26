package com.example.watchwithme.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.watchwithme.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase INSTANCE;

    public static MovieDatabase getMovieDatabase(Context context){
        if(INSTANCE == null){
            synchronized (MovieDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context,
                            MovieDatabase.class,
                            "movie_db")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
