package com.somma.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.somma.clases.Mascota;

public class DatosMascota extends AppCompatActivity {
    protected TextView tvCodigo;
    protected TextView tvNombre;
    protected TextView tvAfiliacion;
    protected TextView tvEspecie;
    protected TextView tvRaza;
    protected TextView tvEdad;
    protected TextView tvPeso;
    protected TextView tvPelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_mascota);
        Intent i = getIntent();
        tvCodigo = (TextView)findViewById(R.id.codigo);
        tvNombre = (TextView)findViewById(R.id.nombre);
        tvAfiliacion = (TextView)findViewById(R.id.afiliacion);
        tvEspecie = (TextView)findViewById(R.id.especie);
        tvRaza = (TextView)findViewById(R.id.raza);
        tvEdad = (TextView)findViewById(R.id.edad);
        tvPeso = (TextView)findViewById(R.id.peso);
        tvPelo = (TextView)findViewById(R.id.pelo);

        Bundle objetoRecibido = i.getExtras();
        Mascota mascota= new Mascota();

        mascota = (Mascota) objetoRecibido.getSerializable("mascota");
        if (mascota !=null) {
            tvCodigo.setText(mascota.getCodigo());
            tvNombre.setText(mascota.getNombre());
            tvAfiliacion.setText(mascota.getFecha_afiliacion());
            tvEspecie.setText(mascota.getEspecie());
            tvRaza.setText(mascota.getRaza());
            tvEdad.setText(mascota.getEdad().toString());
            tvPeso.setText(mascota.getPeso().toString());
            tvPelo.setText(mascota.getPelo());
        } else {
            Toast.makeText(this, "No existe mascota", Toast.LENGTH_LONG).show();
        }
    }
}
