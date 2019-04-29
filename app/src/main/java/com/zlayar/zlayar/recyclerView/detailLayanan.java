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
import com.zlayar.zlayar.data.dataFavorit;
import com.zlayar.zlayar.dataPekerja.Layanan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IrfanRZ on 17/09/2018.
 */

public class detailLayanan extends RecyclerView.Adapter<detailLayanan.bidangViewHolder> {
    private ArrayList<Layanan> dataList;
    private Context ctx;

    public detailLayanan(ArrayList<Layanan> dataList, Context ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @Override
    public bidangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_user, parent, false);
        return new bidangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(bidangViewHolder holder, int position) {
        holder.listName.setText(dataList.get(position).getNama().toString());
        holder.listDescription.setText(dataList.get(position).getTeks().toString());
        holder.listSkill.setText(dataList.get(position).getBidang().toString());

        if (dataList.get(position).getGambarKecil().getUrl().toString().isEmpty()){
            holder.ktFoto.setImageResource(R.drawable.person);
        }
        else {
            Uri personUri = Uri.parse(dataList.get(position).getGambarKecil().getUrl().toString());
            Picasso.with(ctx).load(personUri).fit().into(holder.ktFoto);

            if (holder.ktFoto.getDrawable() == null){
                holder.ktFoto.setImageResource(R.drawable.person);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder{
        private TextView listName, listDescription, listSkill, listType;
        private ImageView ktFoto;

        public bidangViewHolder(View itemView) {
            super(itemView);

            listName = (TextView) itemView.findViewById(R.id.list_name);
            listDescription = (TextView) itemView.findViewById(R.id.list_description);
            listSkill = (TextView) itemView.findViewById(R.id.list_skill);
            listType = (TextView) itemView.findViewById(R.id.list_type);
            ktFoto = (ImageView) itemView.findViewById(R.id.kt_foto);
        }
    }
}
