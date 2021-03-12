package com.anselmdevelopment.recyclerviewwithroom.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.anselmdevelopment.recyclerviewwithroom.model.Item;

@Database(entities = {Item.class}, version = 1)
public abstract class ItemRoomDB extends RoomDatabase {

    public static volatile ItemRoomDB INSTANCE;

    public abstract ItemDao itemDao();

    public static ItemRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ItemRoomDB.class) {
                if (INSTANCE == null) {
                    // Create the database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemRoomDB.class, "item_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
