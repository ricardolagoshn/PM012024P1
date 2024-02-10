package com.example.pm012024p1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;
import Models.Personas;

public class ActivityList extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listpersonas;
    ArrayList<Personas> lista;
    ArrayList<String> Arreglo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
        listpersonas = (ListView) findViewById(R.id.listpersonas);

        ObtenerInfo();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,Arreglo);
        listpersonas.setAdapter(adp);

        
    }

    private void ObtenerInfo()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas person = null;
        lista = new ArrayList<Personas>();

        // Cursor de base de datos para recorrer los datos
        Cursor cursor = db.rawQuery(Transacciones.SelectAllPersonas, null);

        while (cursor.moveToNext())
        {
            person = new Personas();
            person.setId(cursor.getInt(0));
            person.setNombres(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));

            lista.add(person);
        }

        cursor.close();

        FillData();
    }

    private void FillData()
    {
        Arreglo = new ArrayList<String>();
        for(int i = 0; i < lista.size(); i ++)
        {
            Arreglo.add(lista.get(i).getId() + " - "+
                        lista.get(i).getNombres()  +" - "+
                        lista.get(i).getApellidos()) ;
        }
    }
}