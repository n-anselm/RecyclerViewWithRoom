package com.anselmdevelopment.recyclerviewwithroom.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anselmdevelopment.recyclerviewwithroom.R;
import com.anselmdevelopment.recyclerviewwithroom.model.Item;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {

    private final LayoutInflater itemInflater;
    private List<Item> itemList; // Cached copy of items

    public ItemListAdapter(Context context) {
        itemInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = itemInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        if (itemList != null) {
            Item current = itemList.get(position);
            itemViewHolder.textView.setText(current.getItem());
        } else {
            itemViewHolder.textView.setText("No item!");
        }
    }

    public void setItems(List<Item> items) {
        itemList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (itemList != null)
            return itemList.size();
        else return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }
}
