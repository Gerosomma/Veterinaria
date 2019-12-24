package com.somma.veterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.somma.clases.Constantes;
import com.somma.clases.Mascota;

import java.text.DecimalFormat;

public class DatosMascota extends AppCompatActivity {
    protected TextView tvCodigo;
    protected TextView tvNombre;
    protected TextView tvAfiliacion;
    protected TextView tvEspecie;
    protected TextView tvRaza;
    protected TextView tvEdad;
    protected TextView tvPeso;
    protected TextView tvPelo;
    protected TextView tvCedula;
    protected TextView tvNombreDuenio;
    protected TextView tvDireccion;
    protected TextView tvTelefono;

    Mascota mascota= new Mascota();

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
        tvCedula = (TextView)findViewById(R.id.cedula);
        tvNombreDuenio = (TextView)findViewById(R.id.nombreDuenio);
        tvDireccion = (TextView)findViewById(R.id.direccion);
        tvTelefono = (TextView)findViewById(R.id.telefono);
        if (i.hasExtra("mascota")){
            Bundle objetoRecibido = i.getExtras();

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
                tvCedula.setText(mascota.getDuenio().getCedula().toString());
                tvNombreDuenio.setText(mascota.getDuenio().getNombre());
                tvDireccion.setText(mascota.getDuenio().getDireccion());
                tvTelefono.setText(mascota.getDuenio().getTelefono().toString());

            } else {
                Toast.makeText(this, "No existe mascota", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void btnHistoriaClinica(View view) {
        if (mascota != null){
            Intent intentMascota = new Intent(this, HistoriaClinica.class);

            Bundle bundle= new Bundle();
            bundle.putSerializable("mascota", mascota);
            intentMascota.putExtras(bundle);

            Toast.makeText(this, "Iniciando Historia Clinica.", Toast.LENGTH_LONG).show();
            startActivity(intentMascota);
        }else {
            Toast.makeText(this, "No existe mascota.", Toast.LENGTH_LONG).show();
        }
    }

    public void btnRegistroVacuna(View view) {
        if (mascota != null){
            Intent intentMascota = new Intent(this, RegistroVacunas.class);

            Bundle bundle= new Bundle();
            bundle.putSerializable("mascota", mascota);
            intentMascota.putExtras(bundle);

            Toast.makeText(this, "Iniciando Registro de Vacunas.", Toast.LENGTH_LONG).show();
            startActivityForResult(intentMascota, Constantes.CODIGO_REGISTRO_VACUNA);
        }else {
            Toast.makeText(this, "No existe mascota.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Constantes.CODIGO_REGISTRO_VACUNA:
                if (resultCode == RESULT_OK) {
                    //double total = data.getDoubleExtra(Constantes.EXTRA_TOTAL, 0);
                    Toast.makeText(this, "RECIBIMOS EL INTENT DESDE REGISTRO VACUNAS.", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
