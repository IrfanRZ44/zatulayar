package com.zlayar.zlayar.listView;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zlayar.zlayar.FragmentMain.FilterBidang;
import com.zlayar.zlayar.FragmentMain.FragmentCariJasa;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.cariLayanan.CariLayanan;
import com.zlayar.zlayar.dataBidang.BidangKategori;
import com.zlayar.zlayar.dataBidang.SubBidang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IrfanRZ on 5/21/2018.
 */

public class ListSubBidang extends BaseAdapter {
    private Context mContext;
    private ArrayList<SubBidang> dataUser;

    public ListSubBidang(Context mContext, ArrayList<SubBidang> dataUsers) {
        this.mContext = mContext;
        this.dataUser = dataUsers;
    }

    @Override
    public int getCount() {
        return dataUser.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_sub_bidang, null);
        TextView listName = (TextView) v.findViewById(R.id.textSub);

        listName.setText(dataUser.get(position).getNama().toString());

        v.setTag(dataUser.get(position).getIdBidang());

        return v;
    }
}
