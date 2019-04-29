package com.zlayar.zlayar.FragmentMain;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zlayar.zlayar.FragmentCari.fragmentLayanan;
import com.zlayar.zlayar.FragmentCari.fragmentPekerja;
import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.adapter.ViewPagerAdapter;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;

public class HasilFilter extends AppCompatActivity {
    //    private TextView textNothingFound;
    private ImageView backKt;
    private TabLayout tabKategori;
    private ViewPager viewKategori;
    public static int kategoriType;
    public static int type, idUser, bidang, subBidang;
    public static String lokasi, text;
    private RelativeLayout rl_pencarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kategori);

        backKt = (ImageView) findViewById(R.id.ktBack);
        tabKategori = (TabLayout) findViewById(R.id.tab_Kategori);
        viewKategori = (ViewPager) findViewById(R.id.view_Kategori);
        rl_pencarian = (RelativeLayout) findViewById(R.id.kategori_pencarian);

        ViewPagerAdapter adapterKategori = new ViewPagerAdapter(getSupportFragmentManager());

        adapterKategori.AddFragment(new fragmentPekerja(), "Pekerja");
        adapterKategori.AddFragment(new fragmentLayanan(), "Layanan");

        viewKategori.setAdapter(adapterKategori);
        tabKategori.setupWithViewPager(viewKategori);

        tabKategori.getTabAt(0).setText("PEKERJA");
        tabKategori.getTabAt(1).setText("LAYANAN");

        if (kategoriType == 1) {
            tabKategori.getTabAt(0).select();
            type = 1;
            kategoriType = 0;
        } else if (kategoriType == 2) {
            tabKategori.getTabAt(1).select();
            type = 1;
            kategoriType = 0;
        }

        rl_pencarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterJasa.typeSearch = type;
                FilterJasa.filterBidang = new SharedPrefUtil(HasilFilter.this).getObjek("bidang");
                FilterJasa.typeFinish = 2;
                Intent intent = new Intent(HasilFilter.this, FilterJasa.class);
                startActivity(intent);
                FilterJasa.activityFilter = HasilFilter.this;
//                finish();
            }
        });

        backKt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentLayanan.hasilLayanan != null){
                    fragmentLayanan.hasilLayanan.removeAll(fragmentLayanan.hasilLayanan);
                }
                if (fragmentPekerja.hasilPekerja != null){
                    fragmentPekerja.hasilPekerja.removeAll(fragmentPekerja.hasilPekerja);
                }
                kategoriType = 0;
                type = 0;
                idUser = 0;
                bidang = 0;
                lokasi = "";
                text = "";
                subBidang = 0;
                Intent intent = new Intent(HasilFilter.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fragmentLayanan.hasilLayanan != null){
            fragmentLayanan.hasilLayanan.removeAll(fragmentLayanan.hasilLayanan);
        }
        if (fragmentPekerja.hasilPekerja != null){
            fragmentPekerja.hasilPekerja.removeAll(fragmentPekerja.hasilPekerja);
        }
        kategoriType = 0;
        Intent intent = new Intent(HasilFilter.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
