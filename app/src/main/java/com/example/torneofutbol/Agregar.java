package com.example.torneofutbol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.torneofutbol.databinding.ActivityAgregarBinding;
import com.example.torneofutbol.databinding.ActivityMainBinding;

public class Agregar extends AppCompatActivity {

    private ActivityAgregarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        binding = ActivityAgregarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.txtGoles1.getText().toString().isEmpty() || binding.txtGoles2.getText().toString().isEmpty() || binding.spinEquipo1.getSelectedItemPosition() == 0 || binding.spinEquipo2.getSelectedItemPosition() == 0 || binding.txtResumen.getText().toString().isEmpty()){
                    Toast.makeText(Agregar.this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
                }else{
                    Partido partidos = new Partido(binding.spinEquipo1.getSelectedItem().toString(),
                            binding.spinEquipo2.getSelectedItem().toString(),
                            Integer.parseInt(binding.txtGoles1.getText().toString()),
                            Integer.parseInt(binding.txtGoles2.getText().toString()),
                            binding.txtResumen.getText().toString());


                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PARTIDOS", partidos);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}