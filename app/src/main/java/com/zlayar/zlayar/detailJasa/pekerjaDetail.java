package com.zlayar.zlayar.detailJasa;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.zlayar.zlayar.AuthenticationUser.login.loginUser;
import com.zlayar.zlayar.ChatActivity;
import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.data.dataFavorit;
import com.zlayar.zlayar.data.getDataPekerja;
import com.zlayar.zlayar.dataPekerja.DataPekerja;
import com.zlayar.zlayar.dataPekerja.Lokasi;
import com.zlayar.zlayar.dataPekerja.SubBidang;
import com.zlayar.zlayar.recyclerView.bidangPekerjaan;
import com.zlayar.zlayar.chat.GetUsersContract;
import com.zlayar.zlayar.chat.GetUsersPresenter;
import com.zlayar.zlayar.FragmentPerson.fragmentPortofolio;
import com.zlayar.zlayar.FragmentPerson.fragmentLayanan;
import com.zlayar.zlayar.adapter.ViewPagerAdapter;
import com.zlayar.zlayar.data.User;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;
import com.zlayar.zlayar.recyclerView.lokasiPekerjaan;

import java.util.ArrayList;
import java.util.List;

public class pekerjaDetail extends AppCompatActivity implements GetUsersContract.View {
    public static int idPekerja;
    public static DataPekerja detailPekerja;
    public static String emailContact, uidContact, firebaseContact, nameContact;
    private TextView namePerson, skillPerson, contactPerson, descriptionPerson, textTry;
    private ImageView personPic, personBack;
    private TabLayout tabLayoutUtama;
    private ViewPager viewPagerUtama;
    private Button personChat, personFav;
    private GetUsersPresenter mGetUsersPresenter;
    private int favoritPerson = 0;
    private RecyclerView list_Bidang, list_Lokasi;
    private ArrayList<SubBidang> hasilBidang = null;
    private ArrayList<Lokasi> hasilLokasi = null;
    private ProgressDialog progressDialog = null;
    private ProgressBar progressBar;
    private int mProgressStatus = 0;
    private Handler handler = new Handler();
    private RelativeLayout hasilDetail;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pekerja_detail);

        namePerson = (TextView) findViewById(R.id.personName);
        skillPerson = (TextView) findViewById(R.id.skillPerson);
        contactPerson = (TextView) findViewById(R.id.contactPerson);
        tabLayoutUtama = (TabLayout) findViewById(R.id.tabLayout_Person);
        viewPagerUtama = (ViewPager) findViewById(R.id.viewpager_Person);
        personPic = (ImageView) findViewById(R.id.personPic);
        personBack = (ImageView) findViewById(R.id.ktBack);
        personChat = (Button) findViewById(R.id.personChat);
        personFav = (Button) findViewById(R.id.personFav);
        mGetUsersPresenter = new GetUsersPresenter(this);
        list_Bidang = (RecyclerView) findViewById(R.id.listBidang);
        list_Lokasi = (RecyclerView) findViewById(R.id.listLokasi);
        descriptionPerson = (TextView) findViewById(R.id.dataDescription);
        progressBar = (ProgressBar) findViewById(R.id.progressDetail);
        hasilDetail = (RelativeLayout) findViewById(R.id.hasilDetail);
        textTry = (TextView) findViewById(R.id.textTryAgain);

        progressBar.setVisibility(View.VISIBLE);
        hasilDetail.setVisibility(View.GONE);
        textTry.setVisibility(View.GONE);

        if (detailPekerja == null) {
            progres_Data();
        } else {
            setData();
        }

        //Chat Button
        personChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progressDialog = new ProgressDialog(pekerjaDetail.this);
                progressDialog.setMessage("Mohon Tunggu...");
                progressDialog.setTitle("Proses");
                progressDialog.setCancelable(false);
                progressDialog.show();
                //button cancel in progress dialog
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                chatting();
            }
        });
        //Back Button
        personBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        //Favorit Button
        personFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoritPerson();
            }
        });

        //Set the tablayout
        ViewPagerAdapter adapter2 = new ViewPagerAdapter(getSupportFragmentManager());
        adapter2.AddFragment(new fragmentLayanan(), "Penawaran");
        adapter2.AddFragment(new fragmentPortofolio(), "Portofolio");

        viewPagerUtama.setAdapter(adapter2);
        tabLayoutUtama.setupWithViewPager(viewPagerUtama);
        tabLayoutUtama.getTabAt(0).setText("Penawaran");
        tabLayoutUtama.getTabAt(1).setText("Portofolio");

    }

    private void getFavorit() {
        try {
            if (MainActivity.favoritJasa != null) {
                for (int a = 0; a < MainActivity.favoritJasa.size(); a++) {
                    if ((MainActivity.favoritJasa.get(a).getId() == idPekerja) && (MainActivity.favoritJasa.get(a).getTypeFav() == 1)){
                        personFav.setBackgroundResource(R.drawable.ic_star_fav);
                        favoritPerson = 1;
                    }
                }
                ;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Terjadi Kesalahan yang tidak diketahui", Toast.LENGTH_SHORT).show();
        }
    }

    private void favoritPerson() {
        if (favoritPerson == 0) {
            favoritPerson = 1;
            personFav.setBackgroundResource(R.drawable.ic_star_fav);

            try {
                MainActivity.favoritJasa.add(new dataFavorit(detailPekerja.getName().toString()
                        , detailPekerja.getDeskripsi().toString(), detailPekerja.getBidang().getNama().toString()
                        , detailPekerja.getIdUser(), 1, detailPekerja.getGambarKecil().getUrl().toString()
                ));
            } catch (Exception e) {
                Toast.makeText(this, "Terjadi Kesalahan yang tidak Diketahui", Toast.LENGTH_SHORT).show();
            }

            new SharedPrefUtil(pekerjaDetail.this).saveFavorit("favorit", MainActivity.favoritJasa);
        } else if (favoritPerson == 1) {
            favoritPerson = 0;
            personFav.setBackgroundResource(R.drawable.ic_star_white_unfav);
            for (int a = 0; a < MainActivity.favoritJasa.size(); a++) {
                if ((MainActivity.favoritJasa.get(a).getId() == idPekerja) && (MainActivity.favoritJasa.get(a).getTypeFav() == 1)){
                    MainActivity.favoritJasa.remove(a);
                    new SharedPrefUtil(pekerjaDetail.this).saveFavorit("favorit", MainActivity.favoritJasa);
                }
            }
        }
    }

    private void chatting() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // if logged in redirect the User to User listing activity
            mGetUsersPresenter.getAllUsers();
        } else {
            // otherwise redirect the User to login activity
            Intent intent = new Intent(pekerjaDetail.this, loginUser.class);
            loginUser.activity = 2;
            startActivity(intent);
            finish();
        }
    }

    //get All user from firebase
    @Override
    public void onGetAllUsersSuccess(ArrayList<User> users) {
        int user = 0;
        for (int a = 0; a < users.size(); a++) {
            if (users.get(a).email.toString().equals(emailContact)) {
                emailContact = users.get(a).email;
                uidContact = users.get(a).uid;
                firebaseContact = users.get(a).firebaseToken;
                ChatActivity.startActivity(pekerjaDetail.this,
                        emailContact,
                        uidContact,
                        firebaseContact);
                user = 1;
            }
        }
        if (user == 0){
//            Toast.makeText(this, "We're Sorry, this user is not valid !", Toast.LENGTH_SHORT).show();
            Snackbar.make(coordinatorLayout, "We're Sorry, this user is not valid", Snackbar.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
    }

    @Override
    public void onGetAllUsersFailure(String message) {
        Toast.makeText(this, "message : " + message, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onGetChatUsersSuccess(ArrayList<User> users) {
        progressDialog.dismiss();
    }

    @Override
    public void onGetChatUsersFailure(String message) {
        Toast.makeText(this, "message : " + message, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
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
                        if (detailPekerja == null) {
                            textTry.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            hasilDetail.setVisibility(View.GONE);
                        } else {
                            textTry.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            hasilDetail.setVisibility(View.VISIBLE);
                            setData();
                        }
                    }
                });
            }
        }).start();
    }

    public void getData() {
        getDataPekerja process = new getDataPekerja(idPekerja, pekerjaDetail.this, 1);
        process.execute();
    }

    private void setData() {
        namePerson.setText(detailPekerja.getName().toString());
        skillPerson.setText(detailPekerja.getBidang().getNama().toString());
        contactPerson.setText(detailPekerja.getHp().toString());
        emailContact = detailPekerja.getEmail().toString();
        nameContact = namePerson.getText().toString();

        progressBar.setVisibility(View.GONE);
        hasilDetail.setVisibility(View.VISIBLE);

        getFavorit();

//        personPic.setImageResource(android.R.color.transparent);

        if (detailPekerja.getGambarKecil().getUrl().toString().isEmpty()) {
            personPic.setImageResource(R.drawable.person);
        } else {
            Uri personUri = Uri.parse(detailPekerja.getGambarKecil().getUrl().toString());
            Picasso.with(pekerjaDetail.this).load(personUri).fit().into(personPic);

            if (personPic.getDrawable() == null) {
                personPic.setImageResource(R.drawable.person);
            }
        }

        hasilBidang = detailPekerja.getSubBidang();
        hasilLokasi = detailPekerja.getLokasi();

//        Toast.makeText(this, detailPekerja.getLokasi().size(), Toast.LENGTH_SHORT).show();

        //set the adapterBidang of bidang pekerjaan
        bidangPekerjaan adapterBidang = new bidangPekerjaan(hasilBidang);
        RecyclerView.LayoutManager layoutBidang = new LinearLayoutManager(pekerjaDetail.this);
        list_Bidang.setLayoutManager(layoutBidang);
        list_Bidang.setNestedScrollingEnabled(false);
        list_Bidang.setAdapter(adapterBidang);

        //set the adapterLokasi of lokasi pekerjaan
        lokasiPekerjaan adapterLokasi = new lokasiPekerjaan(hasilLokasi);
        RecyclerView.LayoutManager layoutLokasi = new LinearLayoutManager(pekerjaDetail.this);
        list_Lokasi.setLayoutManager(layoutLokasi);
        list_Lokasi.setNestedScrollingEnabled(false);
        list_Lokasi.setAdapter(adapterLokasi);

        descriptionPerson.setText(detailPekerja.getDeskripsi().toString());
    }

    private void back() {
        if (detailPekerja != null){
            detailPekerja = null;
        }
        if (hasilBidang != null){
            hasilBidang.removeAll(hasilBidang);
        }
        if (hasilLokasi != null){
            hasilLokasi.removeAll(hasilLokasi);
        }
        namePerson.setText("");
        skillPerson.setText("");
        contactPerson.setText("");
        finish();
    }

    @Override
    public void onBackPressed() {
        namePerson.setText("");
        skillPerson.setText("");
        contactPerson.setText("");
        finish();
    }
}
