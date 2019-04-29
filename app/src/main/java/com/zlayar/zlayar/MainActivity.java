package com.zlayar.zlayar;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.zlayar.zlayar.FragmentMain.FragmentPesan;
import com.zlayar.zlayar.FragmentMain.FragmentPengaturan;
import com.zlayar.zlayar.FragmentMain.FragmentCariJasa;
import com.zlayar.zlayar.adapter.CustomViewPager;
import com.zlayar.zlayar.adapter.ViewPagerAdapter;
import com.zlayar.zlayar.data.dataFavorit;
import com.zlayar.zlayar.identityFirebase.Constants;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayoutUtama;
    private CustomViewPager viewPagerUtama;
    private String user;
    public static String text = "";
    private ViewPagerAdapter adapter2;
    private boolean exit = false;
    public static List<dataFavorit> favoritJasa = new ArrayList<dataFavorit>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayoutUtama = (TabLayout) findViewById(R.id.tabCari);
        viewPagerUtama = (CustomViewPager) findViewById(R.id.view_Cari);


        user = new SharedPrefUtil(MainActivity.this).getString("type", null);

        adapter2 = new ViewPagerAdapter(getSupportFragmentManager());

        if (new SharedPrefUtil(this).getFavorit("favorit") == null){

        }
        else {
            favoritJasa = new SharedPrefUtil(this).getFavorit("favorit");
            favoritJasa = new ArrayList<dataFavorit>();
        }

        try {
            InputStream is = getAssets().open("terms.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException ex){
            ex.printStackTrace();
        }

        if (user == null){
            typeUser();
        }
        else if (user.equals("pekerja")){
            typePekerja();
        }
        else if (user.equals("user")){
            typeUser();
        }


    }

    private void typeUser(){
        adapter2.AddFragment(new FragmentCariJasa(), "Cari Jasa");
        adapter2.AddFragment(new FragmentPesan(), "Pesanan");
        adapter2.AddFragment(new FragmentPengaturan(), "Pengaturan");

        viewPagerUtama.setAdapter(adapter2);
        tabLayoutUtama.setupWithViewPager(viewPagerUtama);

        tabLayoutUtama.getTabAt(0).setIcon(R.drawable.ic_search_blue);
        tabLayoutUtama.getTabAt(1).setIcon(R.drawable.ic_work_gray);
        tabLayoutUtama.getTabAt(2).setIcon(R.drawable.ic_person_gray);

        tabLayoutUtama.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.ic_search_blue);
                        tabLayoutUtama.getTabAt(1).setIcon(R.drawable.ic_work_gray);
                        tabLayoutUtama.getTabAt(2).setIcon(R.drawable.ic_person_gray);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_work_blue);
                        tabLayoutUtama.getTabAt(0).setIcon(R.drawable.ic_search);
                        tabLayoutUtama.getTabAt(2).setIcon(R.drawable.ic_person_gray);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_person_blue);
                        tabLayoutUtama.getTabAt(0).setIcon(R.drawable.ic_search);
                        tabLayoutUtama.getTabAt(1).setIcon(R.drawable.ic_work_gray);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void typePekerja(){
        adapter2.AddFragment(new FragmentPesan(), "Pesanan");
        adapter2.AddFragment(new FragmentPengaturan(), "Pengaturan");

        viewPagerUtama.setAdapter(adapter2);
        tabLayoutUtama.setupWithViewPager(viewPagerUtama);

        tabLayoutUtama.getTabAt(0).setIcon(R.drawable.ic_work_blue);
        tabLayoutUtama.getTabAt(1).setIcon(R.drawable.ic_person_gray);

        tabLayoutUtama.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.ic_work_blue);
                        tabLayoutUtama.getTabAt(1).setIcon(R.drawable.ic_person_gray);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_person_blue);
                        tabLayoutUtama.getTabAt(0).setIcon(R.drawable.ic_work_gray);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            minimizeApp();
            return;
        } else {
            Toast toast = Toast.makeText(MainActivity.this, "Tekan Cepat 2 Kali untuk Keluar", Toast.LENGTH_SHORT);
            toast.show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2000);
        }
    }

    public void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
