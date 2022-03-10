package com.example.proyectointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.proyectointegrador.db.LibroManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ListView lvLibros;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupToolbar();
        setupAdapter();

        String nombre = getIntent().getStringExtra("NOMBRE");
        Toast.makeText(this, "Hola " + nombre, Toast.LENGTH_LONG).show();
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mis Libros");
    }

    private void setupAdapter() {
        lvLibros = findViewById(R.id.lvLibros);
        lvLibros.setAdapter(new LibrosAdapter(getLibros()));
        lvLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro libro = ((LibrosAdapter) lvLibros.getAdapter()).getItem(position);
                Toast.makeText(HomeActivity.this, libro.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((LibrosAdapter) lvLibros.getAdapter()).setItems(getLibros());
    }

    private List<Libro> getLibros() {
        try {
            return LibroManager.getInstance(this).obtenerLibros();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "No se pudieron obtenre los libros", Toast.LENGTH_SHORT).show();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemAgregar) {
            Intent intent = new Intent(HomeActivity.this, AgregarLibroActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
