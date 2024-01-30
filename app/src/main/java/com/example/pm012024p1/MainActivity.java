package com.example.pm012024p1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo;
    Button btnproceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        btnproceso = (Button) findViewById(R.id.btnprocesar);

        btnproceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AddPerson();
            }
        });
    }

    private void AddPerson()
    {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());

        Long resultado = db.insert(Transacciones.TablePersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro Ingresado con exito " + resultado.toString(),
                Toast.LENGTH_LONG).show();

        db.close();

    }
}