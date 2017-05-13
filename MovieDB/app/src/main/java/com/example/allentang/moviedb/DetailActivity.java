package com.example.allentang.moviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;



public class DetailActivity extends AppCompatActivity {

    private TextView movieTitleTextView;
    private TextView moviePopTextView;
    private TextView movieBodyTextView;
    private TextView movieVoteTextView;
    private ImageView movieImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get references to the views.
        movieTitleTextView = (TextView) findViewById(R.id.titleDetailTextView);
        moviePopTextView = (TextView) findViewById(R.id.popularityDetailTextView);
        movieBodyTextView = (TextView) findViewById(R.id.bodyDetailTextView);
        movieImageView = (ImageView) findViewById(R.id.imageDetailView);
        movieVoteTextView = (TextView) findViewById(R.id.voteDetailTextView);

        Intent intent = getIntent();


        /* Code for receiving the Parcelable Movie */
        Movie loadMovie = intent.getParcelableExtra(MovieAdapter.MOVIE);

        movieTitleTextView.setText(loadMovie.getOriginal_title());
        moviePopTextView.setText(loadMovie.getPopularity());
        movieBodyTextView.setText(loadMovie.getOverview());
        movieVoteTextView.setText(loadMovie.getVote_average());
        Picasso.with(movieImageView.getContext())
                .load("http://image.tmdb.org/t/p/w342/" + loadMovie.getPoster_path()).into(movieImageView);
    }
}