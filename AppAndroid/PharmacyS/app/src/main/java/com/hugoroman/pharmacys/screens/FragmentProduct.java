package com.hugoroman.pharmacys.screens;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.data.DBConnector;
import com.hugoroman.pharmacys.model.Product;

import java.sql.Date;

public class FragmentProduct extends Fragment implements View.OnClickListener {

    private static final Slide enterAnim = new Slide(Gravity.LEFT);
    private static final Slide exitAnim = new Slide(Gravity.TOP);

    private View view;
    private Product product;
    private FABToolbarLayout morph;

    private ImageView productPhoto;
    private TextView productName;
    private TextView productCategory;
    private TextView productDescription;
    private TextView productLaboratory;
    private TextView productSizeUnit;
    private TextView productExpDate;
    private TextView productAvailables;

    private ImageView substractQuantity;
    private TextView quantitySelector;
    private ImageView addQuantity;
    private ImageView addReserveAction;

    private int quantity;
    private int maxQuantity;

    private String pharmacyCif;

    public FragmentProduct() {
        // Required empty public constructor
        this.setEnterTransition(enterAnim);
        this.setExitTransition(exitAnim);
    }

    public void setProduct(Product product) {

        this.product = product;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout para este Fragment
        super.onCreateView(inflater, container, savedInstanceState);

        // Mantener el Fragment y los datos a cambios de orientación de pantalla
        setRetainInstance(true);

        view = inflater.inflate(R.layout.fragment_product, container, false);

        pharmacyCif = getArguments().getString("PH_CIF");

        if(product != null && pharmacyCif != null)
            maxQuantity = new DBConnector(this.getContext()).getInventoryQuantity(pharmacyCif, product.getId());

        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rel_product);

        relativeLayout.setOnClickListener(this);

        productPhoto = (ImageView) view.findViewById(R.id.pro_photo);
        productName = (TextView) view.findViewById(R.id.pro_name);
        productDescription = (TextView) view.findViewById(R.id.pro_description);
        productCategory = (TextView) view.findViewById(R.id.pro_category);
        productLaboratory = (TextView) view.findViewById(R.id.pro_laboratory);
        productSizeUnit = (TextView) view.findViewById(R.id.pro_size_unit);
        productExpDate = (TextView) view.findViewById(R.id.pro_expiration_date);
        productAvailables = (TextView) view.findViewById(R.id.pro_availables);

        if(product != null) {
            productName.setText(product.getName());

            DBConnector dbConnector = new DBConnector(getContext());
            productCategory.setText(dbConnector.getProductCategoryName(product.getId()));

            productDescription.setText(product.getDescription());
            productLaboratory.setText(product.getLaboratory());
            productSizeUnit.setText(product.getSizeUnits() + " " + product.getUnits());

            Date expDate = product.getExpiration_date();
            if(expDate.getTime() != 0)
                productExpDate.setText("Expiration date: " + expDate.toString());
            else {
                relativeLayout.removeView(productExpDate);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.BELOW, R.id.pro_size_unit);

                productAvailables.setLayoutParams(layoutParams);
            }

            productAvailables.setText("Availables: " + maxQuantity);

        }

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add_basket);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.simple_grow);
        fab.setAnimation(animation);

        morph = (FABToolbarLayout) view.findViewById(R.id.fabtoolbar);

        fab.setOnClickListener(this);

        if(maxQuantity > 0)
            quantity = 1;
        else
            quantity = 0;

        substractQuantity = (ImageView) view.findViewById(R.id.substract_count);
        quantitySelector = (TextView) view.findViewById(R.id.count);
        addQuantity = (ImageView) view.findViewById(R.id.add_count);
        addReserveAction = (ImageView) view.findViewById(R.id.add_reserve);

        substractQuantity.setOnClickListener(this);
        addQuantity.setOnClickListener(this);
        addReserveAction.setOnClickListener(this);

        if(quantity != 0) {
            substractQuantity.setImageAlpha(0);
            quantitySelector.setText("1");
        }
        else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_reservations, getActivity().getTheme()));

            substractQuantity.setImageAlpha(0);
            addQuantity.setImageAlpha(0);

            ((LinearLayout) view.findViewById(R.id.fabtoolbar_toolbar)).removeView(substractQuantity);
            ((LinearLayout) view.findViewById(R.id.fabtoolbar_toolbar)).removeView(addQuantity);

            quantitySelector.setTextSize(30.0f);
            quantitySelector.setText(R.string.reserve);

            addReserveAction.setImageDrawable(getResources().getDrawable(R.drawable.ic_reservations, getActivity().getTheme()));
        }

        return view;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.fab_add_basket:
                // Abrir selector de cantidad de elementos
                morph.show();
                break;
            case R.id.rel_product:
                morph.hide();
                break;
            case R.id.substract_count:
                // Cambiar el número de unidades que se puede tramitar restando 1
                if(quantity > 1) {
                    quantity--;

                    if(quantity == 1)
                        substractQuantity.setImageAlpha(0);
                    else
                        substractQuantity.setImageAlpha(1000);

                    quantitySelector.setText(String.valueOf(quantity));
                }
                addQuantity.setImageAlpha(1000);
                break;
            case R.id.add_count:
                // Cambiar el número de unidades que se puede tramitar sumando 1
                if(quantity < maxQuantity) {
                    quantity++;

                    if(quantity == maxQuantity)
                        addQuantity.setImageAlpha(0);
                    else
                        addQuantity.setImageAlpha(1000);

                    quantitySelector.setText(String.valueOf(quantity));
                }

                substractQuantity.setImageAlpha(1000);
                break;
            case R.id.add_reserve:
                if(quantity > 0) {
                    // Añadirlo con toda la cantidad a la base de datos de la cesta
                    DBConnector dbConnector = new DBConnector(getContext());

                    dbConnector.addToBasket(pharmacyCif, product.getId(), quantity);

                    // Notificar al usuario que ha sido, o no, correctamente añadido a la cesta
                    Toast.makeText(getContext(), getResources().getString(R.string.insert_basket), Toast.LENGTH_LONG).show();
                }
                else {
                    // Procesar la reserva

                    // Notificar al usuario que ha sido, o no, correctamente añadido como reserva
                    Toast.makeText(getContext(), getResources().getString(R.string.insert_reservations), Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
