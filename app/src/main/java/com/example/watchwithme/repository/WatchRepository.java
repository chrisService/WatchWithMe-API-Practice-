package com.example.watchwithme.repository;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.watchwithme.Adapter.WatchAdapter;
import com.example.watchwithme.Listeners.GetMoviesListener;
import com.example.watchwithme.MainActivity;
import com.example.watchwithme.database.MovieDatabase;
import com.example.watchwithme.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WatchRepository {

    public static List<Movie> movies = new ArrayList<>();


    public static List<Movie> getMovies() {


        return movies;
    }


    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/popular?";
    private static final String API_KEY = "e0a0a71361941e7d0478d4a3f2b539b2";


    public static  class WatchAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

        GetMoviesListener listener;
        Context context;

        public WatchAsyncTask(GetMoviesListener listener, Context context) {

            this.listener = listener;
            this.context = context;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {

            Uri.Builder uri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter("api_key", API_KEY);
            uri.build();

            try {
                URL url = new URL(uri.toString());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                int resposeCode = httpURLConnection.getResponseCode();
                if (resposeCode == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    String results = convertIsToString(inputStream);

                    JSONObject jsonResponseObject = new JSONObject(results);
                    JSONArray jsonArray = jsonResponseObject.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        String posterPath = (String) jsonObject.get("poster_path");

                        String posterUrl = "https://image.tmdb.org/t/p/w500" + posterPath;


                        int movieId = (Integer) jsonObject.getInt("id");
//
                        String title = (String) jsonObject.get("original_title");

//                        ArrayList<Integer> genresIds = new ArrayList<>();
//                        JSONArray jsonGenresIds = jsonObject.getJSONArray("genre_ids");
//                        for (int j = 0; j < jsonGenresIds.length(); j++){
//                            int genreId = jsonGenresIds.getInt(j);
//                            genresIds.add(genreId);
//                        }

                        double voteAverage = 0.0;
                        if (jsonObject.has("vote_average")){
                            voteAverage = (Double) jsonObject.getDouble("vote_average");
                        }

                        String overview = (String) jsonObject.get("overview");

                        String releaseDate = (String) jsonObject.get("release_date");

                        movies.add(new Movie(movieId, posterUrl, title, voteAverage, overview, releaseDate));


                    }

                    MovieDatabase.getMovieDatabase(context).movieDao().insertMovies(movies);


                }else {
                    movies = MovieDatabase.getMovieDatabase(context).movieDao().getAllMovies();
                }


            } catch (MalformedURLException | ProtocolException | JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                movies = MovieDatabase.getMovieDatabase(context).movieDao().getAllMovies();
            }


            return movies;
        }


        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);

            listener.loadMovies(movies);

        }


        private String convertIsToString(InputStream inputStream) throws IOException {
            StringBuilder sb = new StringBuilder();
            InputStreamReader isReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isReader);
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        }


    }
}
