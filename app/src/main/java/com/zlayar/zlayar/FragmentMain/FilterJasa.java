package com.zlayar.zlayar.FragmentMain;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.dataBidang.BidangKategori;

import java.util.ArrayList;
import java.util.List;

public class FilterJasa extends AppCompatActivity {
    private Button cari;
    private EditText editJasa;
    private Spinner spinnerLokasi, spinnerKategori, spinnerSub;
    private List<String> listLokasi = new ArrayList<String>();
    public static List<BidangKategori> filterBidang = new ArrayList<BidangKategori>();
    private List<String> listKategori = new ArrayList<String>();
    private List<String> listSub = new ArrayList<String>();
    private ImageView back;
    private RelativeLayout rl_sub, rl_bid;
    public static int typeSearch, bidang, subBidang, typeFinish;
    public static Activity activityFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_jasa);

        cari = (Button) findViewById(R.id.btn_cari);
        editJasa = (EditText) findViewById(R.id.edit_cari);
        spinnerSub = (Spinner) findViewById(R.id.spinner_sub);
        spinnerLokasi = (Spinner) findViewById(R.id.spinner_lokasi);
        spinnerKategori = (Spinner) findViewById(R.id.spinner_kategori);
        back = (ImageView) findViewById(R.id.filterBack);
        rl_bid = (RelativeLayout) findViewById(R.id.rl_spinner_kategori);
        rl_sub = (RelativeLayout) findViewById(R.id.rl_spinner_sub);

        rl_bid.setVisibility(View.GONE);
        rl_sub.setVisibility(View.GONE);
        bidang = 0;
        setData();

        listLokasi.add("Makassar");
        listLokasi.add("Kab. Gowa");

        ArrayAdapter<String> dataLokasi = new ArrayAdapter<String>(this,
                R.layout.spinner_text, listLokasi);
        spinnerLokasi.setAdapter(dataLokasi);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!filterBidang.isEmpty()) {
                    filterBidang.removeAll(filterBidang);
                }
                Intent intent = new Intent(FilterJasa.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchJasa();
            }
        });
    }

    private void setData() {
        for (int a = 0; a < filterBidang.size(); a++) {
            listKategori.add(filterBidang.get(a).getNama().toString());
        }

        ArrayAdapter<String> dataKategori = new ArrayAdapter<String>(this,
                R.layout.spinner_text, listKategori);
        spinnerKategori.setAdapter(dataKategori);
        rl_bid.setVisibility(View.VISIBLE);

        spinnerKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int positionPekerja, long id) {
                bidang = filterBidang.get(positionPekerja).getIdBidang();

                if (filterBidang.get(positionPekerja).getSubBidang() == null) {
                    if (listSub.isEmpty()) {
                        listSub.removeAll(listSub);
                        rl_sub.setVisibility(View.GONE);
                    } else {
                        listSub.removeAll(listSub);
                        rl_sub.setVisibility(View.GONE);
                    }
                } else {
                    listSub.removeAll(listSub);
                    for (int b = 0; b < filterBidang.get(positionPekerja).getSubBidang().size(); b++) {
                        listSub.add(filterBidang.get(positionPekerja).getSubBidang().get(b).getNama().toString());
                    }
                }

                if (listSub.isEmpty()) {
                    rl_sub.setVisibility(View.GONE);
                    subBidang = 0;
                } else {

                    ArrayAdapter<String> dataSub = new ArrayAdapter<String>(FilterJasa.this,
                            R.layout.spinner_text, listSub);
                    spinnerSub.setAdapter(dataSub);

                    spinnerSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            subBidang = filterBidang.get(positionPekerja).getSubBidang().get(position).getIdSubBidang();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    rl_sub.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void onSearchJasa() {
        if (typeFinish == 2){
            activityFilter.finish();
        }
        HasilFilter.text = editJasa.getText().toString();
        HasilFilter.bidang = bidang;
        HasilFilter.subBidang = subBidang;
        HasilFilter.lokasi = spinnerLokasi.toString();
        HasilFilter.type = typeSearch;
        Intent intent = new Intent(FilterJasa.this, HasilFilter.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (!filterBidang.isEmpty()) {
            filterBidang.removeAll(filterBidang);
        }
        switch (typeFinish){
            case 1:
                Intent intent = new Intent(FilterJasa.this, MainActivity.class);
                startActivity(intent);
                break;
            case 2:
                break;
            case 3:
                break;
        }
        finish();
    }
}
