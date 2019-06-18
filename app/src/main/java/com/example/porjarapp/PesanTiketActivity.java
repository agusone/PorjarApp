package com.example.porjarapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class PesanTiketActivity extends AppCompatActivity {

    EditText jml_tiket;
    Spinner spiner_cabor;
    Button btnpesan,btncancel;
    DatabaseReference reference;

    Integer tiketNum = new Random().nextInt();

    String selectedCabor;
    String data_cabor[]= {"Sepak Bola","Bola Basket","Bulu tangkis","Atletik","Sepak Takraw","Volly","Pancak Silat"};

    String id_tikett = Integer.toString(tiketNum);

    ArrayAdapter<String> adapterCabors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_tiket);

//        nama = findViewById(R.id.input_namaLengkap);
        spiner_cabor= findViewById(R.id.input_sp_cabor);
        jml_tiket= findViewById(R.id.input_jumlahtTiket);

        btncancel = findViewById(R.id.btn_batal);
        btnpesan = findViewById(R.id.btn_daftar);

        adapterCabors = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_cabor);
        spiner_cabor.setAdapter(adapterCabors);


        spiner_cabor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCabor = adapterCabors.getItem(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("orders").child("order"+tiketNum);

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        dataSnapshot.getRef().child("cabor").setValue(selectedCabor);
                        dataSnapshot.getRef().child("jml_tiket").setValue(jml_tiket.getText().toString());
                        dataSnapshot.getRef().child("id_tiket").setValue(id_tikett);

                        Intent next = new Intent(PesanTiketActivity.this,MenuActivity.class);

                        startActivity(next);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(PesanTiketActivity.this,PublikFragment.class);
                startActivity(c);
            }
        });



    }



}
