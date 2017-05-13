package com.example.allentang.moviedb;


import android.os.Parcel;
import android.os.Parcelable;
/**
 * POJO class for each individual movie. We only need 3 data types sinche that's all we process,
 * we only use getters since all the setting is done through GSON.
 * @author Allen Tang
 */

public class Movie implements Parcelable{
    private String original_title;
    private String popularity;
    private String poster_path;
    private String vote_average;
    private String overview;

    public String getOverview() {return overview;}

    public String getVote_average() {return vote_average;}

    public String getOriginal_title() {
        return original_title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original_title);
        dest.writeString(this.popularity);
        dest.writeString(this.poster_path);
        dest.writeString(this.vote_average);
        dest.writeString(this.overview);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.original_title = in.readString();
        this.popularity = in.readString();
        this.poster_path = in.readString();
        this.vote_average = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
