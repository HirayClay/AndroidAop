package com.hiray.mvvm.androidaop.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hiray on 2018/5/16.
 *
 * @author hiray
 */
public class AndroidItemModel {


    /**
     * _id : 5a967b41421aa91071b838f7
     * createTime : 2018-02-28T17:49:53.265Z
     * desc : MusicLibrary-一个丰富的音频播放SDK
     * publishTime : 2018-03-12T08:44:50.326Z
     * source : web
     * type : Android
     * url : https://github.com/lizixian18/MusicLibrary
     * used : true
     * who : lizixian
     */

    @SerializedName("_id")
    public String id;
    @SerializedName("createTime")
    public String createTime;
    @SerializedName("desc")
    public String desc;
    @SerializedName("publishTime")
    public String publishTime;
    @SerializedName("source")
    public String source;
    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String repo;
    @SerializedName("used")
    public boolean used;
    @SerializedName("who")
    public String author;
}
