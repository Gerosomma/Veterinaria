package com.somma.persistencia;

import android.content.Context;
import android.content.RestrictionEntry;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.somma.clases.Duenio;
import com.somma.clases.Mascota;
import com.somma.veterinaria.BD;
import com.somma.veterinaria.BDHelper;

import java.util.Date;
import java.util.MissingFormatArgumentException;

public class persistenciaMascotas {

    public static final String MIS_LOGS = "Mis_LOGS";

    private BDHelper dbHelper;

    public persistenciaMascotas(Context context) {

        }

    public Mascota buscarMascota(String codigo, SQLiteDatabase baseDatos) {
        Mascota mascota = new Mascota();
        Duenio duenio = new Duenio();
        Cursor cursor;
        /*cursor = baseDatos.rawQuery(new StringBuilder(
                "SELECT ").append(BD.Mascotas.COLUMNAS).append(" FROM ").append(BD.MASCOTAS).append(" WHERE codigo=?")
                .toString(),
                args);*/
        String[] args = new String[] {codigo};
        cursor = baseDatos.query(BD.MASCOTAS, BD.Mascotas.COLUMNAS,
                    BD.Mascotas.CODIGO + " = ?",
                    args, null, null, null);
        if (cursor.moveToFirst()){
            mascota.setCodigo(cursor.getString(cursor.getColumnIndex(BD.Mascotas.CODIGO)));
            mascota.setDuenio(duenio);
            mascota.setNombre(cursor.getString(cursor.getColumnIndex(BD.Mascotas.NOMBRE)));
            mascota.setFecha_afiliacion(cursor.getString(cursor.getColumnIndex(BD.Mascotas.FECHA_AFILIACION)));
            mascota.setEspecie(cursor.getString(cursor.getColumnIndex(BD.Mascotas.ESPECIE)));
            mascota.setRaza(cursor.getString(cursor.getColumnIndex(BD.Mascotas.RAZA)));
            mascota.setEdad(cursor.getInt(cursor.getColumnIndex(BD.Mascotas.EDAD)));
            mascota.setPeso(cursor.getInt(cursor.getColumnIndex(BD.Mascotas.PESO)));
            mascota.setPelo(cursor.getString(cursor.getColumnIndex(BD.Mascotas.PELO)));
            Log.e(MIS_LOGS, "cedula! ::::::::::::::::::::::::");
        } else {
            Log.e(MIS_LOGS, ":::::::::::::::::::     NO SE ENCONTRARON MASCOTAS     :::::::::::::::::::::::: ");
        }
        return mascota;
    }
}
