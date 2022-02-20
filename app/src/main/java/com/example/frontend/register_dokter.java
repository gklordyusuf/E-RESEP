package com.example.frontend;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class register_dokter extends AppCompatActivity {

    public EditText isiuser;
    public EditText isiemail;
    public EditText isipasword;
    public Button dokterDaftar;
    public EditText kodeRs;
    DatabaseHandler db;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_dokter);

        // memasukkan variabel yang sdh terdeklarasi.
        isiuser = findViewById(R.id.user2);
        isiemail = findViewById(R.id.email2);
        isipasword = findViewById(R.id.kunci2);
        dokterDaftar = findViewById(R.id.daftar);
        kodeRs = findViewById(R.id.kodeRS);
        db = new DatabaseHandler(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void Masuk (View view) {
        String user = isiuser.getText().toString();
        String email = isiemail.getText().toString();
        String password = isipasword.getText().toString();
        String role = "DOKTER";
        String KodeRS = kodeRs.getText().toString();


        long val = db.addUser(user,email,password, role);
        if (val > 0 && KodeRS.equals("abc")){
            boolean isValid = true;
            if (isEmpty(isiuser)) {
                isiuser.setError("tolong diisi");
                isValid = false;
            }
            if (isEmpty(isiemail)) {
                //Toast t = Toast.makeText(this, "Tolong diisi!", Toast.LENGTH_SHORT);
                //t.show();
                isiemail.setError("tolong diisi");
                isValid = false;
            }

            if (isEmpty(isipasword)) {
                isipasword.setError("tolong diisi");
                isValid = false;
            }
            if (isValid) {
                Toast.makeText(register_dokter.this, "Anda telah terdaftar!", Toast.LENGTH_SHORT).show();
                Intent intentMoveToDash = new Intent(register_dokter.this, LoginActivity.class);
                this.finish();
                startActivity(intentMoveToDash);
            } else {
                Toast.makeText(register_dokter.this, "Pendaftaran gagal!", Toast.LENGTH_SHORT).show();
            }
        }
        }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), register.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
    }


