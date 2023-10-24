package com.wacmob.foodhub.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wacmob.foodhub.data.local.room.notification.NotificationEntry;
import com.wacmob.foodhub.data.models.SearchItemListModel;

/**
 * Created by KP on 1/19/2019.
 */

@Database(entities = {NotificationEntry.class, SearchItemListModel.class}, version = 1, exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "musicapp.db";

    public abstract FoodDao musicDao();
}
