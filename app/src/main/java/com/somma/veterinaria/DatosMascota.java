package com.somma.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DatosMascota extends AppCompatActivity {
    protected TextView tvCodigo;
    protected TextView tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_mascota);
        Intent i = getIntent();
        tvCodigo = (TextView)findViewById(R.id.codigo);
        tvNombre = (TextView)findViewById(R.id.nombre);

        String codigo = i.getStringExtra("Codigo");
        String nombre = i.getStringExtra("Nombre");
        tvCodigo.setText(codigo);
        tvNombre.setText(nombre);
    }
}
