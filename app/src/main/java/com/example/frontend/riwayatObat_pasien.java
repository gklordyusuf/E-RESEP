package com.example.frontend;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class riwayatObat_pasien extends AppCompatActivity {

    DatabaseHandler db;
    private Button showBtn;
    ListView plist;
    ListView dlist;

    ArrayList<String> reseplist;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayatobat);

        String Usermail = getIntent().getStringExtra("UserMail");

        plist = findViewById(R.id.penyakit_list);

        db = new DatabaseHandler(this);
        reseplist = new ArrayList<>();


        ViewResep();
        plist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String id = plist.getItemAtPosition(i).toString();
                id = id.substring(0, id.indexOf(" "));
                Toast.makeText(riwayatObat_pasien.this, ""+id,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ViewResep(){
        String Usermail = getIntent().getStringExtra("UserMail");
        Cursor cursor = db.ViewResepbyemail(Usermail);
        if(cursor.getCount() == 0){
            Toast.makeText(this,"tidak ada data", Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                reseplist.add(cursor.getString(0)+". "+cursor.getString(3)+" "+cursor.getString(2));
            }
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, reseplist);
        plist.setAdapter(adapter);
        }
    }
}

