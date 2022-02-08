 package uas.Kelompok7.inventory.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import uas.Kelompok7.inventory.R;
import uas.Kelompok7.inventory.model.inventorymodel;

 public class adapter extends RecyclerView.Adapter<adapter.Holder>{
    private ArrayList<inventorymodel> inventorymodels;
    private Activity activity;

    public adapter (Activity activity, ArrayList<inventorymodel> inventorymodels){
        this.inventorymodels = inventorymodels;
        this.activity = activity;

    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adafterdata, parent, false);
        Holder holder = new Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull adapter.Holder holder, int i) {
        inventorymodel model = inventorymodels.get(i);
        holder.id.setText(model.getId_barang());
        holder.nama_barang.setText(model.getNama_barang());
        holder.stock.setText(model.getStock());

    }

    @Override
    public int getItemCount() {
        return inventorymodels.size();
    }


    public static class Holder extends RecyclerView.ViewHolder {
        TextView id, nama_barang,stock;
        inventorymodel model;
        public Holder(@NonNull View v) {
            super(v);
            id = itemView.findViewById(R.id.id);
            nama_barang = itemView.findViewById(R.id.nama);
            stock = itemView.findViewById(R.id.stock);
        }
    }
}
