package com.example.practica03;

public class Musico {
    private String nombre;
    private String instrumento;
    private int papel;

    public Musico(String nombre, String instrumento, int papel) {
        this.nombre = nombre;
        this.instrumento = instrumento;
        this.papel = papel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public int getPapel() {
        return papel;
    }

    public void setPapel(int papel) {
        this.papel = papel;
    }
}
