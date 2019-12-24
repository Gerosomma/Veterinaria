package com.somma.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.somma.clases.Duenio;
import com.somma.clases.Mascota;
import com.somma.veterinaria.BD;
import com.somma.veterinaria.BDHelper;

public class persistenciaMascotas {

    public static final String MIS_LOGS = "MIS_LOGS";

    private BDHelper dbHelper;

    public persistenciaMascotas(Context context) {

    }

    public Mascota buscarMascota(String codigo, SQLiteDatabase baseDatos) {
        Mascota mascota = null;
        Duenio duenio = new Duenio();
        Cursor cursor;
        /*cursor = baseDatos.rawQuery(new StringBuilder(
                "SELECT ").append(BD.Mascotas.COLUMNAS).append(" FROM ").append(BD.MASCOTAS).append(" WHERE codigo=?")
                .toString(),
                args);*/

        /*cursor = baseDatos.query( BD.MASCOTAS + ", " + BD.DUENIOS,
                BD.Mascotas.COLUMNAS,
                BD.MASCOTAS + "." + BD.Mascotas.CODIGO + " = ?",
                args, null, null, null);*/
        try{

            String[] args = new String[] {codigo};
            String[] argsColumnas = new String[] {
                    BD.MASCOTAS + ".*",
                    BD.DUENIOS + "." + BD.Duenios.NOMBRE + " as NombreDuenio ",
                    BD.DUENIOS + "." + BD.Duenios.CEDULA,
                    BD.DUENIOS + "." + BD.Duenios.DIRECCION,
                    BD.DUENIOS + "." + BD.Duenios.TELEFONO
            };

            cursor = baseDatos.query(  BD.MASCOTAS + " INNER JOIN " + BD.DUENIOS
                            + " ON " + BD.MASCOTAS + "." + BD.Mascotas.DUENIO + " = " + BD.DUENIOS + "." + BD.Duenios.CEDULA,
                            argsColumnas,
                            BD.MASCOTAS + "." + BD.Mascotas.CODIGO + " = ?",
                            args, null, null, null);


            if (cursor.moveToFirst()){
                mascota = new Mascota();
                Log.i(MIS_LOGS, cursor.getColumnNames().toString());
                mascota.setCodigo(cursor.getString(cursor.getColumnIndex(BD.Mascotas.CODIGO)));
                duenio.setCedula(cursor.getInt(cursor.getColumnIndex(BD.DUENIOS + "." + BD.Duenios.CEDULA)));
                duenio.setNombre(cursor.getString(cursor.getColumnIndex("NombreDuenio")));
                duenio.setDireccion(cursor.getString(cursor.getColumnIndex(BD.DUENIOS + "." + BD.Duenios.DIRECCION)));
                duenio.setTelefono(cursor.getInt(cursor.getColumnIndex(BD.DUENIOS + "." + BD.Duenios.TELEFONO)));
                mascota.setDuenio(duenio);
                mascota.setNombre(cursor.getString(cursor.getColumnIndex(BD.MASCOTAS + "." + BD.Mascotas.NOMBRE)));
                Log.i(MIS_LOGS, cursor.getString(cursor.getColumnIndex("NombreDuenio")));
                mascota.setFecha_afiliacion(cursor.getString(cursor.getColumnIndex(BD.Mascotas.FECHA_AFILIACION)));
                mascota.setEspecie(cursor.getString(cursor.getColumnIndex(BD.Mascotas.ESPECIE)));
                mascota.setRaza(cursor.getString(cursor.getColumnIndex(BD.Mascotas.RAZA)));
                mascota.setEdad(cursor.getInt(cursor.getColumnIndex(BD.Mascotas.EDAD)));
                mascota.setPeso(cursor.getInt(cursor.getColumnIndex(BD.Mascotas.PESO)));
                mascota.setPelo(cursor.getString(cursor.getColumnIndex(BD.Mascotas.PELO)));
            } else {
                Log.e(MIS_LOGS, ":::::::::::::::::::     NO SE ENCONTRARON MASCOTAS     :::::::::::::::::::::::: ");
            }
        } catch (Exception e) {
            Log.e(MIS_LOGS, "::::::::: cayo exception: " + e.getMessage());
        }
        return mascota;
    }
}
