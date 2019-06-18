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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class UpdateActivity extends AppCompatActivity {

    EditText jml_tiket;
    Spinner spiner_cabor;
    Button btn_update,btn_hapus;
    DatabaseReference reference;
    Integer kabNum= new Random().nextInt();
    String selectedCabor;
    String data_cabor[]= {"Sepak Bola","Basket","Bulu tangkis","Futsal","Sepak Takro","Volly","Silat"};

    ArrayAdapter<String> adapterCabor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        spiner_cabor = findViewById(R.id.edt_sp_cabor);
//        nama= findViewById(R.id.edt_namaLengkap);
        jml_tiket= findViewById(R.id.edt_jumlahtTiket);

        btn_hapus= findViewById(R.id.btnhapus);
        btn_update=findViewById(R.id.btn_update);


        adapterCabor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_cabor);
        spiner_cabor.setAdapter(adapterCabor);

        spiner_cabor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCabor = adapterCabor.getItem(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //get data from preview
//        nama.setText(getIntent().getStringExtra("namaLengkap"));
        jml_tiket.setText(getIntent().getStringExtra("jml_tiket"));
//        spiner_cabor.setSelection(Integer.parseInt(getIntent().getStringExtra("cabor")));


        final String id_tiketss = getIntent().getStringExtra("id_tiket");


        //event button
        reference = FirebaseDatabase.getInstance().getReference().child("orders").
                child("order"+id_tiketss);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//
                        dataSnapshot.getRef().child("cabor").setValue(selectedCabor);
                        dataSnapshot.getRef().child("jml_tiket").setValue(jml_tiket.getText().toString());
                        dataSnapshot.getRef().child("id_tiket").setValue(id_tiketss);

                        Intent next = new Intent(UpdateActivity.this,MenuActivity.class);
                        startActivity(next);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){

                           Toast.makeText(getApplicationContext(),"Berhasil Terhapus", Toast.LENGTH_SHORT).show();

                            Intent hapus = new Intent(UpdateActivity.this,MenuActivity.class);
                            startActivity(hapus);

                       } else {
                           Toast.makeText(getApplicationContext(),"Gagal", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
            }
        });


    }




}
