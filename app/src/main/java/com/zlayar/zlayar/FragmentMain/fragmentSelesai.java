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

/**
 * Created by IrfanRZ on 6/2/2018.
 */

public class fragmentSelesai extends Fragment {
    private TabLayout tabPesanan;
    private ViewPager viewPesanan;
    private View view;

    public fragmentSelesai(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_selesai, container, false);
        return view;
    }
}
