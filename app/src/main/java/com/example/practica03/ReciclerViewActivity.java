package com.example.practica03;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReciclerViewActivity extends AppCompatActivity implements Adapter.ItemClickListener {
    private SQLiteDatabase db;
    private Adapter adapter;
    private RecyclerView RV;
    private ArrayList<Musico> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler);

        RV = findViewById(R.id.recyclerView);
        lista = new ArrayList<>();

        //Creamos la base de datos y la tabla SI NO EXISTEN!!!
        db = openOrCreateDatabase("Musicos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS musicos(Nombre VARCHAR, Instrumento VARCHAR, Papel int)");
        //añadimos la informacion a la base de datos
        Cursor d = db.rawQuery("SELECT count(*) FROM musicos", null);
        d.moveToNext();
        int n = d.getInt(0);
        if (n == 0)
            db.execSQL("INSERT INTO musicos VALUES (\"Alba\", \"Clarinete\", 1), (\"Beatriz\", \"Flauta\", 2), (\"Carlos\", \"Clarinete\", 3), (\"Daniel\", \"Trompeta\", 1), (\"Elisa\", \"Trompa\",2), (\"Fermin\", \"Percusion\", 1), (\"Gabriel\", \"Tuba\", 1), (\"Hector\",\"Saxofon\", 1), (\"Isabel\", \"Flauta\", 1), (\"Jorge\", \"Trompeta\", 2), (\"Ken\", \"Trombon\", 1), (\"Lorenzo\", \"Bombardino\", 1), (\"Miguel\", \"Clarinete\",1), (\"Noelia\", \"Clarinete\", 2), (\"Oscar\", \"Clarinete\", 2), (\"Pedro\", \"Saxofon\",1), (\"Quique\", \"Percusion\", 2), (\"Rosa\", \"Flauta\", 2), (\"Sinara\", \"Bombardino\", 2), (\"Teresa\", \"Trompa\", 2), (\"Umberto\", \"Tuba\", 2), (\"Victor\",\"Saxofon\", 1),(\"Ximena\", \"Flauta\", 2), (\"Yolanda\", \"Clarinete\", 2), (\"Zoe\", \"Trompa\", 1);");
        actualiza();

    }

    private void actualiza() {
        try (Cursor c = db.rawQuery("SELECT * FROM musicos ORDER BY Papel ASC", null)) {
            while (c.moveToNext()) {
                Musico m = new Musico(c.getString(0), c.getString(1), c.getInt(2));
                if (!lista.contains(m))
                    lista.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        Adapter adapter = new Adapter(this, lista, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RV.setLayoutManager(linearLayoutManager);


        //Ponemos una linea de separación entre elementos
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(RV.getContext(), 1);
        RV.addItemDecoration(dividerItemDecoration);

        //añadimos el adaptador a la vista
        RV.setAdapter(adapter);
    }


    public void onItemClick(String value) {
        System.out.println(value);
    }

    @Override
    public void onItemClick(String nombre, String papel) {
        db.execSQL("UPDATE musicos SET Papel =" + papel + "  WHERE Nombre = '" + nombre + "'");
    }

    public void actualizar(View view) {

    }
}

