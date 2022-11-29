package com.example.practica03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Musico> lista;
    private LayoutInflater mInflater;
    private ItemClickListener mClicklistener;
    private Context context;

    /**
     * Constructor
     *
     * @param context
     * @param lista
     */

    public Adapter(Context context, ArrayList<Musico> lista,ItemClickListener mClicklistener) {
        this.lista = lista;
        this.context=context;
        this.mClicklistener=mClicklistener;
    }

    /**
     * Inflar cada fila del xml cuando sea necesario
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciclerviewrow, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Enlaza los datos con el viewholder correspondiente
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setTextNombre(lista.get(position).getNombre());
        holder.setTextInstrumento(lista.get(position).getInstrumento());
        holder.setTextPApel(String.valueOf(lista.get(position).getPapel()));
    }

    /**
     * devuelve cuantos elementos hay
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return lista.size();
    }

    /**** PARA EL LISTENER ****/


    //La actividad padre implementa este metodo para responder a los elementos de clic
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClicklistener = itemClickListener;
    }

    //Permite capturar los eventos del clic
    public interface ItemClickListener {
        void onItemClick(String nombre,String papel);
    }

    //METODOS AUXILIARES
    Musico getItem(int pos) {
        return lista.get(pos);
    }

    /**
     * Esta clase corresponde a cada fila
     * almacena y recicla las vistas según salen de la pantalla
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewNombre;
        private TextView textViewInstrumento;
        private TextView textViewPapel;
        private Button botonsumar, botonrestar;
        private int num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewInstrumento = itemView.findViewById(R.id.textViewInstrumento);
            textViewPapel = itemView.findViewById(R.id.textViewPapel);
            botonsumar = itemView.findViewById(R.id.sumar);
            botonrestar = itemView.findViewById(R.id.restar);
            botonrestar.setOnClickListener(this);
            botonsumar.setOnClickListener(this);
        }

        public void setTextNombre(String s) {
            textViewNombre.setText(s);
        }

        public String getMyTextName() {
            return textViewNombre.getText().toString();
        }

        public void setTextInstrumento(String s) {
            textViewInstrumento.setText(s);
        }

        public String getMyTextInstrumento() {
            return textViewInstrumento.getText().toString();
        }

        public void setTextPApel(String s) {
            textViewPapel.setText(s);
        }

        public String getMyTextPApel() {
            return textViewPapel.getText().toString();
        }

        @Override
        public void onClick(View view) {
            num = Integer.parseInt(textViewPapel.getText().toString());
            if (view.getId() == botonrestar.getId()) {
                if (num > 1) {
                    --num;
                    mClicklistener.onItemClick(getMyTextName(),(String.valueOf(num)));
                }

            } else {
                if (num < 3) {
                    ++num;
                    mClicklistener.onItemClick(getMyTextName(),(String.valueOf(num)));
                }
            }
            setTextPApel(String.valueOf(num));
        }
    }
}
