package com.somma.veterinaria;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDHelper extends SQLiteOpenHelper {
    private Context context;

    public  static final String MIS_LOGS = "MIS_LOGS";
    public BDHelper(Context contexto) {
        super(contexto, BD.NOMBRE_BASE_DATOS, null, BD.VERSION);

        this.context =contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(BD.Duenios.SQL_CREACION_TABLA);
            db.execSQL(BD.Mascotas.SQL_CREACION_TABLA);
            db.execSQL(BD.Vacunas.SQL_CREACION_TABLA);
            db.execSQL(BD.Historia_clinica.SQL_CREACION_TABLA);


            Log.e(MIS_LOGS, "TERMINO DE CREAR LAS TABLAS");
            //Cargamos mascotas en la tabla
            db.execSQL(new StringBuilder("INSERT INTO ").append(BD.DUENIOS)
                    .append(" VALUES (NULL, 46453213, 'Geronimo Somma', 'Salterain 1421', 091654252);").toString());
            db.execSQL(new StringBuilder("INSERT INTO ").append(BD.DUENIOS)
                    .append(" VALUES (NULL, 46615780, 'Valentina Lacoste', 'salto 1556', 0916);").toString());
            db.execSQL(new StringBuilder("INSERT INTO ").append(BD.MASCOTAS)
                    .append(" VALUES (NULL, '121', 46453213, 'Zaturnino', '12022010'," +
                            " 'Perro', 'Labrador', 9, 45, 'Blanco');").toString());
            db.execSQL(new StringBuilder("INSERT INTO ").append(BD.MASCOTAS)
                    .append(" VALUES (NULL, '122', 46453213, 'Bambina', '12022011'," +
                            " 'Perro', 'N/A', 9, 15, 'Marron claro');").toString());
            db.execSQL(new StringBuilder("INSERT INTO ").append(BD.MASCOTAS)
                    .append(" VALUES (NULL, '123', 46615780, 'Asia', '12022016'," +
                            " 'Gato', 'linda', 3, 2, 'Negro atrigrado');").toString());

            Log.i(MIS_LOGS, "AGREGO ÑPS DUENIOS Y MASOCTAS");

        } catch (SQLException e) {
            Log.i(MIS_LOGS, "Error al onCreate dbHelper " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Normalmente acá se manejan las versiones, si hay versiones nuevas actualizamos el schema de la base

        db.execSQL(BD.Duenios.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Mascotas.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Historia_clinica.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Vacunas.SQL_ELIMINAR_TABLA);

        Log.i(MIS_LOGS, "paso por aca ");
        onCreate(db);
    }

    public void eliminarBaseDatos(){
        //Elimina directamente el archivo de la base de datos
        context.deleteDatabase(BD.NOMBRE_BASE_DATOS);
    }
}
