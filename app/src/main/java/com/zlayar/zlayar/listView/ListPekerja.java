package com.zlayar.zlayar.listView;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.cariPekerja.CariPekerja;
import com.zlayar.zlayar.dataPekerja.DataPekerja;

import java.util.ArrayList;

/**
 * Created by IrfanRZ on 5/21/2018.
 */

public class ListPekerja extends BaseAdapter {
    private Context mContext;
    private static ArrayList<CariPekerja> dataUser;

    public ListPekerja(Context mContext, ArrayList<CariPekerja> dataUsers) {
        this.mContext = mContext;
        this.dataUser = dataUsers;
    }

    @Override
    public int getCount() {
        return dataUser.size();
    }

    @Override
    public Object getItem(int position) {
        return dataUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_user, null);
        TextView listName = (TextView)v.findViewById(R.id.list_name);
        TextView listDescription = (TextView)v.findViewById(R.id.list_description);
        TextView listSkill = (TextView)v.findViewById(R.id.list_skill);
        ImageView ktFoto = (ImageView) v.findViewById(R.id.kt_foto);

        listName.setText(dataUser.get(position).getName().toString());
        listDescription.setText(dataUser.get(position).getDeskripsi().toString());
        listSkill.setText(dataUser.get(position).getBidang().getNama());

        if (dataUser.get(position).getGambarKecil().getUrl().toString().isEmpty()){
            ktFoto.setImageResource(R.drawable.person);
        }
        else {
            Uri personUri = Uri.parse(dataUser.get(position).getGambarKecil().getUrl().toString());
            Picasso.with(mContext).load(personUri).fit().into(ktFoto);

            if (ktFoto.getDrawable() == null){
                ktFoto.setImageResource(R.drawable.person);
            }
        }

        v.setTag(dataUser.get(position).getIdUser());

        return v;
    }
}
