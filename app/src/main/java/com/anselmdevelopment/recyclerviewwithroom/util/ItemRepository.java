package com.anselmdevelopment.recyclerviewwithroom.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.anselmdevelopment.recyclerviewwithroom.data.ItemDao;
import com.anselmdevelopment.recyclerviewwithroom.data.ItemRoomDB;
import com.anselmdevelopment.recyclerviewwithroom.model.Item;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;
    private LiveData<List<Item>> allItems;

    public ItemRepository(Application application) {
        ItemRoomDB db = ItemRoomDB.getDatabase(application);
        itemDao = db.itemDao();
        allItems = itemDao.getAllItems();
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    public void insert(Item item) {
        new insertAsyncTask(itemDao).execute(item);
    }

    private class insertAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao asyncTaskDao;

        public insertAsyncTask(ItemDao itemDao) {
            asyncTaskDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
