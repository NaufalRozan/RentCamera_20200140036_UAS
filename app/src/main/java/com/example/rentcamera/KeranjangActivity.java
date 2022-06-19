package com.example.rentcamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rentcamera.adapter.UserAdapter;
import com.example.rentcamera.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class KeranjangActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FloatingActionButton fab;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private List<User> list = new ArrayList<>();
    private UserAdapter userAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.btn_add);

        progressDialog = new ProgressDialog(KeranjangActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        userAdapter = new UserAdapter(getApplicationContext(), list);
        userAdapter.setDialog(new UserAdapter.Dialog() {
            @Override
            public void onClick(final int pos) {
                final CharSequence[] dialogItem = {"Edit", "Hapus", "Lihat Data", "Checkout"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(KeranjangActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), PesananActivity.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("nama", list.get(pos).getNama());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                            case 2:
                                Intent intent1 = new Intent(getApplicationContext(), LihatDataActivity.class);
                                intent1.putExtra("id", list.get(pos).getId());
                                intent1.putExtra("nama", list.get(pos).getNama());
                                intent1.putExtra("kamera", list.get(pos).getKamera());
                                intent1.putExtra("lmsewa", list.get(pos).getLmsewa());
                                intent1.putExtra("harga", list.get(pos).getHarga());
                                startActivity(intent1);
                                break;
                            case 3:
                                Intent intent2 = new Intent(getApplicationContext(), CheckoutActivity.class);
                                intent2.putExtra("id", list.get(pos).getId());
                                intent2.putExtra("nama", list.get(pos).getNama());
                                intent2.putExtra("kamera", list.get(pos).getKamera());
                                intent2.putExtra("lmsewa", list.get(pos).getLmsewa());
                                intent2.putExtra("harga", list.get(pos).getHarga());
                                deleteData(list.get(pos).getId());
                                startActivity(intent2);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(userAdapter);

        fab.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), PesananActivity.class));
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }


    private void getData() {
        progressDialog.show();

        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        User user = new User(document.getString("nama"), document.getString("kamera"), document.getString("lmsewa"), document.getString("harga"));
                        user.setId(document.getId());
                        list.add(user);
                    }
                    userAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "Data gagal di ambil!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }


    private void deleteData(String id){
        progressDialog.show();
        db.collection("users").document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Data gagal di hapus!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                getData();
            }
        });
    }
}