package com.somma.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.somma.clases.Mascota;

import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica extends ListActivity {
    Mascota mascota= new Mascota();
    Intent i = null;

    public static final String MIS_LOGS = "MIS_LOGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_historia_clinica);

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
            List<String> registros = new ArrayList();

            for (int i = 0; i < 5; i++) {
                registros.add("Vacuna " + i);
            }

            ArrayAdapter adaptadorContactos = new ArrayAdapter(this, android.R.layout.simple_list_item_1, registros);
            //getListView().setAdapter(adaptadorContactos);
            setListAdapter(adaptadorContactos);
        }
    }
}
