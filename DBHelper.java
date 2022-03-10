package com.example.proyectointegrador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyectointegrador.Libro;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBHelper extends OrmLiteSqliteOpenHelper {


    public DBHelper(Context context) {
        super(context, DBConstants.DB_NOMBRE, null, DBConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Libro.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        if (oldVersion < newVersion){
            try {
                TableUtils.dropTable(connectionSource, Libro.class, false);
                onCreate(database, connectionSource);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
