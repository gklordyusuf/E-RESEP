package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class dashboard_dokter extends AppCompatActivity {

    private ImageView logores;
    private ImageView logopas;
    private ImageView logottg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dokter_dashboard);

        //Memasukkan variabel
        logores = findViewById(R.id.obat_dash);
        logopas = findViewById(R.id.pasien_dash);
        logottg = findViewById(R.id.ttg_dash);

    }

    public void formResep (View view) {
        Intent moveActivity = new Intent(this, form_resep.class);
        startActivity(moveActivity);
    }
    public void editDelete(View view){
        Intent moveActivity = new Intent(dashboard_dokter.this, manage_pasien.class);
        startActivity(moveActivity);
    }

}
