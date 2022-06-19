package com.example.rentcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {
    Button home;
    private TextView nama, kamera, lmsewa, harga;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        nama = findViewById(R.id.nama);
        kamera = findViewById(R.id.kamera);
        lmsewa = findViewById(R.id.lmsewa);
        harga = findViewById(R.id.harga);
        home = findViewById(R.id.home);

        progressDialog = new ProgressDialog(CheckoutActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityMasuk.class);
                startActivity(i);
            }
        });

        Intent intent = getIntent();
        if(intent != null) {
            nama.setText(intent.getStringExtra("nama"));
            kamera.setText(intent.getStringExtra("kamera"));
            lmsewa.setText(intent.getStringExtra("lmsewa"));
            harga.setText(intent.getStringExtra("harga"));
        }
    }
}