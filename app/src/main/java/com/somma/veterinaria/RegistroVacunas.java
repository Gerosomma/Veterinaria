package com.somma.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.somma.clases.H_Clinica;
import com.somma.clases.Mascota;
import com.somma.clases.Vacuna;
import com.somma.persistencia.persistenciaMascotas;

import java.util.Date;

public class RegistroVacunas extends AppCompatActivity {

    Mascota mascota= new Mascota();
    Intent i = null;
    protected EditText etDosis;
    protected EditText etVacuna;
    protected EditText etDescripcion;
    protected EditText etTrmtRecomendado;
    protected EditText etMedRecetado;
    protected EditText etSintomas;
    protected EditText etVeterinario;

    private BDHelper bdHelper;
    private SQLiteDatabase baseDatos;
    private persistenciaMascotas perMascotas;

    public static final String MIS_LOGS = "MIS_LOGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_vacunas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etDosis = (EditText)findViewById(R.id.etDosis);
        etVacuna = (EditText)findViewById(R.id.etVacuna);
        etDescripcion = (EditText)findViewById(R.id.etDescripcion);
        etSintomas = (EditText)findViewById(R.id.etSintomas);
        etTrmtRecomendado = (EditText)findViewById(R.id.etTmtoRecomendado);
        etMedRecetado = (EditText)findViewById(R.id.etMedRecetado);
        etVeterinario = (EditText)findViewById(R.id.etVeterinario);

        i = getIntent();
        Bundle objetoRecibido = i.getExtras();

        mascota = (Mascota) objetoRecibido.getSerializable("mascota");
        if (mascota !=null) {

            try {
                bdHelper = new BDHelper(this);
                baseDatos = bdHelper.getWritableDatabase();
            } catch (Exception e) {
                Log.e(MIS_LOGS, "Error al instancear dbHelper" + e.getMessage());
            }
            /*Intent intentMascota = new Intent(this, DatosMascota.class);

            Bundle bundle= new Bundle();
            bundle.putSerializable("mascota", mascota);
            intentMascota.putExtras(bundle);

            setResult(RESULT_OK, intentMascota);
            finish();*/
        }
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

    public void btnRegistrarVacuna(View view) {
        try {
            perMascotas = new persistenciaMascotas(this);

            Vacuna vacuna = new Vacuna();
            vacuna.setMascota(mascota);
            vacuna.setFecha(new Date());
            vacuna.setDosis(etDosis.getText().toString());
            vacuna.setVacuna(etVacuna.getText().toString());

            H_Clinica h_clinica = new H_Clinica();
            h_clinica.setDescripcion(etDescripcion.getText().toString());
            h_clinica.setTratamiento_recomendado(etTrmtRecomendado.getText().toString());
            h_clinica.setMedicamento_recetado(etMedRecetado.getText().toString());
            h_clinica.setSintomas(etSintomas.getText().toString());
            h_clinica.setVeterinario(etVeterinario.getText().toString());
            vacuna.setH_clinica(h_clinica);

            perMascotas.agregarRegistroVacuna(vacuna, baseDatos);
        } catch (Exception e) {
            Log.e(MIS_LOGS, "Error buscar mascota " + e.getMessage());
        }finally {
            baseDatos.close();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseDatos.close();
        Log.e(MIS_LOGS, "Destroy RegistroVacuna ");
    }
}
