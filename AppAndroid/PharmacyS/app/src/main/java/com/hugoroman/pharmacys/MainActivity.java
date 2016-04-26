package com.hugoroman.pharmacys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 0;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getPreferences(MODE_PRIVATE);

        if(!preferences.contains("user-email")) {
            Toast.makeText(this, "Creating login activity", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
        }

    }

    // Capturar el evento cuando se loguee el usuario. NO ESTOY SEGURO SI ES REALMENTE NECESARIO
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_LOGIN) {
            if(resultCode == RESULT_OK) {

                TextView t = (TextView) findViewById(R.id.textView2);
                t.setText("De vuelta");
            }
        }
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
}