package com.zlayar.zlayar.FragmentCari;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zlayar.zlayar.FragmentMain.HasilFilter;
import com.zlayar.zlayar.cariLayanan.CariLayanan;
import com.zlayar.zlayar.cariLayanan.getJSONLayanan;
import com.zlayar.zlayar.data.getDataLayanan;
import com.zlayar.zlayar.detailJasa.layananDetail;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.listView.ListLayanan;

import java.util.ArrayList;

/**
 * Created by IrfanRZ on 21/09/2018.
 */

public class fragmentLayanan extends Fragment {
    private View view;
    private ListView listView;
    private TextView textView;
    private ProgressBar progressBar;
    private int mProgressStatus = 0;
    private Handler handler = new Handler();
    private ArrayList<CariLayanan> dataList;
    public static ArrayList<CariLayanan> hasilLayanan = new ArrayList<CariLayanan>();

    public fragmentLayanan(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pekerja, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        listView = (ListView) view.findViewById(R.id.list_kategori);
        textView = (TextView) view.findViewById(R.id.textNothingFound);

        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);

//        if (hasilLayanan.isEmpty()){
            progressLayanan();
//        }
//        else {
//            setData();
//        }

        return view;
    }

    private void progressLayanan() {
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
        getJSONLayanan process = new getJSONLayanan(HasilFilter.text, HasilFilter.bidang, HasilFilter.subBidang, HasilFilter.lokasi, HasilFilter.idUser, HasilFilter.type, getContext());
        process.execute();
    }

    private void setData() {
        dataList = hasilLayanan;
        if (hasilLayanan.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            ListLayanan adapter = new ListLayanan(getContext(), dataList);
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            listClick();
        }
    }

    private void listClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                layananDetail.idLayanan = hasilLayanan.get(position).getIdLayanan();

                Intent intent = new Intent(getContext(), layananDetail.class);
                startActivity(intent);
            }
        });
    }

}
