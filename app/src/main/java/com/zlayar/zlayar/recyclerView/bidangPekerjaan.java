package com.zlayar.zlayar.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlayar.zlayar.R;
import com.zlayar.zlayar.dataPekerja.SubBidang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IrfanRZ on 17/09/2018.
 */

public class bidangPekerjaan extends RecyclerView.Adapter<bidangPekerjaan.bidangViewHolder> {
    private ArrayList<SubBidang> dataList;

    public bidangPekerjaan(ArrayList<SubBidang> dataList) {
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
//        holder.txtNama.setText(dataList.get(positionPekerja).getBidang());
        holder.txtNama.setText(dataList.get(position).getSubBidang().getNama());
        holder.imageView.setBackgroundResource(R.drawable.ic_check);
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
