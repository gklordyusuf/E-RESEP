package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class dash_cari extends AppCompatActivity {

    private EditText cariPasien;
    private Button cariButton;
    DatabaseHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cari_pasien);

        cariPasien = findViewById(R.id.CariPasien);
        cariButton = findViewById(R.id.bt_cari);

    }

    //Intent intent = new Intent(dash_cari.this, form_resep.class);
    //startActivity(intent);
}

