package com.example.porjarapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.porjarapp.UpdateActivity;
import com.example.porjarapp.Model.Order_model;
import com.example.porjarapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

   private Context context;
   private ArrayList<Order_model> order_models;

    public OrderAdapter(Context c,ArrayList<Order_model> p){
        this.context = c;
        this.order_models = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tiket, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

//        myViewHolder.namaLengkap.setText(order_models.get(i).getNamaLengkap());
        myViewHolder.cabor.setText(order_models.get(i).getCabor());
        myViewHolder.jml_tiket.setText(order_models.get(i).getJml_tiket());




//        final String getNamaLengkap = order_models.get(i).getNamaLengkap();
//        final String getCabor = order_models.get(i).getCabor();
        final String getJml_tiket = order_models.get(i).getJml_tiket();
        final String getId_tiket = order_models.get(i).getId_tiket();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context, UpdateActivity.class);


//                aa.putExtra("cabor",getCabor);
                aa.putExtra("jml_tiket",getJml_tiket);
                aa.putExtra("id_tiket",getId_tiket);
                context.startActivity(aa);
            }
        });

    }

    @Override
    public int getItemCount() {
        return order_models.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        TextView namaLengkap,cabor,jml_tiket, id_tiket;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

//            namaLengkap = (TextView) itemView.findViewById(R.id.tv_namapemesan);
            cabor = (TextView) itemView.findViewById(R.id.tv_cabor);
            jml_tiket= (TextView) itemView.findViewById(R.id.tv_jmltiket);
        }
    }
}
