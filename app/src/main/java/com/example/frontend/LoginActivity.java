package com.example.frontend;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{
    private TextView tampilmail;
    private TextView tampilpass;
    private EditText isimail;
    private EditText isipass;
    private Button Login;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        tampilmail = findViewById(R.id.email1);
        isimail = findViewById(R.id.email2);
        tampilpass = findViewById(R.id.kunci1);
        isipass = findViewById(R.id.kunci2);
        Login = findViewById(R.id.login);

        TextView isidata = findViewById(R.id.input);
        db = new DatabaseHandler(this);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String email = isimail.getText().toString();
                    String password = isipass.getText().toString();

                    boolean CheckData = db.checkUser(email, password);

                    if (CheckData) {
                        UserData data = db.getUserData(email,password);
                            if(data.getRole().equals("pasien")){
                                Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent (LoginActivity.this, dashboard_pasien.class);
                                i.putExtra( "email" , email);
                                startActivity(i);
                                isimail.getText().clear();
                                isipass.getText().clear();

                    }       else {
                                Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent (LoginActivity.this, dashboard_dokter.class);
                                i.putExtra( "email" , email);
                                startActivity(i);
                                isimail.getText().clear();
                                isipass.getText().clear();

                    }

                }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("username atau password salah").setNegativeButton("cobalagi", null).create().show();
                    }
            }
        });
    }
    public void isidata (View v) {
        Intent moveActivity = new Intent(this, register.class);
        startActivity(moveActivity);
    }
}






//if (email.equals("pab") && password.equals("miniProject"))