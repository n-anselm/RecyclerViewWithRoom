package com.anselmdevelopment.recyclerviewwithroom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anselmdevelopment.recyclerviewwithroom.model.Item;
import com.anselmdevelopment.recyclerviewwithroom.model.ItemViewModel;
import com.anselmdevelopment.recyclerviewwithroom.ui.ItemListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemListAdapter itemListAdapter;

    private ItemViewModel itemViewModel;

    private static final int NEW_ITEM_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        itemListAdapter = new ItemListAdapter(this);
        // Pass the adapter into the recyclerview
        recyclerView.setAdapter(itemListAdapter);
        // Set the layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            startActivityForResult(new Intent(MainActivity.this, NewItemActivity.class),
                    NEW_ITEM_REQUEST_CODE);
        });

        itemViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                // Update the cached copy of items in the adapter
                itemListAdapter.setItems(items);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            Item item = new Item(data.getStringExtra(NewItemActivity.EXTRA_REPLY));
            itemViewModel.insert(item);
        } else {
            Toast.makeText(this, "Not saved!", Toast.LENGTH_SHORT).show();
        }
    }
}