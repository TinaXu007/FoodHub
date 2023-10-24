package com.wacmob.foodhub.data.local.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wacmob.foodhub.data.local.room.notification.NotificationEntry;
import com.wacmob.foodhub.data.models.SearchItemListModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by KP on 1/19/2019.
 */

@Dao
public interface FoodDao {

    @Query("SELECT * FROM notification_tb")
    Single<List<NotificationEntry>> getAllNotification();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NotificationEntry notification);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultiple(List<NotificationEntry> notification);

    @Delete
    void remove(NotificationEntry notification);

    @Query("SELECT count(*) FROM notification_tb where id LIKE :id")
    int isNotification(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(SearchItemListModel item);

    @Query("select * from SearchItemListModel")
    Single<List<SearchItemListModel>> fetchItems();
}
