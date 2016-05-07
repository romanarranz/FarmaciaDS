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
import com.hugoroman.pharmacys.adapters.ClickListener;
import com.hugoroman.pharmacys.adapters.InventoryAdapter;
import com.hugoroman.pharmacys.data.DBConnector;
import com.hugoroman.pharmacys.model.Inventory;

import java.util.List;

public class FragmentInventory extends Fragment {

    private static final Slide enterAnim = new Slide(Gravity.BOTTOM);
    private static final Slide exitAnim = new Slide(Gravity.TOP);

    private View view;
    private String pharmacyCif;

    public FragmentInventory() {
        // Required empty public constructor
        this.setEnterTransition(enterAnim);
        this.setExitTransition(exitAnim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_inventory, container, false);

        //setRetainInstance(true);

        pharmacyCif = getArguments().getString("PH_CIF");

        DBConnector dbConnector = new DBConnector(this.getContext());

        final List<Inventory> inventories = dbConnector.getPharmacyInventory(pharmacyCif);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.inventory_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        InventoryAdapter inventoryAdapter = new InventoryAdapter(inventories);

        inventoryAdapter.setOnItemClickListener(new ClickListener() {

            @Override
            public void onItemClick(int position, View v) {

                // Añadir los eventos que tienen que ocurrir cuando se pulse algún CardView del RecyclerView
                FragmentProducts fragmentProducts = new FragmentProducts();

                DBConnector dbConnector = new DBConnector(getContext());

                int categoryID = dbConnector.getProductCategoryId(inventories.get(position).getProductID());

                Bundle bundle = new Bundle();

                bundle.putInt("CATEGORY_ID", categoryID);
                bundle.putString("PH_CIF", pharmacyCif);

                fragmentProducts.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentProducts).addToBackStack(null).commit();

                ((MainActivity) getActivity()).setMenuItemCheck(fragmentProducts);
            }
        });

        recyclerView.setAdapter(inventoryAdapter);

        return view;
    }
}
