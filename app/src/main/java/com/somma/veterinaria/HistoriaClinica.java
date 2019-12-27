package com.somma.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.somma.clases.Mascota;
import com.somma.persistencia.persistenciaMascotas;

import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica extends AppCompatActivity {
    Mascota mascota= new Mascota();
    persistenciaMascotas perMascotas = null;
    private BDHelper bdHelper;
    private SQLiteDatabase baseDatos;
    Intent i = null;
    private SimpleCursorAdapter adaptadorArtistas;
    protected ListView lvHistoriaClinica;
    public static final String MIS_LOGS = "MIS_LOGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_clinica);

        lvHistoriaClinica = (ListView)findViewById(R.id.lvHClinica);
        i = getIntent();
        Bundle objetoRecibido = i.getExtras();

        mascota = (Mascota) objetoRecibido.getSerializable("mascota");
        if (mascota !=null) {
            try {
                bdHelper = new BDHelper(this);
                baseDatos = bdHelper.getWritableDatabase();

                String[] argsColumns = new String[] {
                        BD.Historia_clinica._ID,
                        BD.Historia_clinica.FECHA,
                        BD.Historia_clinica.DESCRIPCION
                    };
                perMascotas = new persistenciaMascotas(this);
                Cursor cursor = perMascotas.ListadoHistoriaClinica(mascota, baseDatos);
                adaptadorArtistas = new SimpleCursorAdapter(this, R.layout.listitem_h_clinica,
                        cursor, argsColumns,
                        new int[] {R.id.tvId, R.id.tvFecha, R.id.tvDescripcion}, 0);

                lvHistoriaClinica.setAdapter(adaptadorArtistas);
                lvHistoriaClinica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        lvHClinicaOnItemClick(parent, view, position, id);
                    }
                });

            } catch (Exception e) {
                Log.e(MIS_LOGS, "Error al instancear dbHelper" + e.getMessage());
            } finally {
                baseDatos.close();
            }
        }
    }

    protected void lvHClinicaOnItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = ((SimpleCursorAdapter)adaptadorArtistas).getCursor();
        cursor.moveToPosition(position);

        int columnaId = cursor.getColumnIndex(BD.Historia_clinica._ID);
        int columnaNombre = cursor.getColumnIndex(BD.Historia_clinica.FECHA);
        int columnaAnioNacimiento = cursor.getColumnIndex(BD.Historia_clinica.DESCRIPCION);

        StringBuilder info = new StringBuilder(String.valueOf(cursor.getLong(columnaId)));
        info.append(" - ").append(cursor.getString(columnaNombre));
        info.append(" (").append(String.valueOf(cursor.getString(columnaAnioNacimiento))).append(")");

        Toast.makeText(this, info.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Intent intentMascota = new Intent(this, DatosMascota.class);
        Log.i(MIS_LOGS, "Aca pasando por onSupportNavigateUp");
        Bundle bundle= new Bundle();
        bundle.putSerializable("mascota", mascota);
        intentMascota.putExtras(bundle);

        baseDatos.close();
        setResult(RESULT_OK, intentMascota);
        finish();
        return false;
    }

    public void btnVolver(View view) {
        Intent intentMascota = new Intent(this, DatosMascota.class);

        Bundle bundle= new Bundle();
        bundle.putSerializable("mascota", mascota);
        intentMascota.putExtras(bundle);

        baseDatos.close();
        setResult(RESULT_OK, intentMascota);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseDatos.close();
        Log.e(MIS_LOGS, "Destroy Historia Clinica ");
    }
}
