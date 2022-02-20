package com.example.frontend;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class manage_pasien extends AppCompatActivity {

    ListView listView;
    private EditText email;
    private Button sButton;
    DatabaseHandler db;

    ArrayList<String> reseplist;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_pasien);

        email = findViewById(R.id.email_search);
        sButton = findViewById(R.id.button_search);

        listView = findViewById(R.id.listview);
        reseplist = new ArrayList<>();
        db = new DatabaseHandler(this);

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String p_email = email.getText().toString();
                ViewResep();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
            String id = listView.getItemAtPosition(i).toString();
            id = id.substring(0, id.indexOf(" "));
            Intent moveActivity = new Intent(manage_pasien.this, dokter_editdelete.class);
            moveActivity.putExtra("id",id);
            Toast.makeText(manage_pasien.this, ""+id,Toast.LENGTH_SHORT).show();
            startActivity(moveActivity);
        }
    });
}
    private void ViewResep(){
        String Usermail = email.getText().toString();
        Cursor cursor = db.ViewResepbyemail(Usermail);
        if(cursor.getCount() == 0){
            Toast.makeText(this,"tidak ada data", Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                reseplist.add(cursor.getString(0)+" "+cursor.getString(3)+" "+cursor.getString(4));
            }
            adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, reseplist);
            listView.setAdapter(adapter);

        }
    }
}