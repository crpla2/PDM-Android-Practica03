package com.example.practica03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Clase Adaptador que gestiona el funcionamiento de cada uno de los elementos del ReciclerView
 * hereda de ReciclerView
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final ArrayList<Musico> lista;
    private final ItemClickListener mClicklistener;

    /**
     * Constructor de la Clase
     * @param mClicklistener Objeto de la clase ItemClickListener
     * @param lista Objeto de tipo ArrayList de Musico
     */
    public Adapter(ArrayList<Musico> lista, ItemClickListener mClicklistener) {
        this.lista = lista;
        this.mClicklistener=mClicklistener;
    }

    /**
     * Metodo encargado de inflar cada fila del xml cuando sea necesario
     * @param parent Objeto de tipo ViewGroup
     * @param viewType  de tipo int
     * @return devuelve un Objeto ViewHolder
     */

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciclerviewrow, parent, false);
        return new ViewHolder(view);
    }

    /**
     *
     * Metodo que enlaza los datos con el viewholder correspondiente
     * @param holder Objreto de tipo ViewHolder
     * @param position de tipo int
     */
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.setTextNombre(lista.get(position).getNombre());
        holder.setTextInstrumento(lista.get(position).getInstrumento());
        holder.setTextPapel(String.valueOf(lista.get(position).getPapel()));
    }

    /**
     * Metodo que devuelve cuantos elementos hay
     * @return el numero de elementos de tipo int
     */
    @Override
    public int getItemCount() {
        return lista.size();
    }

    /**
     * Interfaz que permite capturar los eventos del click
     */
    public interface ItemClickListener {
        void onItemClick(String nombre,String papel);
    }

    /**
     * Clase de tipo ViewHolder,  corresponde a cada fila
     * almacena y recicla las vistas según salen de la pantalla
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textViewNombre;
        private final TextView textViewInstrumento;
        private final TextView textViewPapel;
        private final ImageButton botonsumar;

        /**
         * Constructor de la Clase ViewHolder
         * @param itemView Objto de la Clase View
         */
        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewInstrumento = itemView.findViewById(R.id.textViewInstrumento);
            textViewPapel = itemView.findViewById(R.id.textViewPapel);
            botonsumar = itemView.findViewById(R.id.sumar);
            ImageButton botonrestar = itemView.findViewById(R.id.restar);
            botonrestar.setOnClickListener(this);
            botonsumar.setOnClickListener(this);
        }
        //GETTERS Y SETTERS
        public void setTextNombre(String s) {
            textViewNombre.setText(s);
        }

        public String getMyTextName() {
            return textViewNombre.getText().toString();
        }

        public void setTextInstrumento(String s) {
            textViewInstrumento.setText(s);
        }

        public void setTextPapel(String s) {
            textViewPapel.setText(s);
        }
        /**
         * Metodo que define el comportamiento de los botones dentro del ViewHolder
         */
        @Override
        public void onClick(View view) {
            //Recogemos los datos del textView como int
            int num = Integer.parseInt(textViewPapel.getText().toString());
            //Comprobamos cual de los dos botones se ha pulsado
            if (view.getId() == botonsumar.getId()) {
                //Si el numero obtenido es mayor que 1
                if (num > 1) {
                    //restamos 1
                    --num;
                    //Llamamos al metodo onclick de la interfaz pasandole los valores que recibiremos en el activity
                    mClicklistener.onItemClick(getMyTextName(),(String.valueOf(num)));
                }

            } else {
                //Si el numero es menor que 3
                if (num < 3) {
                    //sumamos 1
                    ++num;
                    //Llamamos al metodo onclick de la interfaz pasandole los valores que recibiremos en el activity
                    mClicklistener.onItemClick(getMyTextName(),(String.valueOf(num)));
                }
            }
            //Cambiamos el valor del textview
            setTextPapel(String.valueOf(num));
        }
    }
}
