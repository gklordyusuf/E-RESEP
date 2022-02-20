package com.example.frontend;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dokter_editdelete extends AppCompatActivity {

    private TextView textPenyakit;
    private TextView textDeskipsi;
    DatabaseHandler db;

    private EditText Penyakit;
    private EditText desk_p;
    private Button edit,delete;

    ArrayList<String> reseplist;
    ArrayAdapter adapter;
    ListView outputlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dokter_editdelete);
        
        textPenyakit = findViewById(R.id.textView_penyakit);
        textDeskipsi = findViewById(R.id.textView_desk);
        Penyakit = findViewById(R.id.penyakit);
        desk_p = findViewById(R.id.des_obat);
        edit = findViewById(R.id.bt_edit);
        delete = findViewById(R.id.bt_delete);
        
        db = new DatabaseHandler(this);
        reseplist = new ArrayList<>();

        getResepDatabyid();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String p_email = email.getText().toString();
                deleteResep();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String p_email = email.getText().toString();
                update();
            }
        });
    }
    public void getResepDatabyid() {
        ResepData resepData = db.getResepDatabyid(this.getIntent().getStringExtra("id"));
        Penyakit.setText(resepData.penyakit);
        desk_p.setText(resepData.daftarresep);
    }

    public void deleteResep(){
        if(db.deleteResep(this.getIntent().getStringExtra("id"))){
            Toast.makeText(this, "Delete Successfull",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(dokter_editdelete.this, manage_pasien.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Delete Failed",Toast.LENGTH_SHORT).show();
        }
    }

    public void update(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ResepData resepData2 = new ResepData();
        ResepData resepData3 = db.getResepDatabyid(this.getIntent().getStringExtra("id"));
        resepData2.id = resepData3.id;
        resepData2.userid = resepData3.userid;
        resepData2.penyakit= Penyakit.getText().toString();
        resepData2.daftarresep= desk_p.getText().toString();
        resepData2.tanggal= sdf.format(new Date());
        if(db.updateResep(resepData2)){
            Toast.makeText(this, "Update Successfull",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "update Failed",Toast.LENGTH_SHORT).show();
        }
    }
}
