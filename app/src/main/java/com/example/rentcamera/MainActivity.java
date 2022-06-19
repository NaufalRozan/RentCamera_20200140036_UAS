package com.example.rentcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ednama, edpassword;
    Button btnLogin, btnRegister;
    String nama, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin=findViewById(R.id.button);
        btnRegister=findViewById(R.id.button2);
        ednama=findViewById(R.id.edNama);
        edpassword=findViewById(R.id.edPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = ednama.getText().toString();
                password = edpassword.getText().toString();

                String Nama = "Naufal";
                String Pass = "Naufal123";

                if (nama.isEmpty() && password.isEmpty()) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Nama dan Password wajib diisi!!!",
                            Toast.LENGTH_LONG);
                    t.show();

                } else if (nama.equals(Nama) && password.equals(Pass)) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Login Berhasil",
                            Toast.LENGTH_LONG);

                    t.show();

                    Intent i = new Intent(getApplicationContext(), ActivityMasuk.class);
                    startActivity(i);


                } else if (nama.equals(Nama)) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Password Salah", Toast.LENGTH_LONG);
                    t.show();
                } else if (password.equals(Pass)) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Nama Salah", Toast.LENGTH_LONG);
                    t.show();
                }else if (ednama.getText().toString().length() == 0)
                {ednama.setError("Masukkan Nama!");

                }
                else {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Email dan Password Salah", Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityDaftar.class);
                startActivity(i);
            }
        });
    }
}