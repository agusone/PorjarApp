package com.example.porjarapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.porjarapp.Model.Cabor_model;
import com.example.porjarapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CaborAdapter extends RecyclerView.Adapter<CaborAdapter.MyViewholder> {

 private   Context context;
  private  ArrayList<Cabor_model> cabor_model;

    public CaborAdapter(Context c, ArrayList<Cabor_model> p){
        this.context = c;
       this.cabor_model = p;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewholder(LayoutInflater.from(context).inflate(R.layout.item_cabor, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder myViewholder, int i) {

//        int no = i+1;

         myViewholder.nama.setText(cabor_model.get(i).getNama());
         myViewholder.status.setText(cabor_model.get(i).getStatus());
//         myViewholder.logo.setImageResource(Integer.parseInt(cabor_model.get(i).getLogo()));
//         myViewholder.no.setText(String.valueOf(no));
         Picasso.get().load(cabor_model.get(i).getLogo()).into(myViewholder.logo);
    }

    @Override
    public int getItemCount() {
        return cabor_model.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {

        TextView nama,status;
        ImageView logo;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            nama=(TextView)itemView.findViewById(R.id.tv_cabor);
            status= (TextView)itemView.findViewById(R.id.tv_status);
            logo = (ImageView) itemView.findViewById(R.id.image_logo);

        }
    }

}
