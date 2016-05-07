package com.hugoroman.pharmacys.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.model.Basket;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private Basket basket;
    private static ClickListener clickListener;

    public BasketAdapter(Basket basket) {

        this.basket = basket;
    }

    public static class BasketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        public BasketViewHolder(View itemView) {
            super(itemView);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {

        BasketAdapter.clickListener = clickListener;
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);

        return new BasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BasketViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {

        return basket.getProducts().size();
    }
}