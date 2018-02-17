package ru.samsung.recyclerview;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by aleksej on 14.02.2018.
 */

public class Film {

    String name;
    int year;
    String url;
    Bitmap poster;

    public Film(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Film(String name, int year, String url) {
        this.name = name;
        this.year = year;
        this.url = url;
    }

    public Film() {

    }

    public Bitmap getPoster() {
        return poster;
    }

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
