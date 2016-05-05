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
    private static final Slide exitAnim = new Slide(Gravity.RIGHT);
    private View view;
    private CardView cardView1;

    public FragmentMain() {
        // Required empty public constructor
        this.setEnterTransition(new Slide(Gravity.LEFT));
        this.setExitTransition(new Slide(Gravity.BOTTOM));
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

        fragment.setEnterTransition(enterAnim);
        fragment.setExitTransition(exitAnim);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

        ((MainActivity) getActivity()).setMenuItemCheck(fragment);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

}