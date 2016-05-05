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

    public PharmaciesAdapter(List<Pharmacy> pharmacies) {

        this.pharmacies = pharmacies;
    }

    public static class PharmaciesViewHolder extends RecyclerView.ViewHolder {

        public static CardView pharmacyCV;
        public static TextView pharmacyName;
        public static TextView pharmacyPhone;

        public PharmaciesViewHolder(View view) {
            super(view);

            pharmacyCV = (CardView) view.findViewById(R.id.pharmacy_cv);
            pharmacyName = (TextView) view.findViewById(R.id.pharmacy_name);
            pharmacyPhone = (TextView) view.findViewById(R.id.pharmacy_phone);

        }
    }

    @Override
    public PharmaciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacies_layout, parent, false);

        return new PharmaciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PharmaciesViewHolder holder, int position) {

        PharmaciesViewHolder.pharmacyName.setText(pharmacies.get(position).getName());
        PharmaciesViewHolder.pharmacyPhone.setText("Phone: " + pharmacies.get(position).getPhoneNumber().toString());

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
