package com.example.torneofutbol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.torneofutbol.databinding.ActivityAgregarBinding;

import java.util.ArrayList;

public class adapterPartidos extends RecyclerView.Adapter<adapterPartidos.TodoVH> {

    private Context context;

    private ArrayList<Partido> objetos;

    private ActivityAgregarBinding binding;

    private int cardLayout;


    public adapterPartidos(Context context, ArrayList<Partido> objetos, int cardLayout) {
        this.context = context;
        this.objetos = objetos;
        this.cardLayout = cardLayout;
    }

    @NonNull
    @Override
    public TodoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoView = LayoutInflater.from(context).inflate(cardLayout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toDoView.setLayoutParams(layoutParams);
        return new TodoVH(toDoView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoVH holder, int position) {
        Partido partidos = objetos.get(position);
        holder.lblLocal.setText(partidos.getEquipo1());
        holder.lblVisitante.setText(partidos.getEquipo2());
        holder.txtGolesLocal.setText(String.valueOf(partidos.getGoles1()));
        holder.txtGolesVisitante.setText(String.valueOf(partidos.getGoles2()));

        holder.btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verPartido("Resultado", partidos).show();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Resultado.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARTIDOS", partidos);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return objetos.size();
    }

    public class TodoVH extends RecyclerView.ViewHolder{

        TextView lblLocal,lblVisitante,txtGolesLocal,txtGolesVisitante;
        Button btnResultado;
        public TodoVH(@NonNull View itemView){
            super(itemView);
            lblLocal = itemView.findViewById(R.id.lblEquipoLocal);
            lblVisitante = itemView.findViewById(R.id.lblEquipoVisitante);
            txtGolesLocal = itemView.findViewById(R.id.txtGolesLocal);
            txtGolesVisitante = itemView.findViewById(R.id.txtGolesVisitante);
            btnResultado = itemView.findViewById(R.id.btnResultados);
        }

    }

    public AlertDialog verPartido(String titulo, Partido partidos){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setCancelable(true);
        if(partidos.getGoles1()>partidos.getGoles2())
            builder.setMessage("El "+partidos.getEquipo1()+ " es el ganador");
        else if (partidos.getGoles2()>partidos.getGoles1()){
            builder.setMessage("El "+partidos.getEquipo2() +" es el ganador");
        }else {
            builder.setMessage("Han empatado");
        }
        return builder.create();
    }


}
