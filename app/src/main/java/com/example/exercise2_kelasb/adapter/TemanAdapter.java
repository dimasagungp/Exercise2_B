package com.example.exercise2_kelasb.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exercise2_kelasb.R;
import com.example.exercise2_kelasb.database.Teman;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private OnItemSelectedListener listener;
    private  boolean withContextMenu;

    public TemanAdapter(ArrayList<Teman> listData, OnItemSelectedListener listener1) {
        this.listData = listData;
        this.listener = listener1;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm,tlp;

        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelpon();

        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);
        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardku;
        private TextView namaTxt,telponTxt;

        OnItemSelectedListener onItemSelectedListener;
        public TemanViewHolder(View view, OnItemSelectedListener onItemSelectedListener) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);

            this.onItemSelectedListener  = onItemSelectedListener;
            view.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            int position = getAdapterPosition();
            if (listener != null) {
                listener.onSelected(listData.get(position));
            }
            }
        }

    public interface OnItemSelectedListener {
        void onSelected(Teman object);
        void onMenu(Teman object, MenuItem item);
    }


}
