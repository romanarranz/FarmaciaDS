package com.hugoroman.pharmacys.screens;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.adapters.BasketAdapter;
import com.hugoroman.pharmacys.adapters.LongClickListener;
import com.hugoroman.pharmacys.data.DBConnector;
import com.hugoroman.pharmacys.model.Basket;
import com.hugoroman.pharmacys.model.Pharmacy;
import com.hugoroman.pharmacys.model.Product;

import java.util.ArrayList;

public class FragmentBasket extends Fragment {

    private static final Slide enterAnim = new Slide(Gravity.BOTTOM);
    private static final Slide exitAnim = new Slide(Gravity.BOTTOM);

    private View view;
    private TextView emptyBasket;
    private Basket basket;
    private FloatingActionButton fab;
    private boolean modeSelection;
    private ArrayList<Integer> selections;

    public FragmentBasket() {
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

        view = inflater.inflate(R.layout.fragment_basket, container, false);

        final DBConnector dbConnector = new DBConnector(this.getContext());

        basket = dbConnector.getBasket();
        emptyBasket = (TextView) view.findViewById(R.id.basket_empty);
        modeSelection = false;
        selections = new ArrayList<Integer>();

        if(basket.getProductsPharmaciesQuantities().size() != 0) {
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frame_basket);

            frameLayout.removeView(emptyBasket);
        }

        fab = (FloatingActionButton) view.findViewById(R.id.fab_delete);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.simple_grow);
        fab.setAnimation(animation);
        fab.animate();
        fab.hide();


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.basket_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        final BasketAdapter basketAdapter = new BasketAdapter(basket);

        basketAdapter.setOnItemClickListener(new LongClickListener() {

            @Override
            public boolean onLongItemClick(int position, View v) {

                if(!modeSelection) {
                    toggle(position, (CardView) v);

                    fab.show();
                }

                modeSelection = true;

                return true;
            }

            @Override
            public void onItemClick(int position, View v) {

                if(!modeSelection) {
                    // Añadir los eventos que tienen que ocurrir cuando se pulse algún CardView del RecyclerView
                    FragmentProduct fragmentProduct = new FragmentProduct();

                    Bundle bundle = new Bundle();

                    Pharmacy pharmacy = (Pharmacy) basket.getProductsPharmaciesQuantities().get(position).get(0);

                    bundle.putString("PH_CIF", pharmacy.getCif());

                    fragmentProduct.setArguments(bundle);

                    Product product = (Product) basket.getProductsPharmaciesQuantities().get(position).get(1);

                    fragmentProduct.setProduct(product);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentProduct).addToBackStack(null).commit();

                    ((MainActivity) getActivity()).setMenuItemCheck(fragmentProduct);
                }
                else {
                    toggle(position, (CardView) v);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for(Integer position : selections) {
                    Pharmacy pharmacy = (Pharmacy) basket.getProductsPharmaciesQuantities().get(position).get(0);
                    Product product = (Product) basket.getProductsPharmaciesQuantities().get(position).get(1);

                    dbConnector.removeFromBasket(pharmacy.getCif(), product.getId());

                    basket.getProductsPharmaciesQuantities().remove(position);
                    basketAdapter.swap(basket);

                    fab.hide();

                    if(basket.getProductsPharmaciesQuantities().size() == 0) {
                        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frame_basket);

                        frameLayout.addView(emptyBasket);
                    }
                }
            }
        });

        recyclerView.setAdapter(basketAdapter);

        return view;
    }

    public void toggle(int position, CardView cv) {

        if(!selections.contains((Integer) position)) {
            cv.setCardBackgroundColor(getResources().getColor(R.color.colorAccent));
            selections.add(position);
        }
        else {
            cv.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryLogin));
            selections.remove((Integer) position);

            if(selections.size() == 0) {
                modeSelection = false;

                fab.hide();
            }
        }
    }
}
