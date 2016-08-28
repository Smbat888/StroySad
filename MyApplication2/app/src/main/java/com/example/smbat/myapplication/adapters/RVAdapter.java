package com.example.smbat.myapplication.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smbat.myapplication.R;
import com.example.smbat.myapplication.models.ProductCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smbat on 8/8/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    ArrayList<ProductCard> persons;
    
    public RVAdapter(ArrayList<ProductCard> persons){
        this.persons = persons;
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.personName.setText(persons.get(position).getProductTitle());
        holder.personAge.setText(persons.get(position).getProductPrice());
        holder.personPhoto.setImageResource(persons.get(position).getProductImage());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.product_title);
            personAge = (TextView)itemView.findViewById(R.id.product_price);
            personPhoto = (ImageView)itemView.findViewById(R.id.product_image);
        }
    }
}