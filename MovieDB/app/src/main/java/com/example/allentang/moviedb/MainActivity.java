package com.example.allentang.moviedb;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_URL_STRING = "https://api.themoviedb.org/3/movie/popular?" +
            "api_key=" + ApiKey.apiKey;
    public static final int PAGES_TO_BE_LOADED = 5;

    private RecyclerView movieRecyclerView;
    private ArrayList<Movie> moviesList = new ArrayList<>();
    private MovieAdapter adaptMovieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = (RecyclerView) findViewById(R.id.MovieRecyclerView);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //movies list will be populated with asynctask but it is empty for now
        adaptMovieView = new MovieAdapter(moviesList);
        movieRecyclerView.setAdapter(adaptMovieView);

        try{
            //create an array of the 5 urls which correspond to 5 pages or 100 movies
            URL [] movieLoadPages = new URL[PAGES_TO_BE_LOADED];
            for (int i = 0; i < PAGES_TO_BE_LOADED; i++) {
                movieLoadPages[i] = new URL(MOVIE_URL_STRING + "&page=" + i+1);
            }
            //fetch data in background
            new MovieASyncTask(this).execute(movieLoadPages);
        } catch(MalformedURLException movieMalE){
            movieMalE.printStackTrace();
        }
    }


    /**
     * Created by zilles on 3/14/17 modified by Allen Tang 3/28
     *
     * This class (1) takes a URL, (2) makes an HTTP request, (3) parses the resulting JSON
     * into a NewsCollection, and (4) returns the array of NewsArticles.
     *
     * This class requires a Context in its constructor so that we can make Toasts.
     */
    public class MovieASyncTask extends AsyncTask<URL, Void, Movie[]>{
        Context movieContext;
        public MovieASyncTask(Context movieContext){
            this.movieContext = movieContext;
        }


        //background run
        @Override
        protected Movie[] doInBackground(URL...params){
            try {
                ArrayList<Movie> totalMovies = new ArrayList<>();
                //load all the movie data and append to an arraylist
                for(int i = 0; i < PAGES_TO_BE_LOADED; i++) {
                    URL movieLoadURL = params[i];
                    URLConnection connectionToMovieDB = movieLoadURL.openConnection();
                    connectionToMovieDB.connect();

                    InputStream inStream = connectionToMovieDB.getInputStream();
                    InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));

                    Gson movieGson = new Gson();
                    MoviesList gsonMovieList = movieGson.fromJson(inStreamReader, MoviesList.class);

                    for(Movie toAdd : gsonMovieList.getResults()){
                        totalMovies.add(toAdd);
                    }
                }
                Movie [] toArrayMovies = totalMovies.toArray(new Movie [totalMovies.size()]);
                return toArrayMovies;

            } catch (IOException movieIOE){
                Log.d("MovieASyncTask ", "failed to get data from movieDB", movieIOE);
                return null;
            }

        }

        //UI thread run
        @Override
        protected void onPostExecute(Movie[] movies){
            if(movies == null){
                Toast.makeText(movieContext, "failed to get data from movieDB", Toast.LENGTH_LONG).show();
                return;
            }
            //clearing is extraneous but good practice
            moviesList.clear();
            for(Movie currentMovie: movies){
                Log.d("MOVIE", currentMovie.getOriginal_title());
                moviesList.add(currentMovie);
            }

            //give adapter the new set of movies so it can load them
            adaptMovieView.notifyDataSetChanged();
        }
    }

}
