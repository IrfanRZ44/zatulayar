package com.zlayar.zlayar.FragmentPerson;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zlayar.zlayar.R;
import com.zlayar.zlayar.detailJasa.layananDetail;
import com.zlayar.zlayar.detailJasa.pekerjaDetail;
import com.zlayar.zlayar.identityFirebase.ItemClickSupport;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;
import com.zlayar.zlayar.listView.ListLayanan;
import com.zlayar.zlayar.recyclerView.detailLayanan;
import com.zlayar.zlayar.recyclerView.favoritPekerja;

public class fragmentLayanan extends Fragment implements ItemClickSupport.OnItemClickListener {

    View view;
    public fragmentLayanan(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_layanan, container, false);
        RecyclerView rcLayanan = (RecyclerView) view.findViewById(R.id.rcLayanan);

        detailLayanan adapterBidang = new detailLayanan(pekerjaDetail.detailPekerja.getLayanan(), getContext());
        RecyclerView.LayoutManager layoutBidang = new LinearLayoutManager(getContext());
        rcLayanan.setLayoutManager(layoutBidang);
        rcLayanan.setNestedScrollingEnabled(true);
        rcLayanan.setAdapter(adapterBidang);

        ItemClickSupport.addTo(rcLayanan)
                .setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        layananDetail.idLayanan = pekerjaDetail.detailPekerja.getLayanan().get(position).getIdLayanan();
        Intent intent = new Intent(getContext(), layananDetail.class);
        startActivity(intent);
        getActivity().finish();
    }
}
