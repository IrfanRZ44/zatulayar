package com.zlayar.zlayar.FragmentMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zlayar.zlayar.detailJasa.layananDetail;
import com.zlayar.zlayar.detailJasa.pekerjaDetail;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.identityFirebase.ItemClickSupport;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;
import com.zlayar.zlayar.recyclerView.favoritPekerja;

/**
 * Created by IrfanRZ on 10/09/2018.
 */

public class fragmentFavorit extends android.support.v4.app.Fragment implements ItemClickSupport.OnItemClickListener{
    private View view;
    private RecyclerView rcPekerja;
    private ProgressBar progressBar;
    private TextView textNotFound;
    private RelativeLayout rlPekerja;

    public fragmentFavorit(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorit, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_BarFav);
        textNotFound = (TextView) view.findViewById(R.id.textNotFound);
        rcPekerja = (RecyclerView) view.findViewById(R.id.listUserPekerja);
        rlPekerja = (RelativeLayout) view.findViewById(R.id.pekerja);

        progressBar.setVisibility(View.VISIBLE);
        textNotFound.setVisibility(View.GONE);
        rlPekerja.setVisibility(View.GONE);

        try {
            if (new SharedPrefUtil(getContext()).getFavorit("favorit") != null){
                //set the adapter pekerja
                favoritPekerja adapterBidang = new favoritPekerja(new SharedPrefUtil(getContext()).getFavorit("favorit"), getContext());
                RecyclerView.LayoutManager layoutBidang = new LinearLayoutManager(getContext());
                rcPekerja.setLayoutManager(layoutBidang);
                rcPekerja.setNestedScrollingEnabled(false);
                rcPekerja.setAdapter(adapterBidang);

                rlPekerja.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                textNotFound.setVisibility(View.GONE);
            }
            else {
                rlPekerja.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                textNotFound.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

        ItemClickSupport.addTo(rcPekerja)
                .setOnItemClickListener(this);

        return view;
    }
    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        switch (new SharedPrefUtil(getContext()).getFavorit("favorit").get(position).getTypeFav()){
            case 1:
                pekerjaDetail.idPekerja = new SharedPrefUtil(getContext()).getFavorit("favorit").get(position).getId();
                Intent pekerja = new Intent(getContext(), pekerjaDetail.class);
                startActivity(pekerja);
                break;
            case 2:
                layananDetail.idLayanan = new SharedPrefUtil(getContext()).getFavorit("favorit").get(position).getId();
                Intent layanan = new Intent(getContext(), layananDetail.class);
                startActivity(layanan);
                break;
        }
    }
}
