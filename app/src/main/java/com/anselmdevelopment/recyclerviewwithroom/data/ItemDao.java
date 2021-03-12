package com.anselmdevelopment.recyclerviewwithroom.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.anselmdevelopment.recyclerviewwithroom.model.Item;

import java.util.List;

@Dao
public interface ItemDao {

    // CRUD
    @Insert
    void insert(Item item);

    @Query("DELETE FROM item_table")
    void deleteAll();

    @Query("DELETE FROM item_table WHERE id = :id")
    int deleteItem(int id);

    @Query("UPDATE item_table SET item_column = :itemText WHERE id = :id")
    int updateItem(int id, String itemText);

    @Query("SELECT * FROM item_table ORDER BY item_column DESC")
    LiveData<List<Item>> getAllItems();
}
