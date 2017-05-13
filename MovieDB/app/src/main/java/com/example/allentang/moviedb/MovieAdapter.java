package com.example.allentang.moviedb;

import android.content.Intent;
//import android.net.Uri;
//import android.widget.EditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

//import static android.R.id.message;
//import static android.provider.AlarmClock.EXTRA_MESSAGE;

import java.util.ArrayList;

/**
 * Helper class that deals with updating the recycler view using our POJO, references taken from
 * Craig Zilles @ github.com/zillesc/MyGoogleNews
 *
 * @author Allen Tang
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    //public static final String TITLE = "TITLE";
    //public static final String POPULARITY = "POP";
    //public static final String BODY = "BODY";
    //public static final String IMG_URL = "IMG_URL";
    //public static final String JSON = "JSON";
    public static final String MOVIE = "MOVIE";
    ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies){
        this.movies = movies;
    }


    /**
     * This function is called only enough times to cover the screen with views.  After
     * that point, it recycles the views when scrolling is done.
     * @param parent the intended parent object (our RecyclerView)
     * @param viewType unused in our function (enables having different kinds of views in the same RecyclerView)
     * @return the new ViewHolder we allocate
     */
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final View movieListItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.movie_item_list, parent, false);
        return new ViewHolder(movieListItem);
    }


    /**
     * This function gets called each time a ViewHolder needs to hold data for a different
     * position in the list.  We don't need to create any views (because we're recycling), but
     * we do need to update the contents in the views.
     *
     * 4.4.17 added onclick listener that calls detail activity to load the extended view
     * @param holder the ViewHolder that knows about the Views we need to update
     * @param position the index into the array of NewsArticles
     */
    public void onBindViewHolder(ViewHolder holder, int position){
        final Movie currentMovie = movies.get(position);

        holder.titleView.setText(currentMovie.getOriginal_title());
        holder.popularityView.setText(currentMovie.getPopularity());
        Picasso.with(holder.posterView.getContext()).load("http://image.tmdb.org/t/p/w92/" +
                currentMovie.getPoster_path()).into(holder.posterView);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Code for launching an Implicit Intent to view a URL in a browser
//                Uri uri = Uri.parse(newsArticle.getUrl());
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
//                v.getContext().startActivity(browserIntent);

                // Code for launching an Explicit Intent to go to another Activity in
                // the same App.
                Intent intent = new Intent(v.getContext(), DetailActivity.class);

                /* Code for passing data as a collection of primitives; this is fine
                 * if you are only passing a few values. */
//                intent.putExtra(TITLE, newsArticle.getTitle());
//                intent.putExtra(AUTHOR, newsArticle.getAuthor());
//                intent.putExtra(BODY, newsArticle.getDescription());
//                intent.putExtra(IMG_URL, newsArticle.getUrlToImage());



                /* Pass data as a Parcelable Plain-Old Java Object (POJO) */
                intent.putExtra(MOVIE, currentMovie);
                v.getContext().startActivity(intent);
            }
        });
    }

    /**
     * A ViewHolder class for our adapter that 'caches' the references to the
     * subviews, so we don't have to look them up each time.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView titleView;
        public TextView popularityView;
        public ImageView posterView;

        public ViewHolder(View itemView){
            super(itemView);
            view = itemView;
            titleView = (TextView) itemView.findViewById(R.id.titleTextView);
            popularityView = (TextView) itemView.findViewById(R.id.popularityTextView);
            posterView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }

    /**
     * RecyclerView wants to know how many list items there are, so it knows when it gets to the
     * end of the list and should stop scrolling.
     * @return the number of NewsArticles in the array.
     */
    public int getItemCount(){
        return movies.size();
    }
}
