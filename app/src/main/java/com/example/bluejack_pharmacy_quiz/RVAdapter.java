package com.example.bluejack_pharmacy_quiz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter <RVAdapter.MyViewHolder> {
    private Context context;
    int []arr;
    String []title = {"Panadol","Degirol","Blackmores BIO C","Alpara","Blackmores Gold","Blackmores Multivitamin"};

    public RVAdapter(int[] arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(arr[position]);
        holder.titleView.setText(title[position]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Product.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView titleView;
        TextView priceView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            Context context;
            imageView = itemView.findViewById(R.id.product_pic);
            titleView = itemView.findViewById(R.id.product_title);
            priceView = itemView.findViewById(R.id.product_price);

            context = itemView.getContext();

        }

        @Override
        public void onClick(View view) {
//            final Intent intent;
//            switch (getAdapterPosition()){
//                case 0:
//                    intent = new Intent(context, Product.class);
//                    break;
//                case 1:
//                    intent = new Intent(context, Product.class);
//                    break;
//                case 2:
//                    intent = new Intent(context, Product.class);
//                    break;
//                case 4:
//                    intent = new Intent(context, Product.class);
//                    break;
//                case 5:
//                    intent = new Intent(context, Product.class);
//                    break;
//                default:
//                    intent = new Intent(context, Product.class);
//                    break;
//            }
//            context.startActivity(intent);
//            context.startActivity(new Intent(context, Product.class));
        }
    }

}
