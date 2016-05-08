package com.hugoroman.pharmacys.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.model.Pharmacy;
import com.hugoroman.pharmacys.model.Product;
import com.hugoroman.pharmacys.model.Reservation;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private Reservation reservation;
    private static LongClickListener clickListener;

    public ReservationAdapter(Reservation reservation) {

        this.reservation = reservation;
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public CardView reservationCardView;
        public TextView reservationProductName;
        public TextView reservationPharmacyName;
        public TextView reservationQuantity;

        public ReservationViewHolder(View itemView) {
            super(itemView);

            reservationCardView = (CardView) itemView.findViewById(R.id.reservation_cv);
            reservationProductName = (TextView) itemView.findViewById(R.id.reservation_product_name);
            reservationPharmacyName = (TextView) itemView.findViewById(R.id.reservation_pharmacy_name);
            reservationQuantity = (TextView) itemView.findViewById(R.id.reservation_quantity);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {

            ReservationAdapter.clickListener.onItemClick(getAdapterPosition(), view);
        }

        @Override
        public boolean onLongClick(View view) {

            return ReservationAdapter.clickListener.onLongItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(LongClickListener clickListener) {

        ReservationAdapter.clickListener = clickListener;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_layout, parent, false);

        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {

        Pharmacy pharmacy = (Pharmacy) reservation.getProductsPharmaciesQuantities().get(position).get(0);
        Product product = (Product) reservation.getProductsPharmaciesQuantities().get(position).get(1);
        Integer quantity = (Integer) reservation.getProductsPharmaciesQuantities().get(position).get(2);

        holder.reservationCardView.setLongClickable(true);
        holder.reservationProductName.setText(product.getName());
        holder.reservationPharmacyName.setText(pharmacy.getName());
        holder.reservationQuantity.setText(quantity.toString());

    }

    @Override
    public int getItemCount() {

        return reservation.getProductsPharmaciesQuantities().size();
    }
}