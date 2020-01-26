package com.example.hutangku;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HutangAdapter extends RecyclerView.Adapter<HutangAdapter.MyViewHolder> {

    Context context;
    ArrayList<Hutang> hutang; // <Hutang> didapat dari model Hutang

    public HutangAdapter(Context c, ArrayList<Hutang> p) {
        context = c;
        hutang = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hutang,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nama.setText(hutang.get(i).getNama());
        myViewHolder.jumlah.setText(hutang.get(i).getJumlah());
        myViewHolder.deskripsi.setText(hutang.get(i).getDeskripsi());
        myViewHolder.tanggal.setText(hutang.get(i).getTanggal());

        final String getNamaHutang = hutang.get(i).getNama();
        final String getJumlahHutang = hutang.get(i).getJumlah();
        final String getDeskripsiHutang = hutang.get(i).getDeskripsi();
        final String getTanggalHutang = hutang.get(i).getTanggal();
        final String getKeyHutang = hutang.get(i).getKeyhutang();

        // Bisa klik per data item hutang
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,EditHutangAct.class);
                aa.putExtra("nama", getNamaHutang);
                aa.putExtra("jumlah", getJumlahHutang);
                aa.putExtra("deskripsi", getDeskripsiHutang);
                aa.putExtra("tanggal", getTanggalHutang);
                aa.putExtra("keyhutang", getKeyHutang);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hutang.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama, jumlah, deskripsi, tanggal, keyhutang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            jumlah = itemView.findViewById(R.id.jumlah);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            tanggal = itemView.findViewById(R.id.tanggal);
        }
    }
}
