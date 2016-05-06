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
    private CardView cardView1;

    public FragmentMain() {
        // Required empty public constructor
        this.setEnterTransition(enterAnim);
        this.setExitTransition(exitAnim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_main, container, false);

        cardView1 = (CardView) view.findViewById(R.id.cv1);

        cardView1.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;

        switch(v.getId()) {
            case R.id.cv1:
                fragment = new FragmentPharmacies();
                break;
        }

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

        ((MainActivity) getActivity()).setMenuItemCheck(fragment);
    }
}