package com.example.porjarapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.porjarapp.Adapter.CaborAdapter;
import com.example.porjarapp.Model.Cabor_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    DatabaseReference reference;
    RecyclerView listKabupaten;
    ArrayList<Cabor_model> list;
    CaborAdapter caborAdapter;




    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        listKabupaten = view.findViewById(R.id.listKabupaten);
//        listKabupaten.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listKabupaten.setLayoutManager(layoutManager);
        list = new ArrayList<Cabor_model>();
//
//        //get data from database
//
        reference = FirebaseDatabase.getInstance().getReference().child("cabor");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set code to retrive data and replace layout
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Cabor_model p = dataSnapshot1.getValue(Cabor_model.class);
                        list.add(p);
                    }

                    caborAdapter = new CaborAdapter(getContext(),list);
                    listKabupaten.setAdapter(caborAdapter);
                    caborAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

           }
        });

//


        return view;


    }










}
