package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class form_resep extends AppCompatActivity {

    private EditText p_obat;
    private EditText d_obat;
    private EditText e_obat;
    private Button submitResep;
    DatabaseHandler db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_resep);

        p_obat = findViewById(R.id.penyakit);
        d_obat = findViewById(R.id.des_obat);
        e_obat = findViewById(R.id.email2);
        submitResep = findViewById(R.id.bt_keluhan);
        db = new DatabaseHandler(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Button btKeluhan = findViewById(R.id.bt_keluhan);
        btKeluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String penyakit = p_obat.getText().toString();
                String deskripsi = d_obat.getText().toString();
                String user = e_obat.getText().toString();
                String tanggal = sdf.format(new Date());

                long val = db.addResep(user, penyakit, deskripsi, tanggal);
                if(val > 0) {
                    Toast t = Toast.makeText(form_resep.this, "Berhasil menambahkan resep", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(form_resep.this, dashboard_dokter.class);
                    startActivity(i);
                }else{
                    Toast t = Toast.makeText(form_resep.this, "Gagal menambahkan resep", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }
}
