package com.hugoroman.pharmacys.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.model.Pharmacy;

import java.util.List;

public class PharmaciesAdapter extends RecyclerView.Adapter<PharmaciesAdapter.PharmaciesViewHolder> {

    private List<Pharmacy> pharmacies;
    private static ClickListener clickListener;

    public PharmaciesAdapter(List<Pharmacy> pharmacies) {

        this.pharmacies = pharmacies;
    }

    public static class PharmaciesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView pharmacyName;
        public TextView pharmacyPhone;

        public PharmaciesViewHolder(View itemView) {
            super(itemView);

            pharmacyName = (TextView) itemView.findViewById(R.id.pharmacy_name);
            pharmacyPhone = (TextView) itemView.findViewById(R.id.pharmacy_phone);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {

        PharmaciesAdapter.clickListener = clickListener;
    }

    @Override
    public PharmaciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacies_layout, parent, false);

        return new PharmaciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PharmaciesViewHolder holder, final int position) {

        holder.pharmacyName.setText(pharmacies.get(position).getName());
        holder.pharmacyPhone.setText("Phone: " + pharmacies.get(position).getPhoneNumber().toString());

    }

    @Override
    public int getItemCount() {

        return pharmacies.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);
    }
}
