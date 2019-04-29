package com.zlayar.zlayar.FragmentMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlayar.zlayar.R;
import com.zlayar.zlayar.adapter.ViewPagerAdapter;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;

/**
 * Created by IrfanRZ on 6/2/2018.
 */

public class FragmentPesan extends Fragment {
    private TabLayout tabPesanan;
    private ViewPager viewPesanan;
    private View view;
    private ViewPagerAdapter adapterPesan;
    private String type;

    public FragmentPesan(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pesan, container, false);
        tabPesanan = (TabLayout) view.findViewById(R.id.tab_Pesan);
        viewPesanan = (ViewPager)view.findViewById(R.id.view_Pesan);

        adapterPesan = new ViewPagerAdapter(getFragmentManager());

        type = new SharedPrefUtil(getContext()).getString("type", null);
        if (type == null){
            user();
        }
        else if (type.equals("pekerja")){
            pekerja();
        }
        else if (type.equals("user")){
            user();
        }

        return view;
    }

    private void pekerja(){
        adapterPesan.AddFragment(new fragmentPesanan(), "Pesan");

        viewPesanan.setAdapter(adapterPesan);
        tabPesanan.setupWithViewPager(viewPesanan);

        tabPesanan.getTabAt(0).setText("PESAN");
    }
    private void user(){
        adapterPesan.AddFragment(new fragmentFavorit(), "Favorit");
        adapterPesan.AddFragment(new fragmentPesanan(), "Pesan");

        viewPesanan.setAdapter(adapterPesan);
        tabPesanan.setupWithViewPager(viewPesanan);

        tabPesanan.getTabAt(0).setText("FAVORIT");
        tabPesanan.getTabAt(1).setText("PESAN");
    }
}
