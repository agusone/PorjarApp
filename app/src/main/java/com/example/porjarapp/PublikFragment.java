package com.example.porjarapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.porjarapp.Adapter.OrderAdapter;
import com.example.porjarapp.Model.Order_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublikFragment extends Fragment {
    Button startdaftar;

    DatabaseReference reference;

    FirebaseDatabase firebaseDatabase;


    RecyclerView listtiket;
    ArrayList<Order_model>list;
    OrderAdapter orderAdapter;





    public PublikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_publik, container, false);

        startdaftar=view.findViewById(R.id.button_pesan);
        listtiket=view.findViewById(R.id.listtiket);
//        listtiket.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listtiket.setLayoutManager(layoutManager);
        list = new ArrayList<Order_model>();

        reference = FirebaseDatabase.getInstance().getReference().child("orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Order_model p = dataSnapshot1.getValue(Order_model.class);
                    list.add(p);

                }

                orderAdapter= new OrderAdapter(getContext(),list);
                listtiket.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "gagal ",
                              Toast.LENGTH_LONG).show();

            }
        });

        startdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), PesanTiketActivity.class);
                startActivity(a);
            }
        });


//        caborAdapter = new CaborAdapter(getContext(),list);







        return view;
    }

//    private void getAllOrder(){
//
//        list = new ArrayList<Order_model>();
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list.clear();
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
//                    Order_model order_model = postSnapshot.getValue(Order_model.class);
//
//                    list.add(order_model);
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }





}
