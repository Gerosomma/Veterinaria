package com.somma.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.somma.clases.Duenio;
import com.somma.clases.Mascota;
import com.somma.clases.Vacuna;
import com.somma.veterinaria.BD;
import com.somma.veterinaria.BDHelper;

import java.util.Calendar;
import java.util.Date;

public class persistenciaMascotas {

    public static final String MIS_LOGS = "MIS_LOGS";

    private BDHelper dbHelper;

    public persistenciaMascotas(Context context) {

    }

    public Mascota buscarMascota(String codigo, SQLiteDatabase baseDatos) {
        Mascota mascota = null;
        Duenio duenio = new Duenio();
        Cursor cursor;

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

    public void agregarRegistroVacuna(Vacuna vacuna, SQLiteDatabase baseDatos) {
        ContentValues valores = new ContentValues();

        //ejemplo de trabajo sobre transacciones.
        baseDatos.beginTransaction();

        try {
            valores.put(BD.Vacunas.CODIGO, vacuna.getMascota().getCodigo());
            valores.put(BD.Vacunas.FECHA, vacuna.getFecha().toString());
            valores.put(BD.Vacunas.VACUNA, vacuna.getVacuna());
            valores.put(BD.Vacunas.DOSIS, vacuna.getDosis());
            baseDatos.insert(BD.VACUNAS, null, valores);

            valores = new ContentValues();
            Log.e(MIS_LOGS, "Se registro una vacuna!");

            valores.put(BD.Historia_clinica.CODIGO, vacuna.getMascota().getCodigo());
            valores.put(BD.Historia_clinica.FECHA, vacuna.getFecha().toString());
            valores.put(BD.Historia_clinica.DESCRIPCION, vacuna.getH_clinica().getDescripcion());
            valores.put(BD.Historia_clinica.SINTOMAS, vacuna.getH_clinica().getSintomas());
            valores.put(BD.Historia_clinica.TRATAMIENTO_RECOMENDADO, vacuna.getH_clinica().getTratamiento_recomendado());
            valores.put(BD.Historia_clinica.MEDICAMENTO_RECETADO, vacuna.getH_clinica().getMedicamento_recetado());
            valores.put(BD.Historia_clinica.VETERINARIO, vacuna.getH_clinica().getVeterinario());
            baseDatos.insert(BD.H_CLINICA, null, valores);
            Log.e(MIS_LOGS, "Se registro Historia clinica!");

            baseDatos.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(MIS_LOGS, "No se pudo insertar vacuna." + e.getMessage());
        } finally {
            baseDatos.endTransaction();
        }
    }

    public Cursor ListadoHistoriaClinica(Mascota mascota, SQLiteDatabase baseDatos){
        //                     nombre base | quecolumnas obtengo | clausula "where"
        String[] args = new String[] {mascota.getCodigo()};
        return baseDatos.query(BD.H_CLINICA, BD.Historia_clinica.COLUMNAS, BD.Historia_clinica.CODIGO + " = ?",
                args, null, null,
                BD.Historia_clinica.FECHA + " DESC");
    }
}
