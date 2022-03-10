package com.example.proyectointegrador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String USER_PREF = "USER_PREF";
    static final String USER_NAME = "USER_NAME";

    private Button btnLogin;
    private EditText etNombre;
    private EditText etContrasenia;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etContrasenia = findViewById(R.id.etPass);

        preferences = getSharedPreferences(USER_PREF, MODE_PRIVATE);
        String nombreUsuario = preferences.getString(USER_NAME, "");
        etNombre.setText(nombreUsuario);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datosValidos()) {
                    actualizarNombreUsuario(etNombre.getText().toString());
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("NOMBRE", etNombre.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Completar datos",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void actualizarNombreUsuario(String nombreUsuario) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_NAME, nombreUsuario);
        editor.apply();
    }

    private boolean datosValidos() {
        if (etNombre.getText().toString().isEmpty() ||
                etContrasenia.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


}
