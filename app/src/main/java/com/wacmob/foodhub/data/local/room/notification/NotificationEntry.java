package com.wacmob.foodhub.data.local.room.notification;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.Nullable;


/**
 * Created by KP on 1/19/2019.
 */

@Entity(tableName = "notification_tb")
public class NotificationEntry {

    @PrimaryKey
    private int id;

    @Nullable
    private String title;

    @Nullable
    private Integer entryId;

    @Nullable
    private String desc;

    @Nullable
    private String image;

    @Nullable
    private String date;

    @Nullable
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getDesc() {
        return desc;
    }

    public void setDesc(@Nullable String desc) {
        this.desc = desc;
    }

    @Nullable
    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(@Nullable Integer entryId) {
        this.entryId = entryId;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    @Nullable
    public String getDate() {
        return date;
    }

    public void setDate(@Nullable String date) {
        this.date = date;
    }

    @Nullable
    public String getTime() {
        return time;
    }

    public void setTime(@Nullable String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
