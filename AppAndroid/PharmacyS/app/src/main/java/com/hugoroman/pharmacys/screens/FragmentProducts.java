package com.hugoroman.pharmacys.screens;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.adapters.ClickListener;
import com.hugoroman.pharmacys.adapters.ProductsAdapter;
import com.hugoroman.pharmacys.data.DBConnector;
import com.hugoroman.pharmacys.model.Product;

import java.util.List;

// https://danielme.com/2015/09/13/diseno-android-endless-recyclerview/  HACER PAGINACIÓN DE PRODUCTOS DE 20 EN 20

public class FragmentProducts extends Fragment {

    private static final Slide enterAnim = new Slide(Gravity.RIGHT);
    private static final Slide exitAnim = new Slide(Gravity.RIGHT);

    private View view;
    private int categoryID;

    public FragmentProducts() {
        // Required empty public constructor
        this.setEnterTransition(enterAnim);
        this.setExitTransition(exitAnim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout para este Fragment
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_products, container, false);

        categoryID = getArguments().getInt("CATEGORY_ID");


        DBConnector dbConnector = new DBConnector(this.getContext());

        final List<Product> products = dbConnector.getAllProductsByCategoryId(categoryID);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.products_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        ProductsAdapter productsAdapter = new ProductsAdapter(products);

        productsAdapter.setOnItemClickListener(new ClickListener() {

            @Override
            public void onItemClick(int position, View v) {

                // Añadir los eventos que tienen que ocurrir cuando se pulse algún CardView del RecyclerView
                FragmentProduct fragmentProduct = new FragmentProduct();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentProduct).addToBackStack(null).commit();

                ((MainActivity) getActivity()).setMenuItemCheck(fragmentProduct);
            }
        });

        recyclerView.setAdapter(productsAdapter);

        FloatingActionButton FAB = (FloatingActionButton) view.findViewById(R.id.fab_basket);

        FAB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentBasket fragmentBasket = new FragmentBasket();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentBasket).addToBackStack(null).commit();

                ((MainActivity) getActivity()).setMenuItemCheck(fragmentBasket);
            }
        });

        return view;
    }
}

