package com.hugoroman.pharmacys.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hugoroman.pharmacys.R;

public class FragmentMain extends Fragment implements View.OnClickListener {

    private static final Slide enterAnim = new Slide(Gravity.LEFT);
    private static final Slide exitAnim = new Slide(Gravity.BOTTOM);

    private View view;
    private CardView pharmaciesCardView;
    private CardView mapCardView;
    private CardView basketCardView;
    private CardView ordersCardView;
    private CardView reservationsCardView;

    public FragmentMain() {
        // Required empty public constructor
        this.setEnterTransition(enterAnim);
        this.setExitTransition(exitAnim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_main, container, false);

        pharmaciesCardView = (CardView) view.findViewById(R.id.pharmacies_cv);
        mapCardView = (CardView) view.findViewById(R.id.map_cv);
        basketCardView = (CardView) view.findViewById(R.id.basket_cv);
        ordersCardView = (CardView) view.findViewById(R.id.orders_cv);
        reservationsCardView = (CardView) view.findViewById(R.id.reservations_cv);

        pharmaciesCardView.setOnClickListener(this);
        mapCardView.setOnClickListener(this);
        basketCardView.setOnClickListener(this);
        ordersCardView.setOnClickListener(this);
        reservationsCardView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;

        switch(v.getId()) {
            case R.id.pharmacies_cv:
                fragment = new FragmentPharmacies();
                break;
            case R.id.map_cv:

                break;
            case R.id.basket_cv:
                fragment = new FragmentBasket();
                break;
            case R.id.orders_cv:

                break;
            case R.id.reservations_cv:

                break;
        }

        if(fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

            ((MainActivity) getActivity()).setMenuItemCheck(fragment);
        }
    }
}