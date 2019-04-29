package com.zlayar.zlayar.FragmentMain;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zlayar.zlayar.R;
import com.zlayar.zlayar.adapter.SwipeAdapter;
import com.zlayar.zlayar.dataBidang.BidangKategori;
import com.zlayar.zlayar.dataBidang.getBidang;
import com.zlayar.zlayar.identityFirebase.ItemClickSupport;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;
import com.zlayar.zlayar.recyclerView.RecyclerBidang;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by IrfanRZ on 6/2/2018.
 */

public class FragmentCariJasa extends Fragment implements ItemClickSupport.OnItemClickListener {
    private View view;
    private SwipeAdapter adapter;
    private ViewPager viewPager;
    private int mProgressStatus = 0;
    Handler h = new Handler();
    private Runnable runnable;
    private static int currentPage = 0;
    private RelativeLayout searchJasa, pekerja, layanan;
    public static ArrayList<BidangKategori> hasilBidang = new ArrayList<BidangKategori>();
    private Handler handler = new Handler();
    private RecyclerView recyclerKategori;
    private TextView textKategoriPekerjaan;

    public FragmentCariJasa() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cari_jasa, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.view_Pager);
        searchJasa = (RelativeLayout) view.findViewById(R.id.rl_pencarian);
        pekerja = (RelativeLayout) view.findViewById(R.id.kategoriPekerja);
        layanan = (RelativeLayout) view.findViewById(R.id.kategoriLayanan);
        recyclerKategori = (RecyclerView) view.findViewById(R.id.recyclerKategori);
        textKategoriPekerjaan = (TextView) view.findViewById(R.id.Textpilih);

        adapter = new SwipeAdapter(view.getContext());
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);

        final CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        try {
            if (new SharedPrefUtil(getContext()).getObjek("bidang") != null){
            hasilBidang = new SharedPrefUtil(getContext()).getObjek("bidang");
            setData();
            }
            else {
            progressBidang();
            textKategoriPekerjaan.setVisibility(View.GONE);
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "saved bidang " + e.toString(), Toast.LENGTH_SHORT).show();
        }

        searchJasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilterSearch(3);
            }
        });

        pekerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilterBidang(1);
            }
        });

        layanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilterBidang(2);
            }
        });

        viewPager.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }


            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {

                }


            }

        });

        return view;
    }

    private void setRecyclerKategori() {
        textKategoriPekerjaan.setVisibility(View.VISIBLE);
        RecyclerBidang adapterAgenda = new RecyclerBidang(hasilBidang);
        RecyclerView.LayoutManager layoutAgenda = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerKategori.setLayoutManager(layoutAgenda);
        recyclerKategori.setNestedScrollingEnabled(false);
        recyclerKategori.setAdapter(adapterAgenda);

        ItemClickSupport.addTo(recyclerKategori)
                .setOnItemClickListener(this);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        FilterBidang.dataFilterBidang = hasilBidang.get(position).getSubBidang();
        FilterBidang.posisitonBidang = position;
        Intent intent = new Intent(getContext(), FilterBidang.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onStart() {
        h.postDelayed(new Runnable() {
                          public void run() {
                              if (currentPage == adapter.getCount()) {
                                  currentPage = 0;
                              } else {
                                  currentPage++;
                              }

                              viewPager.setCurrentItem(currentPage);

                              runnable = this;

                              h.postDelayed(runnable, 5000);
                          }
                      }
                , 5000);

        super.onStart();
    }

    private void onFilterSearch(int typeSearch) {
        FilterJasa.typeSearch = typeSearch;
        FilterJasa.filterBidang = hasilBidang;
        FilterJasa.typeFinish = 1;
        Intent intent = new Intent(getContext(), FilterJasa.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void onFilterBidang(int kategori) {
        switch (kategori) {
            case 1:
                HasilFilter.kategoriType = kategori;
                break;
            case 2:
                HasilFilter.kategoriType = kategori;
                break;
        }
        Intent intent = new Intent(getContext(), HasilFilter.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void progressBidang() {
        getData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mProgressStatus = 0;
                while (mProgressStatus < 100) {
                    mProgressStatus++;
                    SystemClock.sleep(30);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setData();
                    }
                });
            }
        }).start();
    }

    private void getData() {
        getBidang process = new getBidang(getContext());
        process.execute();
    }

    private void setData() {
        if (hasilBidang.isEmpty()) {
//            progressBidang();
            textKategoriPekerjaan.setVisibility(View.GONE);
        } else {
            new SharedPrefUtil(getContext()).saveObjek("bidang", hasilBidang);
            setRecyclerKategori();
        }
    }

}