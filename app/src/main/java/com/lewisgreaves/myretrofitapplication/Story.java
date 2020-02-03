package com.lewisgreaves.myretrofitapplication;

import com.google.gson.annotations.SerializedName;

/*
 * Created by @Mayakovsky28 on 02 02 2020.
 */

public class Story {

    private String url;

    private String byLine;

    private String title;

    @SerializedName("abstract")
    private String briefDescription;

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