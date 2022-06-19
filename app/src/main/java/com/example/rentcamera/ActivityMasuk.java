package com.example.rentcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityMasuk extends AppCompatActivity {
    Button psnbtn, btnout, btninfo;
    FloatingActionButton fabcart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        psnbtn = findViewById(R.id.btnpsn);
        btnout = findViewById(R.id.btnout);
        btninfo = findViewById(R.id.btninfo);
        fabcart = findViewById(R.id.fab);

        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(i);
            }
        });

        psnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PesananActivity.class);
                startActivity(i);
            }
        });

        fabcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), KeranjangActivity.class);
                startActivity(i);
            }
        });

        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}