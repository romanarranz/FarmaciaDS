package com.hugoroman.pharmacys.screens;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hugoroman.pharmacys.R;

//http://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/

public class LoginActivity extends Activity {

    private static final int REQUEST_SIGNUP = 0;

    private EditText iEmail;
    private EditText iPass;
    private Button btnLogin;
    private TextView linkSignUp;

    private Toast exitToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iEmail = (EditText) findViewById(R.id.input_email);
        iPass = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        linkSignUp = (TextView) findViewById(R.id.link_signup);


        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Loguearse
                //login();
                onLoginSuccess();
            }
        });

        linkSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Lanzar la actividad de registrarse
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }

    private void login() {

        if(!validate()) {
            onLoginFailed();

            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppThemeDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = iEmail.getText().toString();
        String password = iPass.getText().toString();

        // Lógica del login
        new android.os.Handler().postDelayed(
            new Runnable() {

                @Override
                public void run() {
                    // Cuando se complete, se decide si llamar a uno u a otro en función de la respuesta del servidor.
                    // Si es correcto se tiene que guardar en el SharedPreferences
                    onLoginSuccess();
                    //onLoginFailed();
                    progressDialog.dismiss();
                }
            }, 3000);
    }

    public void onLoginSuccess() {

        setResult(RESULT_OK, null);

        finish();
    }

    public void onLoginFailed() {

        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnLogin.setEnabled(true);
    }

    public boolean validate() {

        boolean valid = true;

        String email = iEmail.getText().toString();
        String password = iPass.getText().toString();

        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            iEmail.setError("enter a valid email address");
            valid = false;
        }
        else {
            iEmail.setError(null);
        }

        if(password.isEmpty() || password.length() < 4 || password.length() > 10) {
            iPass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        }
        else {
            iPass.setError(null);
        }

        return valid;
    }

    // Capturar el evento cuando se registre el usuario si así lo ha pulsado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // Terminamos la actividad de login y volvemos a la main
                onLoginSuccess();
            }
        }
    }

    @Override
    public void onBackPressed() {

        if(exitToast!=null && exitToast.getView().getWindowToken() != null) {
            finishAffinity();
        }
        else {
            exitToast = Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT);
            exitToast.show();
        }
    }
}
