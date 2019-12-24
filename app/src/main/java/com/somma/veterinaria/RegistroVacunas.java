package com.somma.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.somma.clases.Mascota;

public class RegistroVacunas extends AppCompatActivity {

    Mascota mascota= new Mascota();
    Intent i = null;

    public static final String MIS_LOGS = "MIS_LOGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_vacunas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        i = getIntent();
        Bundle objetoRecibido = i.getExtras();

        mascota = (Mascota) objetoRecibido.getSerializable("mascota");
        if (mascota !=null) {

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
        Log.i(MIS_LOGS, "Äca pasando por onSupportNavigateUp");
        Bundle bundle= new Bundle();
        bundle.putSerializable("mascota", mascota);
        intentMascota.putExtras(bundle);

        setResult(RESULT_OK, intentMascota);
        finish();
        return false;
    }

    public void btnVolver(View view) {
        Intent intentMascota = new Intent(this, DatosMascota.class);

        Bundle bundle= new Bundle();
        bundle.putSerializable("mascota", mascota);
        intentMascota.putExtras(bundle);

        setResult(RESULT_OK, intentMascota);
        finish();
    }
}
