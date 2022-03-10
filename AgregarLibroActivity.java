package com.example.proyectointegrador;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectointegrador.db.LibroManager;

import java.sql.SQLException;

public class AgregarLibroActivity extends AppCompatActivity {

    private EditText etNombreLibro;
    private EditText etAutor;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);

        setupUI();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datosVacios()) {
                    Toast.makeText(AgregarLibroActivity.this, "Completar campos", Toast.LENGTH_SHORT).show();
                } else {
                    insertarLibro();
                }
            }
        });
    }

    private void insertarLibro() {
        String nombreLibro = etNombreLibro.getText().toString();
        String autor = etAutor.getText().toString();
        Libro libro = new Libro(nombreLibro, autor);

        try {
            LibroManager.getInstance(this).insertarLibro(libro);
            finish();
        } catch (SQLException e) {
            Toast.makeText(this, "No se pudo crear el libro", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private boolean datosVacios() {
        return etNombreLibro.getText().toString().isEmpty() ||
                etAutor.getText().toString().isEmpty();
    }

    private void setupUI() {
        etNombreLibro = findViewById(R.id.etNombreLibro);
        etAutor = findViewById(R.id.etAutor);
        btnGuardar = findViewById(R.id.btnGuardar);
    }
}
