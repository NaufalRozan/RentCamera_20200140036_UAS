package com.example.rentcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class LihatDataActivity extends AppCompatActivity {
    private TextView nama, kamera, lmsewa, harga;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        nama = findViewById(R.id.nama);
        kamera = findViewById(R.id.kamera);
        lmsewa = findViewById(R.id.lmsewa);
        harga = findViewById(R.id.harga);

        progressDialog = new ProgressDialog(LihatDataActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");

        Intent intent = getIntent();
        if(intent != null){
            nama.setText(intent.getStringExtra("nama"));
            kamera.setText(intent.getStringExtra("kamera"));
            lmsewa.setText(intent.getStringExtra("lmsewa"));
            harga.setText(intent.getStringExtra("harga"));
        }
    }
}