package com.example.proyectointegrador;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Libros")
public class Libro {

    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String nombre;
    @DatabaseField
    private String autor;

    public Libro() {
    }

    public Libro(String nombre, String autor) {
        this.nombre = nombre;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
