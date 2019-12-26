package com.example.watchwithme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.watchwithme.Adapter.WatchAdapter;
import com.example.watchwithme.Listeners.GetMoviesListener;
import com.example.watchwithme.model.Movie;
import com.example.watchwithme.repository.WatchRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rvMovies);

        LinearLayoutManager llManager = new LinearLayoutManager(this);
        llManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setLayoutManager(llManager);

        WatchRepository.WatchAsyncTask asyncTask = new WatchRepository.WatchAsyncTask(new GetMoviesListener() {
            @Override
            public void loadMovies(List<Movie> movies) {
                WatchAdapter adapter = new WatchAdapter(WatchRepository.getMovies(), MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        }, this);

        asyncTask.execute();

    }
}
