package com.hugoroman.pharmacys.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.hugoroman.pharmacys.R;

import java.util.List;

//http://www.sgoliver.net/blog/interfaz-de-usuario-en-android-navigation-drawer-navigationview/

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 0;
    private static final String NAV_MENU_ITEM = "navItemId";
    private static final String CURRENT_FRAGMENT = "currentFragment";
    private static final String ACTIONBAR_TITTLE = "actionBarTittle";

    private SharedPreferences preferences;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private int navMenuItem;
    private int currentFragment;
    private String actionBarTittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        if(savedInstanceState == null) {
            navMenuItem = R.id.navigation_item_1;
            currentFragment = 1;
            actionBarTittle = "PharmacyS";
        } else {
            navMenuItem = savedInstanceState.getInt(NAV_MENU_ITEM);
            currentFragment = savedInstanceState.getInt(CURRENT_FRAGMENT);
            actionBarTittle = savedInstanceState.getString(ACTIONBAR_TITTLE);
        }

        Fragment fragment = null;
        switch(currentFragment) {
            case 1:
                fragment = new FragmentMain();
                break;
            case 2:
                fragment = new FragmentPharmacies();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        navView.getMenu().findItem(navMenuItem).setChecked(true);

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
                                if(currentFragment != 1) {
                                    fragment = new FragmentMain();
                                    currentFragment = 1;

                                    fragmentTransaction = true;

                                    // Vaciar la pila de Fragments, para si se pulsa en el botón atrás cerrar la App.
                                    cleanFragmentStack();

                                    fragmentToStack = false;
                                }
                                break;
                            case R.id.navigation_item_2:
                                if(currentFragment != 2) {
                                    fragment = new FragmentPharmacies();
                                    currentFragment = 2;

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
                            fragment.setEnterTransition(new Slide(Gravity.LEFT));
                            fragment.setExitTransition(new Slide(Gravity.BOTTOM));

                            if(fragmentToStack)
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(String.valueOf(menuItem.getItemId())).commit();
                            else
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

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

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Capturar el evento cuando se loguee el usuario. NO ESTOY SEGURO SI ES REALMENTE NECESARIO
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {

                //TextView t = (TextView) findViewById(R.id.textView1);
                //t.setText("De vuelta al fragment");
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Guardar el menú actualmente seleccionado
        savedInstanceState.putInt(NAV_MENU_ITEM, navMenuItem);
        savedInstanceState.putInt(CURRENT_FRAGMENT, currentFragment);
        savedInstanceState.putString(ACTIONBAR_TITTLE, getSupportActionBar().getTitle().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        navMenuItem = savedInstanceState.getInt(NAV_MENU_ITEM);
        currentFragment = savedInstanceState.getInt(CURRENT_FRAGMENT);
        actionBarTittle = savedInstanceState.getString(ACTIONBAR_TITTLE);

        Fragment fragment = null;
        switch(currentFragment) {
            case 1:
                fragment = new FragmentMain();
                break;
            case 2:
                fragment = new FragmentPharmacies();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        navView.getMenu().findItem(navMenuItem).setChecked(true);
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

    public void setCurrentFragment(int fragment) {

        currentFragment = fragment;
    }

    public void setCurrentMenuItem(int menuItem) {

        navMenuItem = menuItem;
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

        if(fragment.getClass() == FragmentMain.class) {
            navMenuItem = R.id.navigation_item_1;
            currentFragment = 1;
            actionBarTittle = "PharmacyS";

            // Vaciar la pila de Fragments, para si se pulsa en el botón atrás cerrar la App.
            cleanFragmentStack();
        }
        else if(fragment.getClass() == FragmentPharmacies.class) {
            navMenuItem = R.id.navigation_item_2;
            currentFragment = 2;
            actionBarTittle = "Pharmacies";
        }

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
