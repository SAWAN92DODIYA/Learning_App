package com.example.learningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>  {


    Context context ;
    RecyclerContactAdapter(Context context)
    {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.device_row,parent,false);
        ViewHolder  viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
           TextView device_name , device_model, device_price;
            public ViewHolder(View itemView)
            {
                super(itemView);
                device_name = itemView.findViewById(R.id.textView_device_name);
                device_model = itemView.findViewById(R.id.text_device_model);
                device_price = itemView.findViewById(R.id.text_device_price);
            }
        }

}
