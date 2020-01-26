package com.example.hutangku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PiutangAdapter extends RecyclerView.Adapter<PiutangAdapter.MyViewHolder> {

    Context context;
    ArrayList<Piutang> piutang; // <Piutang> didapat dari model Piutang

    public PiutangAdapter(Context c, ArrayList<Piutang> p) {
        context = c;
        piutang = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_piutang,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.namapiutang.setText(piutang.get(i).getNamapiutang());
        myViewHolder.jumlahpiutang.setText(piutang.get(i).getJumlahpiutang());
        myViewHolder.deskripsipiutang.setText(piutang.get(i).getDeskripsipiutang());
        myViewHolder.tanggalpiutang.setText(piutang.get(i).getTanggalpiutang());
    }

    @Override
    public int getItemCount() {
        return piutang.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namapiutang, jumlahpiutang, deskripsipiutang, tanggalpiutang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namapiutang = itemView.findViewById(R.id.namapiutang);
            jumlahpiutang = itemView.findViewById(R.id.jumlahpiutang);
            deskripsipiutang = itemView.findViewById(R.id.deskripsipiutang);
            tanggalpiutang = itemView.findViewById(R.id.tanggalpiutang);
        }
    }
}
