package com.example.practica03;

/**
 * Clase que define el Objeto Musico tiene 3 atributos, nombre, instrumento y papel.
 */
public class Musico {
    private String nombre;
    private String instrumento;
    private int papel;

    /**
     * Metodo Constructor de la clase
     * @param nombre Nombre del Músico de tipo String
     * @param instrumento Instrumento que toca de tipo String
     * @param papel Rol en la orquesta de tipo int
     */
    public Musico(String nombre, String instrumento, int papel) {
        this.nombre = nombre;
        this.instrumento = instrumento;
        this.papel = papel;
    }
    // GETTERS Y SETTERS
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
