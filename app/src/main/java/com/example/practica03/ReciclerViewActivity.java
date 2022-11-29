package com.example.practica03;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Clase de la actividad ReciclerViewActivity hereda de AppCompatActivity e implementa la interfaz
 * ItemClickListener de la Clase adapter
 * Tiene 3 atributos db (base de datos), RV (ReciclerView), lista(ArrayList de Musicos
 */
public class ReciclerViewActivity extends AppCompatActivity implements Adapter.ItemClickListener {
    private SQLiteDatabase db;
    private RecyclerView RV;
    private ArrayList<Musico> lista;

    /**
     * Constructor vacio de la Clase
     */
    public ReciclerViewActivity() {
    }

    /**
     * etodo que inicializa la actividad y la enlaza con su layout correspondiente e inicializa los
     * diferentes elementos con lo que va a interactuar.
     * @param savedInstanceState Objeto del tipo Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler);

        RV = findViewById(R.id.recyclerView);
        lista = new ArrayList<>();

        //Creamos la base de datos y la tabla SI NO EXISTEN
        db = openOrCreateDatabase("Musicos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS musicos(Nombre VARCHAR, Instrumento VARCHAR, Papel int)");
        //Realizamos una consulta para comprobar que la base de datos no contiene datos para
        //no duplicarlos
        int n = 0;
        Cursor d = null;
        try {
            d = db.rawQuery("SELECT count(*) FROM musicos", null);
            d.moveToNext();
            n = d.getInt(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            d.close();
        }
        //Si no hay datos
        if (n == 0)
            //añadimos la informacion a la base de datos
            db.execSQL("INSERT INTO musicos VALUES (\"Alba\", \"Clarinete\", 1), (\"Beatriz\", \"Flauta\", 2), (\"Carlos\", \"Clarinete\", 3), (\"Daniel\", \"Trompeta\", 1), (\"Elisa\", \"Trompa\",2), (\"Fermin\", \"Percusion\", 1), (\"Gabriel\", \"Tuba\", 1), (\"Hector\",\"Saxofon\", 1), (\"Isabel\", \"Flauta\", 1), (\"Jorge\", \"Trompeta\", 2), (\"Ken\", \"Trombon\", 1), (\"Lorenzo\", \"Bombardino\", 1), (\"Miguel\", \"Clarinete\",1), (\"Noelia\", \"Clarinete\", 2), (\"Oscar\", \"Clarinete\", 2), (\"Pedro\", \"Saxofon\",1), (\"Quique\", \"Percusion\", 2), (\"Rosa\", \"Flauta\", 2), (\"Sinara\", \"Bombardino\", 2), (\"Teresa\", \"Trompa\", 2), (\"Umberto\", \"Tuba\", 2), (\"Victor\",\"Saxofon\", 1),(\"Ximena\", \"Flauta\", 2), (\"Yolanda\", \"Clarinete\", 2), (\"Zoe\", \"Trompa\", 1);");
        //Cargamos los datos
        actualiza();

    }

    /**
     * Metodo que se encarga de cargar los datos y mostrarlos en el layout
     */
    private void actualiza() {
        Cursor c = null;
        try {
            //Realizamos una consulta para extraer los datos
            c = db.rawQuery("SELECT * FROM musicos ORDER BY Papel ASC", null);
            //Borramos el contenido de la lista para evitar duplicidades
            lista.clear();
            //Recorremos el resultado de la consulta
            while (c.moveToNext()) {
                //Creamos un Objeto Musico con los datos extraidos
                Musico m = new Musico(c.getString(0), c.getString(1), c.getInt(2));
                //Lo añadimos a la lista
                lista.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            c.close();

        }
        //Creamos un objeto del tipo Adapter pasandole como argumento la lista de musicos y el listener
        Adapter adapter = new Adapter(lista, this);
        //Creamos un Linear Layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //Lo asociamos con el ReciclerView
        RV.setLayoutManager(linearLayoutManager);
        //Ponemos una linea de separación entre elementos
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(RV.getContext(), 1);
        RV.addItemDecoration(dividerItemDecoration);
        //añadimos el adaptador a la vista
        RV.setAdapter(adapter);
    }

    /**
     * Metodo que define el comportamiento de los botones del Recicler view es heredado de la interfaz
     * ItemClickListener de la Clase adapter
     * Actualiza la base de datos en función de lo que ha sido pulsado en la pantalla.
     * @param nombre es un String
     * @param papel es un String
     */
    @Override
    public void onItemClick(String nombre, String papel) {
        //Consulta para la actualización de los datos en la base de datos
        db.execSQL("UPDATE musicos SET Papel =" + papel + "  WHERE Nombre = '" + nombre + "'");
    }

    /**
     * Metodo que define el comportamiento del boton actualizar de la actividad, llama al metodo
     * actualiza()
     * @param view Objeto de tipo View
     */
    public void actualizar(View view) {
        actualiza();
    }
}

