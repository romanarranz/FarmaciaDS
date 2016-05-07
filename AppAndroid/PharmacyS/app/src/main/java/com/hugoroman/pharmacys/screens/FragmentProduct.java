package com.hugoroman.pharmacys.screens;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hugoroman.pharmacys.R;

public class FragmentProduct extends Fragment {

    private static final Slide enterAnim = new Slide(Gravity.LEFT);
    private static final Slide exitAnim = new Slide(Gravity.TOP);

    private View view;

    public FragmentProduct() {
        // Required empty public constructor
        this.setEnterTransition(enterAnim);
        this.setExitTransition(exitAnim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout para este Fragment
        super.onCreateView(inflater, container, savedInstanceState);

        // Mantener el Fragment y los datos a cambios de orientación de pantalla
        setRetainInstance(true);

        view = inflater.inflate(R.layout.fragment_product, container, false);

        FloatingActionButton FAB = (FloatingActionButton) view.findViewById(R.id.fab_add_basket);

        FAB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Añadirlo a la base de datos de la cesta

                // Notificar al usuario que ha sido, o no, correctamente añadido
            }
        });

        return view;
    }
}
