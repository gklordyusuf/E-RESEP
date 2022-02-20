package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class dashboard_pasien extends AppCompatActivity {

    private ImageView gresep;
    private ImageView gtentang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasien_dashboard);

        gresep = findViewById(R.id.pasien_resep);
        gtentang = findViewById(R.id.pasien_ttg);
    }

    public void tampil_obat (View view) {
        Intent pindahActivity = new Intent(this, riwayatObat_pasien.class);
        String usermail = getIntent().getStringExtra("email");
        pindahActivity.putExtra("UserMail",usermail);
        startActivity(pindahActivity);
    }
}
