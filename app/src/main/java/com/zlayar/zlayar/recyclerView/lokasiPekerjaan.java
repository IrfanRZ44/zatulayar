package com.zlayar.zlayar.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlayar.zlayar.R;
import com.zlayar.zlayar.dataPekerja.Lokasi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IrfanRZ on 17/09/2018.
 */

public class lokasiPekerjaan extends RecyclerView.Adapter<lokasiPekerjaan.bidangViewHolder> {
    private ArrayList<Lokasi> dataList;

    public lokasiPekerjaan(ArrayList<Lokasi> dataList) {
        this.dataList = dataList;
    }

    @Override
    public bidangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_bidang, parent, false);
        return new bidangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(bidangViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getProvinsi().getKota().toString());
        holder.imageView.setBackgroundResource(R.drawable.ic_sub_lokasi);

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama;
        private ImageView imageView;

        public bidangViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.textBidang);
            imageView = (ImageView) itemView.findViewById(R.id.img_check);
        }
    }
}
