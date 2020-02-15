package com.lewisgreaves.myretrofitapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * Created by @Mayakovsky28 on 02 02 2020.
 */
public class Story implements Serializable {

    @SerializedName("url")
    private String url;

    @SerializedName("byline")
    private String byLine;

    @SerializedName("title")
    private String title;

    @SerializedName("abstract")
    private String briefDescription;

    @SerializedName("published_date")
    private String publishedDate;

    public String getUrl() {
        return url;
    }

    public String getByLine() {
        return byLine;
    }

    public String getTitle() {
        return title;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public String getPublishedDate() {
        return publishedDate;
    }
}
