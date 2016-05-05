package com.hugoroman.pharmacys.screens;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hugoroman.pharmacys.R;

public class SignUpActivity extends Activity {

    private EditText iName;
    private EditText iEmail;
    private EditText iPass;
    private Button btnSignUp;
    private TextView linkLogin;

    private Toast exitToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        iName = (EditText) findViewById(R.id.input_name);
        iEmail = (EditText) findViewById(R.id.input_email);
        iPass = (EditText) findViewById(R.id.input_password);
        btnSignUp = (Button) findViewById(R.id.btn_login);
        linkLogin = (TextView) findViewById(R.id.link_signup);


        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Registrarse
                signup();
            }
        });

        linkLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Terminar la actividad para volver a la anterior
                finish();
            }
        });

    }

    private void signup() {

        if(!validate()) {
            onSignUpFailed();

            return;
        }

        btnSignUp.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this, R.style.AppThemeDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating account...");
        progressDialog.show();

        String email = iEmail.getText().toString();
        String password = iPass.getText().toString();

        // Lógica del registro
        new android.os.Handler().postDelayed(
                new Runnable() {

                    @Override
                    public void run() {
                        // Cuando se complete, se decide si llamar a uno u a otro en función de la respuesta del servidor.
                        // Si es correcto se tiene que guardar en el SharedPreferences
                        onSignUpSuccess();
                        //onSignUpFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignUpSuccess() {

        setResult(RESULT_OK, null);

        finish();
    }

    public void onSignUpFailed() {

        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnSignUp.setEnabled(true);
    }

    public boolean validate() {

        boolean valid = true;

        String name = iName.getText().toString();
        String email = iEmail.getText().toString();
        String password = iPass.getText().toString();

        if(name.isEmpty() || name.length() < 3) {
            iName.setError("at least 3 characters");
            valid = false;
        }
        else {
            iName.setError(null);
        }

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
}
