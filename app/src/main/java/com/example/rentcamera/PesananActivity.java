package com.example.rentcamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PesananActivity extends AppCompatActivity {
    int hsKamera,jlhLmsw,ttlHargasewa;
    String s_nama;

    Spinner adListkamera;
    TextView hargaKamera;
    EditText lamaSewa,namaPenyewa;

    Button btnok, btnsw, btnbtl;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";


    String list_kamera[] = {"Nikon D5200","Sony A7s","Canon M10","Canon M100"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        namaPenyewa = findViewById(R.id.nama);
        adListkamera = findViewById(R.id.kamera);
        lamaSewa = findViewById(R.id.lmsewa);
        hargaKamera = findViewById(R.id.harga);

        btnok = findViewById(R.id.btnok);
        btnsw = findViewById(R.id.btnsw);
        btnbtl = findViewById(R.id.btnklr);

        progressDialog = new ProgressDialog(PesananActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setTitle("Menyimpan...");

        ArrayAdapter adCam = new ArrayAdapter(PesananActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list_kamera);
        adListkamera.setAdapter(adCam);

        btnsw.setOnClickListener(view -> {
            if (namaPenyewa.getText().length()>0 && adListkamera.getSelectedItem().toString().length()>0 && lamaSewa.getText().length()>0 && hargaKamera.getText().length()> 0) {
                saveData(namaPenyewa.getText().toString(), adListkamera.getSelectedItem().toString(), lamaSewa.getText().toString(), hargaKamera.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent!=null) {
            id = intent.getStringExtra("id");
            namaPenyewa.setText(intent.getStringExtra("nama"));
            adListkamera.setSelected(Boolean.parseBoolean(intent.getStringExtra("kamera")));
            lamaSewa.setText(intent.getStringExtra("lmsewa"));
            hargaKamera.setText(intent.getStringExtra("harga"));
        }
    }
    private void  saveData(String nama, String kamera, String lmsewa, String harga) {
        Map<String, Object> user = new HashMap<>();
        user.put("nama", nama);
        user.put("kamera", kamera);
        user.put("lmsewa", lmsewa);
        user.put("harga", harga);

        progressDialog.show();
        if (id!=null){
            db.collection("users").document(id).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }


    public void buttonok (View view) {
        jlhLmsw = Integer.parseInt(lamaSewa.getText().toString());
        s_nama = namaPenyewa.getText().toString();

        if (adListkamera.getSelectedItem().toString()=="Nikon D5200"){
            hsKamera = 80000;
            ttlHargasewa = jlhLmsw * hsKamera;
            hargaKamera.setText(Integer.toString(ttlHargasewa));
        } else if (adListkamera.getSelectedItem().toString()=="Sony A7s") {
            hsKamera = 200000;
            ttlHargasewa = jlhLmsw * hsKamera;
            hargaKamera.setText(Integer.toString(ttlHargasewa));
        } else if (adListkamera.getSelectedItem().toString()=="Canon M10") {
            hsKamera = 100000;
            ttlHargasewa = jlhLmsw * hsKamera;
            hargaKamera.setText(Integer.toString(ttlHargasewa));
        } else if (adListkamera.getSelectedItem().toString()=="Canon M100") {
            hsKamera = 150000;
            ttlHargasewa = jlhLmsw * hsKamera;
            hargaKamera.setText(Integer.toString(ttlHargasewa));
        }
    }

    public void halUtama(View view) {
        Intent intent = new Intent(PesananActivity.this,ActivityMasuk.class);
        startActivity(intent);
    }
}