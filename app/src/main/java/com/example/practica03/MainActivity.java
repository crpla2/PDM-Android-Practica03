package com.example.practica03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase de la actividad principal de la aplicación hereda de AppCompatActivity
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Metodo que inicializa la actividad y la enlaza con su layout correspondiente
     * @param savedInstanceState Objeto del tipo Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Metodo que define el comportamiento del boton, en este caso lanza una segunda actividad
     * @param view Objeto del tipo View
     */
    public void OnClick(View view){
        Intent intent= new Intent(this,ReciclerViewActivity.class);
        startActivity(intent);
    }
}