package com.hugoroman.pharmacys.screens;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.hugoroman.pharmacys.R;
import com.hugoroman.pharmacys.data.DBPharmacyS;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 0;
    private static final String NAV_MENU_ITEM = "navItemId";
    private static final String ACTIONBAR_TITTLE = "actionBarTittle";
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;

    private SharedPreferences preferences;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private int navMenuItem;
    private String actionBarTittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);

        this.getApplicationContext().deleteDatabase(DBPharmacyS.DATABASE_NAME);

        preferences = getPreferences(MODE_PRIVATE);

        /*if(!preferences.contains("user-email")) {
            Toast.makeText(this, "Creating login activity", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
        }*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.drawer_nav);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        onRestoreInstanceState(savedInstanceState);

        navView.setItemIconTintList(null);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        boolean fragmentToStack = true;
                        Fragment fragment = null;

                        switch(menuItem.getItemId()) {
                            case R.id.navigation_item_1:
                                if(navMenuItem != R.id.navigation_item_1) {
                                    fragment = new FragmentMain();

                                    fragmentTransaction = true;

                                    // Vaciar la pila de Fragments, para si se pulsa en el botón atrás cerrar la App.
                                    cleanFragmentStack();

                                    fragmentToStack = false;
                                }
                                break;
                            case R.id.navigation_item_2:
                                if(navMenuItem != R.id.navigation_item_2) {
                                    fragment = new FragmentPharmacies();

                                    fragmentTransaction = true;
                                }
                                break;
                            case R.id.navigation_item_3:
                                fragment = new FragmentMap();

                                fragmentTransaction = true;
                                break;
                            case R.id.navigation_item_4:
                                if(navMenuItem != R.id.navigation_item_4) {
                                    fragment = new FragmentBasket();

                                    fragmentTransaction = true;
                                }
                                break;
                            case R.id.navigation_item_5:
                                fragmentTransaction = false;
                                break;
                            case R.id.navigation_item_6:
                                if(navMenuItem != R.id.navigation_item_6) {
                                    fragment = new FragmentReservation();

                                    fragmentTransaction = true;
                                }
                                break;
                            case R.id.navigation_item_settings:
                                return false;
                            case R.id.navigation_item_settings_1:
                                Toast.makeText(getApplicationContext(), "Option 1 Selected", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navigation_item_settings_2:
                                Toast.makeText(getApplicationContext(), "Option 2 Selected", Toast.LENGTH_SHORT).show();
                                break;
                        }

                        if(fragmentTransaction) {
                            if(fragmentToStack) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(String.valueOf(menuItem.getItemId())).commit();
                                else
                                    getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.content_frame, fragment).addToBackStack(String.valueOf(menuItem.getItemId())).commit();
                            }
                            else {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                                else
                                    getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.content_frame, fragment).commit();

                            }
                            menuItem.setChecked(true);
                            navMenuItem = menuItem.getItemId();

                            if(menuItem.getItemId() == R.id.navigation_item_1)
                                actionBarTittle = "PharmacyS";
                            else
                                actionBarTittle = menuItem.getTitle().toString();

                            getSupportActionBar().setTitle(actionBarTittle);
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                }
        );
    }

    // Capturar si se ha pulsado el icono de la hamburguesa para abrir el menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Capturar el evento cuando se loguee el usuario
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_LOGIN) {
            if(resultCode == RESULT_OK) {

                // Solicitar permisos Localización dinámicamente para Android Marshallow y N
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Guardar el menú actualmente seleccionado
        savedInstanceState.putInt(NAV_MENU_ITEM, navMenuItem);
        savedInstanceState.putString(ACTIONBAR_TITTLE, getSupportActionBar().getTitle().toString());

        Fragment fragment = getVisibleFragment();

        if(fragment != null)
            getSupportFragmentManager().putFragment(savedInstanceState, "FRAGMENT", fragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        Fragment fragment = null;

        if(savedInstanceState == null) {
            navMenuItem = R.id.navigation_item_1;
            actionBarTittle = "PharmacyS";

            fragment = new FragmentMain();
        }
        else {
            super.onRestoreInstanceState(savedInstanceState);

            navMenuItem = savedInstanceState.getInt(NAV_MENU_ITEM);
            actionBarTittle = savedInstanceState.getString(ACTIONBAR_TITTLE);

            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "FRAGMENT");
        }

        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

            if(navMenuItem > 0)
                navView.getMenu().findItem(navMenuItem).setChecked(true);
            else {
                for(int i=0; i<navView.getMenu().size(); i++)
                    navView.getMenu().getItem(i).setChecked(false);
            }
        }

        getSupportActionBar().setTitle(actionBarTittle);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*if(!preferences.contains("user-email")) {
            Toast backtoast = Toast.makeText(this, "Creating login activity on resume", Toast.LENGTH_SHORT);
            backtoast.show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);


        }*/
    }

    @Override
    public void onBackPressed() {

        drawerLayout.closeDrawers();

        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();

            // Gestionar el cambio del menú item seleccionado del menú y el título de la action bar
            setMenuItemCheck(null);
        }
        else {
            super.onBackPressed();
        }
    }

    private Fragment getVisibleFragment(){

        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }

        return null;
    }

    public void setMenuItemCheck(Fragment fragment) {

        if(fragment == null)
            fragment = getVisibleFragment();

        if(fragment != null)
            Log.e("SET ON ITEM CHECKED", "fragment" + fragment.getClass().toString());
        else
            Log.e("SET ON ITEM CHECKED", "fragment null");

        if(fragment.getClass() == FragmentMain.class) {
            navMenuItem = R.id.navigation_item_1;
            actionBarTittle = "PharmacyS";

            // Vaciar la pila de Fragments, para si se pulsa en el botón atrás cerrar la App.
            cleanFragmentStack();
        }
        else if(fragment.getClass() == FragmentPharmacies.class) {
            navMenuItem = R.id.navigation_item_2;
            actionBarTittle = "Pharmacies";
        }
        else if(fragment.getClass() == FragmentMap.class) {
            navMenuItem = R.id.navigation_item_3;
            actionBarTittle = "Pharmacies Map";
        }
        else if(fragment.getClass() == FragmentPharmacy.class) {
            if(navMenuItem > 0)
                navView.getMenu().findItem(navMenuItem).setChecked(false);

            navMenuItem = -1;
            actionBarTittle = ((FragmentPharmacy)fragment).getPharmacyName();
        }
        else if(fragment.getClass() == FragmentInventory.class) {
            if(navMenuItem > 0)
                navView.getMenu().findItem(navMenuItem).setChecked(false);

            navMenuItem = -1;
            actionBarTittle = "Product Categories";
        }
        else if(fragment.getClass() == FragmentProducts.class) {
            if(navMenuItem > 0)
                navView.getMenu().findItem(navMenuItem).setChecked(false);

            navMenuItem = -1;
            actionBarTittle = "Catalogue of " + ((FragmentProducts)fragment).getCategoryName();
        }
        else if(fragment.getClass() == FragmentProduct.class) {
            if(navMenuItem > 0)
                navView.getMenu().findItem(navMenuItem).setChecked(false);

            navMenuItem = -1;
            actionBarTittle = ((FragmentProduct) fragment).getProductName();
        }
        else if(fragment.getClass() == FragmentBasket.class) {
            navMenuItem = R.id.navigation_item_4;
            actionBarTittle = "My Basket";
        }
        else if(fragment.getClass() == FragmentReservation.class) {
            navMenuItem = R.id.navigation_item_6;
            actionBarTittle = "My Reservation";
        }

        if(navMenuItem > 0)
            navView.getMenu().findItem(navMenuItem).setChecked(true);

        getSupportActionBar().setTitle(actionBarTittle);
    }

    private void cleanFragmentStack() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if(count > 0) {
            for (int i = 0; i < count; i++) {
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}