package com.hugoroman.pharmacys.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.model.Pharmacy;


public class FragmentPharmacy extends Fragment {

    private static final Slide enterAnim = new Slide(Gravity.RIGHT);
    private static final Slide exitAnim = new Slide(Gravity.TOP);

    private View view;
    private Pharmacy pharmacy;
    private TextView pharmacyName;
    private TextView pharmacyPhone;
    private TextView pharmacyDescription;
    private TextView pharmacyOpeningtime;
    private Button catalogButton;

    public FragmentPharmacy() {
        // Required empty public constructor
        this.setEnterTransition(enterAnim);
        this.setExitTransition(exitAnim);
    }

    public void setPharmacy(Pharmacy pharmacy) {

        this.pharmacy = pharmacy;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout para este Fragment
        super.onCreateView(inflater, container, savedInstanceState);

        // Mantener el Fragment y los datos a cambios de orientación de pantalla
        setRetainInstance(true);

        view = inflater.inflate(R.layout.fragment_pharmacy, container, false);

        pharmacyName = (TextView) view.findViewById(R.id.ph_name);
        pharmacyPhone = (TextView) view.findViewById(R.id.ph_phone);
        pharmacyDescription = (TextView) view.findViewById(R.id.ph_description);
        pharmacyOpeningtime = (TextView) view.findViewById(R.id.ph_opening_time);
        catalogButton = (Button) view.findViewById(R.id.ph_catalog_button);

        if(pharmacy != null) {
            pharmacyName.setText(pharmacy.getName());
            pharmacyPhone.setText(String.valueOf(pharmacy.getPhoneNumber()));
            pharmacyDescription.setText(pharmacy.getDescription());
            pharmacyOpeningtime.setText(pharmacy.getStartSchedule() + ":00 - " + pharmacy.getEndSchedule() + ":00");
        }

        catalogButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentInventory fragment = new FragmentInventory();

                Bundle bundle = new Bundle();

                bundle.putString("PH_CIF", pharmacy.getCif());

                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

                ((MainActivity) getActivity()).setMenuItemCheck(fragment);
            }
        });

        return view;
    }
}
