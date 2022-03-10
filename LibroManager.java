package com.example.proyectointegrador.db;

import android.content.Context;

import com.example.proyectointegrador.Libro;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class LibroManager {

    private Dao<Libro, Integer> dao;
    private static LibroManager instance;

    public static LibroManager getInstance(Context context) {
        if (instance == null) {
            instance = new LibroManager(context);
        }

        return instance;
    }

    private LibroManager(Context context) {
        OrmLiteSqliteOpenHelper helper = OpenHelperManager.getHelper(context, DBHelper.class);
        try {
            dao = helper.getDao(Libro.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarLibro(Libro libro) throws SQLException {
        dao.create(libro);
    }

    public List<Libro> obtenerLibros() throws SQLException {
        return dao.queryForAll();
    }


}
