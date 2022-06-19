package com.example.rentcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ActivityDaftar extends AppCompatActivity {
    EditText ednama, edemail, edpass, edrepass;
    Button btnregis;
    String nama, email, password, repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        btnregis = findViewById(R.id.button3);
        ednama = findViewById(R.id.edNama);
        edemail = findViewById(R.id.edEmail);
        edpass = findViewById(R.id.edPassword);
        edrepass = findViewById(R.id.edrePass);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = ednama.getText().toString();
                email = edemail.getText().toString();
                password = edpass.getText().toString();
                repass = edrepass.getText().toString();



                if (nama.isEmpty() && email.isEmpty() && password.isEmpty()&& repass.isEmpty())
                {
                    Snackbar.make(view, "Isi Semua Data!", Snackbar.LENGTH_LONG).show();
                }
                if (ednama.getText().toString().length() == 0) {
                    ednama.setError("Masukkan Nama");
                }
                if (edemail.getText().toString().length() == 0) {
                    edemail.setError("Masukkan Email");
                }
                if (edpass.getText().toString().length() == 0) {
                    edpass.setError("Masukkan Password");
                }
                if (edrepass.getText().toString().length() == 0) {
                    edrepass.setError("Masukkan Password Kembali");
                }
                else {
                    if (edpass.getText().toString().equals(edrepass.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil . . .",
                                Toast.LENGTH_LONG).show();

                        Bundle b = new Bundle();
                        b.putString("a", nama.trim());

                        Intent i = new Intent(getApplicationContext(), ActivityMasuk.class);
                        i.putExtras(b);
                        startActivity(i);
                    }
                    else {
                        Snackbar.make(view, "Password dan Repassword harus sama!!!!",
                                Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}