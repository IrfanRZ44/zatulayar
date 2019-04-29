package com.zlayar.zlayar.detailJasa;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.cariLayanan.CariLayanan;
import com.zlayar.zlayar.data.dataFavorit;
import com.zlayar.zlayar.data.getDataLayanan;
import com.zlayar.zlayar.data.getDataPekerja;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class layananDetail extends AppCompatActivity {
    public static int idLayanan;
    public static CariLayanan detailLayanan = null;
    private ImageView imageLayanan, imageUser, back;
    private TextView title, teksLayanan, teksNotFound;
    private ProgressBar progressBar;
    private int mProgressStatus = 0;
    private Handler handler = new Handler();
    private RelativeLayout relativeLayout;
    private Button fav;
    private int favoritPerson = 0;
    private CircleImageView personDetail;
    public static String urlUser;

//    private ArrayList<CariLayanan> layananFavorit = new ArrayList<CariLayanan>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan_detail);

        back = (ImageView) findViewById(R.id.layananBack);
        imageLayanan = (ImageView) findViewById(R.id.layananImage);
        imageUser = (ImageView) findViewById(R.id.userLayanan);
        title = (TextView) findViewById(R.id.Title);
        teksLayanan = (TextView) findViewById(R.id.Layanan);
        progressBar = (ProgressBar) findViewById(R.id.progressDetail);
        teksNotFound = (TextView) findViewById(R.id.textTryAgain);
        relativeLayout = (RelativeLayout) findViewById(R.id.custoomPic);
        fav = (Button) findViewById(R.id.layananFav);
        personDetail = (CircleImageView) findViewById(R.id.userLayanan);

        progressBar.setVisibility(View.VISIBLE);
        teksNotFound.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.GONE);

        if (detailLayanan == null){
            progres_Data();
        }
        else {
            setData();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                detailLayanan = null;
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoritPerson();
            }
        });

        personDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pekerjaDetail.idPekerja = detailLayanan.getIdUser();
                Intent intent = new Intent(layananDetail.this, pekerjaDetail.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getFavorit() {
        try {
            if (MainActivity.favoritJasa != null) {
                for (int a = 0; a < MainActivity.favoritJasa.size(); a++) {
                    if ((MainActivity.favoritJasa.get(a).getId() == idLayanan) && (MainActivity.favoritJasa.get(a).getTypeFav() == 2) ){
                        fav.setBackgroundResource(R.drawable.ic_star_fav);
                        favoritPerson = 1;
                    }
                }
            }
        }catch (Exception e){
            Toast.makeText(this, "Terjadi Kesalahan yang tidak Diketahui", Toast.LENGTH_SHORT).show();
        }
    }

    private void favoritPerson() {
        if (favoritPerson == 0) {
            favoritPerson = 1;
            fav.setBackgroundResource(R.drawable.ic_star_fav);

            try {
                MainActivity.favoritJasa.add(new dataFavorit(detailLayanan.getNama().toString()
                        , detailLayanan.getTeks().toString(), detailLayanan.getBidang().toString()
                        , detailLayanan.getIdLayanan(), 2, detailLayanan.getGambarSedang().getUrl().toString()
                ));
            }
            catch (Exception e){
                Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            }
            new SharedPrefUtil(layananDetail.this).saveFavorit("favorit", MainActivity.favoritJasa);
        } else if (favoritPerson == 1) {
            favoritPerson = 0;
            fav.setBackgroundResource(R.drawable.ic_star_white_unfav);
            for (int a = 0; a < MainActivity.favoritJasa.size(); a++) {
                if ((MainActivity.favoritJasa.get(a).getId() == idLayanan) && (MainActivity.favoritJasa.get(a).getTypeFav() == 2) ){
                    MainActivity.favoritJasa.remove(a);
                    new SharedPrefUtil(layananDetail.this).saveFavorit("favorit", MainActivity.favoritJasa);
                }
            }
        }
    }

    public void progres_Data() {
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
                        if (detailLayanan == null) {
                            progressBar.setVisibility(View.GONE);
                            teksNotFound.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.GONE);
                        } else {
                            setData();
                        }
                    }
                });
            }
        }).start();
    }

    private void getData() {
        getDataLayanan getLayanan = new getDataLayanan(idLayanan, layananDetail.this);
        getLayanan.execute();
    }

    private void setData() {
        progressBar.setVisibility(View.GONE);
        teksNotFound.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);

        getFavorit();

        if (detailLayanan.getGambarSedang().getUrl().toString().isEmpty()){
            imageLayanan.setImageResource(R.drawable.person);
        }
        else {
            Uri personUri = Uri.parse(detailLayanan.getGambarSedang().getUrl().toString());
            Picasso.with(layananDetail.this).load(personUri).fit().into(imageLayanan);

            if (imageLayanan.getDrawable() == null){
                imageLayanan.setImageResource(R.drawable.person);
            }
        }
        progres_Foto();
        title.setText(detailLayanan.getNama().toString());
        teksLayanan.setText(detailLayanan.getTeks().toString());
    }
    @Override
    public void onBackPressed() {
        detailLayanan = null;
        finish();
    }

    public void progres_Foto() {
        getFotoUser();
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
                        if (detailLayanan == null) {
                            progressBar.setVisibility(View.GONE);
                            teksNotFound.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.GONE);
                        } else {
                            setFotoUser();
                        }
                    }
                });
            }
        }).start();
    }

    private void getFotoUser(){
        getDataPekerja process = new getDataPekerja(detailLayanan.getIdUser(), layananDetail.this, 2);
        process.execute();
    }

    private void setFotoUser(){
        if (urlUser == null){
            personDetail.setImageResource(R.drawable.person);
            getFotoUser();
        }
        else {
            Uri personUri = Uri.parse(urlUser);
            Picasso.with(layananDetail.this).load(personUri).fit().into(personDetail);

            if (personDetail.getDrawable() == null){
                personDetail.setImageResource(R.drawable.person);
            }
        }
    }
}
