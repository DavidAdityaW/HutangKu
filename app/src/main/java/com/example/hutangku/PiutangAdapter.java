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

        final String getNamapiutang = piutang.get(i).getNamapiutang();
        final String getJumlahpiutang = piutang.get(i).getJumlahpiutang();
        final String getDeskripsipiutang = piutang.get(i).getDeskripsipiutang();
        final String getTanggalpiutang = piutang.get(i).getTanggalpiutang();
        final String getKeypiutang = piutang.get(i).getKeypiutang();


        // Bisa klik per data item piutang
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,EditPiutangAct.class);
                aa.putExtra("namapiutang", getNamapiutang);
                aa.putExtra("jumlahpiutang", getJumlahpiutang);
                aa.putExtra("deskripsipiutang", getDeskripsipiutang);
                aa.putExtra("tanggalpiutang", getTanggalpiutang);
                aa.putExtra("keypiutang", getKeypiutang);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return piutang.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namapiutang, jumlahpiutang, deskripsipiutang, tanggalpiutang, keypiutang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namapiutang = itemView.findViewById(R.id.namapiutang);
            jumlahpiutang = itemView.findViewById(R.id.jumlahpiutang);
            deskripsipiutang = itemView.findViewById(R.id.deskripsipiutang);
            tanggalpiutang = itemView.findViewById(R.id.tanggalpiutang);
        }
    }
}
