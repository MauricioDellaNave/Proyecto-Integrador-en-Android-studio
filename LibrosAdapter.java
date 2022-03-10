package com.example.proyectointegrador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LibrosAdapter extends BaseAdapter {

    private List<Libro> libros;

    public LibrosAdapter(List<Libro> libros) {
        this.libros = libros;
    }

    //Devuelve la cantidad de libros que tiene la lista
    @Override
    public int getCount() {
        return libros.size();
    }

    @Override
    public Libro getItem(int position) {
        return libros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return libros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Libro libro = getItem(position);

        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_libro, parent, false);
        } else {
            view = convertView;
        }

        TextView txtNombre = view.findViewById(R.id.txtNombre);
        TextView txtAutor = view.findViewById(R.id.txtAutor);

        txtNombre.setText(libro.getNombre());
        txtAutor.setText(libro.getAutor());

        return view;
    }

    public void setItems(List<Libro> libros) {
        this.libros = libros;
        notifyDataSetChanged();
    }
}
