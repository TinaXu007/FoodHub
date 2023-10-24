package com.wacmob.foodhub.data.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


/**
 * Created by KP on 12/14/2018.
 */

public class SampleModel extends BaseObservable{
    private String title;
    private String desc;
    private String url;

    public SampleModel(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public SampleModel(String title, String desc, String url) {
        this.title = title;
        this.desc = desc;
        this.url = url;
    }

    public SampleModel(String title) {
        this.title = title;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        notifyPropertyChanged(BR.desc);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }
}
