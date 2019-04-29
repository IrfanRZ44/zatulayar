package com.zlayar.zlayar.recyclerView;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.dataBidang.BidangKategori;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IrfanRZ on 17/09/2018.
 */

public class RecyclerBidang extends RecyclerView.Adapter<RecyclerBidang.bidangViewHolder> {
    private ArrayList<BidangKategori> dataList;
    private Context context;

    public RecyclerBidang(ArrayList<BidangKategori> dataList) {
        this.dataList = dataList;
    }

    @Override
    public bidangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_kategori, parent, false);
        this.context = parent.getContext();
        return new bidangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(bidangViewHolder holder, int position) {
        String gbr;
        holder.txtTitle.setText(dataList.get(position).getNama().toString());
        gbr = dataList.get(position).getGambarKecil().getUrl().toString();
        if (dataList.get(position).getGambarKecil().getUrl().toString().isEmpty()){
//            holder.imageView.setBackgroundResource(R.drawable.person);
            holder.imageView.setImageResource(R.drawable.person);
        }
        else {
            Uri personUri = Uri.parse(dataList.get(position).getGambarKecil().getUrl().toString());
            Picasso.with(context).load(personUri).fit().into(holder.imageView);

            if (holder.imageView.getDrawable() == null){
//                holder.imageView.setBackgroundResource(R.drawable.person);
                holder.imageView.setImageResource(R.drawable.person);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle;
        private ImageView imageView;

        public bidangViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.listText);
            imageView = (ImageView) itemView.findViewById(R.id.listImage);
        }
    }
}
