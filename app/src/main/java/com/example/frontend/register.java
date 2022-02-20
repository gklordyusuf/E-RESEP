package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {

    public EditText isiuser;
    public EditText isiemail;
    public EditText isipasword;
    public Button dokterRegister;
    DatabaseHandler db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        // memasukkan variabel yang sdh terdeklarasi.
        isiuser = findViewById(R.id.user2);
        isiemail = findViewById(R.id.email2);
        isipasword = findViewById(R.id.kunci2);
        dokterRegister = findViewById(R.id.dokterRegister);
        db = new DatabaseHandler(this);

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void onClick(View view) {
        Intent i = new Intent(register.this, register_dokter.class);
        startActivity(i);
    }

    public void halPasien(View view) {
        String user = isiuser.getText().toString();
        String email = isiemail.getText().toString();
        String password = isipasword.getText().toString();
        String role = "pasien";

        long val = db.addUser(user, email, password, role);

        if (val > 0) {
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
                Toast.makeText(register.this, "Anda telah terdaftar!", Toast.LENGTH_SHORT).show();
                Intent intentMoveToDash = new Intent(register.this, LoginActivity.class);
                this.finish();
                startActivity(intentMoveToDash);
            } else {
                Toast.makeText(register.this, "Pendaftaran gagal!", Toast.LENGTH_SHORT).show();
            }

        }


    }
}