package com.zlayar.zlayar.FragmentPerson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zlayar.zlayar.R;
import com.zlayar.zlayar.detailJasa.pekerjaDetail;
import com.zlayar.zlayar.recyclerView.detailLayanan;
import com.zlayar.zlayar.recyclerView.detailPortofolio;

/**
 * Created by IrfanRZ on 8/22/2018.
 */

public class fragmentPortofolio extends Fragment {
    View view;
    public fragmentPortofolio(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_potofolio, container, false);
        RecyclerView rcPortofolio = (RecyclerView) view.findViewById(R.id.rc_portofolio);

        detailPortofolio adapterBidang = new detailPortofolio(pekerjaDetail.detailPekerja.getPortofolio(), getContext());
        RecyclerView.LayoutManager layoutBidang = new LinearLayoutManager(getContext());
        rcPortofolio.setLayoutManager(layoutBidang);
        rcPortofolio.setNestedScrollingEnabled(true);
        rcPortofolio.setAdapter(adapterBidang);

        return view;
    }
}
