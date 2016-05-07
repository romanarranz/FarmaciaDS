package com.hugoroman.pharmacys.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.data.DBConnector;
import com.hugoroman.pharmacys.model.Inventory;

import java.util.List;


public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private List<Inventory> inventories;
    private static ClickListener clickListener;

    public InventoryAdapter(List<Inventory> inventories) {

        this.inventories = inventories;
    }

    public static class InventoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView categoryName;

        public InventoryViewHolder(View itemView) {
            super(itemView);

            categoryName = (TextView) itemView.findViewById(R.id.category_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {

        InventoryAdapter.clickListener = clickListener;
    }

    @Override
    public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);

        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InventoryViewHolder holder, int position) {

        DBConnector dbConnector = new DBConnector(holder.itemView.getContext());

        String categoryName = dbConnector.getProductCategoryName(inventories.get(position).getProductID());

        holder.categoryName.setText(categoryName);
    }

    @Override
    public int getItemCount() {

        return inventories.size();
    }
}
