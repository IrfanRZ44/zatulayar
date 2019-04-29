package com.zlayar.zlayar.FragmentMain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.dataBidang.BidangKategori;
import com.zlayar.zlayar.dataBidang.SubBidang;
import com.zlayar.zlayar.listView.ListSubBidang;

import java.util.ArrayList;

public class FilterBidang extends AppCompatActivity {
    public static ArrayList<SubBidang> dataFilterBidang = new ArrayList<SubBidang>();
    public static int posisitonBidang;
    private ProgressBar progressBar;
    private RelativeLayout rlBidang;
    private TextView textView;
    private ImageView back;
    private ListView listSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_bidang);

        progressBar = (ProgressBar) findViewById(R.id.progressDetail);
        rlBidang = (RelativeLayout) findViewById(R.id.filter_bidang);
        textView = (TextView) findViewById(R.id.textTryAgain);
        back = (ImageView) findViewById(R.id.ktBack);
        listSub = (ListView) findViewById(R.id.listSubBidang);

        setBidang();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterBidang.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void setBidang() {
        if (FragmentCariJasa.hasilBidang.get(posisitonBidang).getSubBidang() == null) {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            rlBidang.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            rlBidang.setVisibility(View.VISIBLE);
            ListSubBidang adapter = new ListSubBidang(FilterBidang.this, dataFilterBidang);
            listSub.setAdapter(adapter);
            setOnClickList();
        }
    }

    private void setOnClickList(){
        listSub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HasilFilter.type = 2;
                HasilFilter.bidang = dataFilterBidang.get(position).getIdBidang();
                HasilFilter.subBidang = dataFilterBidang.get(position).getIdSubBidang();
                Intent intent = new Intent(FilterBidang.this, HasilFilter.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FilterBidang.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
