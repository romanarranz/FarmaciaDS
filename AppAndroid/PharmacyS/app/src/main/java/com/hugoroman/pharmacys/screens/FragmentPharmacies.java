package com.hugoroman.pharmacys.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.adapters.PharmaciesAdapter;
import com.hugoroman.pharmacys.data.DBConnector;
import com.hugoroman.pharmacys.model.Pharmacy;

import java.util.List;

public class FragmentPharmacies extends Fragment {

    private View view;

    public FragmentPharmacies() {
        // Required empty public constructor
        this.setEnterTransition(new Slide(Gravity.RIGHT));
        this.setExitTransition(new Slide(Gravity.LEFT));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_pharmacies, container, false);

        DBConnector dbConnector = new DBConnector(this.getContext());

        List<Pharmacy> pharmacies = dbConnector.getAllPharmacies();

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.pharmacies_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        rv.setLayoutManager(linearLayoutManager);

        PharmaciesAdapter pharmaciesAdapter = new PharmaciesAdapter(pharmacies);
        rv.setAdapter(pharmaciesAdapter);

        return view;
    }
}