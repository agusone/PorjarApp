package com.example.porjarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SigninActivity extends AppCompatActivity {

    TextView create_account;
    Button btn_sign_in;
    EditText xusername,xpassword;
    DatabaseReference reference;
    String USERNAME_KEY= "usernamekey";
    String username_key ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        btn_sign_in = findViewById(R.id.btn_signin);
        create_account= findViewById(R.id.btnnewaccount);
        xusername= findViewById(R.id.in_username);
        xpassword= findViewById(R.id.in_password);


        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btn_sign_in.setText("Loading ...");
              final   String username= xusername.getText().toString();
               final String password=xpassword.getText().toString();

                reference= FirebaseDatabase.getInstance().getReference()
                        .child("users").child(username);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            //get password from firebase
                            String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                            //validasi pass
                            if(password.equals(passwordFromFirebase)){

//                                Toast.makeText(getApplicationContext()," user dan password tedaftar :) !",
//                                        Toast.LENGTH_SHORT).show();

                                //simpan username(key)kepada local
                                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(username_key, xusername.getText().toString());
                                editor.apply();

                                Intent gotohome= new Intent(SigninActivity.this,MenuActivity.class);
                                startActivity(gotohome);

                            }else {
                                Toast.makeText(getApplicationContext(),"password tidak tedaftar !",
                                        Toast.LENGTH_SHORT).show();
                            }


                        }else {
                            Toast.makeText(getApplicationContext(),"Username tidak tedaftar :(!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newacc= new Intent(SigninActivity.this,RegisterActivity.class);
                startActivity(newacc);
            }
        });









    }
}
