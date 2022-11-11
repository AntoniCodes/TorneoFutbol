package com.example.torneofutbol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.torneofutbol.databinding.ActivityResultadoBinding;

public class Resultado extends AppCompatActivity {

    private ActivityResultadoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        Partido partido = (Partido) bundle.getSerializable("PARTIDOS");
        binding.lblEquipoLocalRes.setText(partido.getEquipo1());
        binding.lblEquipoVisitanteRes.setText(partido.getEquipo2());
        binding.lblGolesLocalRes.setText(String.valueOf(partido.getGoles1()));
        binding.lblGolesVisitantesRess.setText(String.valueOf(partido.getGoles2()));
        binding.lblResumenRes.setText(partido.getResultado());


    }
}