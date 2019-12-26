package com.somma.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.somma.clases.Mascota;
import com.somma.persistencia.persistenciaMascotas;

public class MainActivity extends AppCompatActivity {

    public  static final String MIS_LOGS = "MIS_LOGS";

    private BDHelper bdHelper;
    private SQLiteDatabase baseDatos;
    private persistenciaMascotas perMascotas;

    protected EditText etCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCodigo = (EditText)findViewById(R.id.etCodigo);
        try {
            bdHelper = new BDHelper(this);
            baseDatos = bdHelper.getWritableDatabase();
        } catch (Exception e) {
            Log.e(MIS_LOGS, "Error al instancear dbHelper" + e.getMessage());
        }
    }

    public void btnBuscarMascota(View view) {
        try {
            String codigo = etCodigo.getText().toString();
            perMascotas = new persistenciaMascotas(this);

            Mascota mascota = perMascotas.buscarMascota(codigo, baseDatos);
            if (mascota != null){
                Intent intentMascota = new Intent(this, DatosMascota.class);

                Bundle bundle= new Bundle();
                bundle.putSerializable("mascota", mascota);
                intentMascota.putExtras(bundle);

                Toast.makeText(this, "Iniciando visor de datos.", Toast.LENGTH_LONG).show();
                startActivity(intentMascota);

                System.out.println(mascota.toString());
            } else {
                Toast.makeText(this, "Codigo inexistente.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e(MIS_LOGS, "Error buscar mascota " + e.getMessage());
        }finally {
            baseDatos.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseDatos.close();

        Log.e(MIS_LOGS, "el otro aca ");
        //cuidado con esto, es para las pruebas de clases
        //cuando temrino de usar la app se elimine la base de datos para que a la proxima vez vuelva a crear toda la base.
        bdHelper.eliminarBaseDatos();
    }
}
