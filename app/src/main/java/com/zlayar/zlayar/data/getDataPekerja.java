package com.zlayar.zlayar.data;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.zlayar.zlayar.dataPekerja.Bidang;
import com.zlayar.zlayar.dataPekerja.DataPekerja;
import com.zlayar.zlayar.dataPekerja.GambarKecil;
import com.zlayar.zlayar.dataPekerja.Kota;
import com.zlayar.zlayar.dataPekerja.Layanan;
import com.zlayar.zlayar.dataPekerja.Lokasi;
import com.zlayar.zlayar.dataPekerja.Portofolio;
import com.zlayar.zlayar.dataPekerja.Provinsi;
import com.zlayar.zlayar.dataPekerja.SubBidang;
import com.zlayar.zlayar.dataPekerja.SubBidang_;
import com.zlayar.zlayar.detailJasa.layananDetail;
import com.zlayar.zlayar.detailJasa.pekerjaDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by IrfanRZ on 6/24/2018.
 */

public class getDataPekerja extends AsyncTask<Void, Void, Void> {
    private String kategoriData, data = "";
    private int idUser;
    private static ArrayList<Lokasi> lokasiData = new ArrayList<Lokasi>();
    private static Bidang bidangData = null;
    private static ArrayList<SubBidang> subBidangData = new ArrayList<SubBidang>();
    private static ArrayList<Portofolio> portofolioData = new ArrayList<Portofolio>();
    private static ArrayList<Layanan> layananData = new ArrayList<Layanan>();
    private GambarKecil gambarKecil;
    private Context view;
    private String error;
    private errorGetData errorData;
    private URL url;
    private int typeReq;

    public getDataPekerja(int idUser, Context view, int typeReq) {
        errorData = new errorGetData();
        this.kategoriData = kategoriData;
        this.idUser = idUser;
        this.view = view;
        this.typeReq = typeReq;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            url = new URL("https://zatulayar.com/api/pekerja/" + idUser);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String dataUser = "";
            while (dataUser != null) {
                dataUser = bufferedReader.readLine();
                data = data + dataUser;
            }

            JSONObject JO = new JSONObject(data);
            String id_user, hp, wa, id_bidang, is_active, id_bid, id_admin;
            GambarKecil gambarkcl = null;

            //get data Lokasi
            JSONArray lokasi = JO.getJSONArray("lokasi");
            for (int a = 0; a < lokasi.length(); a++) {
                lokasiData = new ArrayList<Lokasi>();
                bidangData = null;
                subBidangData = new ArrayList<SubBidang>();
                portofolioData = new ArrayList<Portofolio>();
                layananData = new ArrayList<Layanan>();
                String id_relasi_user_dan_lokasi, id_usr, id_provinsi, id_kota, is_active_lokasi;
                String id_kota_lokasi, id_provinsi_lokasi;
                String kt, prov;
                Kota kotaData = null;
                Provinsi provinsiData = null;

                JSONObject dataLokasi = (JSONObject) lokasi.get(a);

                JSONObject dataKota = (JSONObject) dataLokasi.get("kota");
                JSONObject dataProv = (JSONObject) dataLokasi.get("provinsi");

                id_kota_lokasi = dataKota.get("id_kota").toString();
                id_provinsi_lokasi = dataKota.get("id_provinsi").toString();

                kotaData = new Kota(Integer.parseInt(id_kota_lokasi), Integer.parseInt(id_provinsi_lokasi)
                        , dataKota.get("kota").toString(), dataKota.get("tgl_en").toString()
                        , dataKota.get("tgl_ed").toString());

                kt = dataProv.get("id_kota").toString();
                prov = dataProv.get("id_provinsi").toString();

                provinsiData = new Provinsi(Integer.parseInt(kt), Integer.parseInt(prov)
                        , dataProv.get("kota").toString(), dataProv.get("tgl_en").toString()
                        , dataProv.get("tgl_ed").toString());

                id_relasi_user_dan_lokasi = dataLokasi.get("id_relasi_user_dan_lokasi").toString();
                id_usr = dataLokasi.get("id_user").toString();
                id_provinsi = dataLokasi.get("id_provinsi").toString();
                id_kota = dataLokasi.get("id_kota").toString();
                is_active_lokasi = dataLokasi.get("is_active").toString();

                lokasiData.add(new Lokasi(Integer.parseInt(id_relasi_user_dan_lokasi), Integer.parseInt(id_usr)
                        , Integer.parseInt(id_provinsi), Integer.parseInt(id_kota), Integer.parseInt(is_active_lokasi)
                        , dataLokasi.get("created_at").toString(), dataLokasi.get("updated_at").toString()
                        , kotaData, provinsiData));
            }


            //get data Sub Bidang
            JSONArray subBidang = JO.getJSONArray("sub_bidang");

            for (int b = 0; b < subBidang.length(); b++) {
                String id_rel, id_usr, id_bdg, id_sb_bdg, is_act;
                String id_sbdg, id_adm, id_bg, is_ct;
                SubBidang_ subBdg = null;
                JSONObject dataSubBidang = (JSONObject) subBidang.get(b);

                id_rel = dataSubBidang.get("id_relasi_user_dan_sub_bidang").toString();
                id_usr = dataSubBidang.get("id_user").toString();
                id_bdg = dataSubBidang.get("id_bidang").toString();
                id_sb_bdg = dataSubBidang.get("id_sub_bidang").toString();
                is_act = dataSubBidang.get("is_active").toString();

                JSONObject dataSubBdng = (JSONObject) dataSubBidang.get("sub_bidang");

                id_sbdg = dataSubBdng.get("id_sub_bidang").toString();
                id_adm = dataSubBdng.get("id_admin").toString();
                id_bg = dataSubBdng.get("id_bidang").toString();
                is_ct = dataSubBdng.get("is_active").toString();

                subBdg = new SubBidang_(Integer.parseInt(id_sbdg), dataSubBdng.get("id_admin").toString()
                        , Integer.parseInt(id_bg), dataSubBdng.get("nama").toString(), Integer.parseInt(is_ct)
                        , dataSubBdng.get("deskripsi_singkat").toString(), dataSubBdng.get("created_at").toString()
                        , dataSubBdng.get("updated_at").toString());

                subBidangData.add(new SubBidang(Integer.parseInt(id_rel), Integer.parseInt(id_usr)
                        , Integer.parseInt(id_bdg), Integer.parseInt(id_sb_bdg), Integer.parseInt(is_act)
                        , dataSubBidang.get("created_at").toString(), dataSubBidang.get("updated_at").toString()
                        , subBdg));
            }


            //get data Bidang
            JSONObject dataBidang = (JSONObject) JO.get("bidang");

            id_bid = dataBidang.get("id_bidang").toString();
            id_admin = dataBidang.get("id_admin").toString();

            bidangData = new Bidang(Integer.parseInt(id_bid), dataBidang.get("id_admin").toString()
                    , dataBidang.get("nama").toString(), dataBidang.get("created_at").toString()
                    , dataBidang.get("updated_at").toString());


            //get data gambar
            JSONObject gbrData = (JSONObject) JO.get("gambar_kecil");

            String gbr_user, gbr_id, gbr_admin, gbr_ref, gbr_thumb;
            gbr_id = gbrData.get("id_user").toString();
            gbr_user = gbrData.get("id").toString();
            gbr_admin = gbrData.get("id_admin").toString();
            gbr_ref = gbrData.get("id_ref").toString();
            gbr_thumb = gbrData.get("is_thumb").toString();

            gambarkcl = new GambarKecil(Integer.parseInt(gbr_user)
                    , gbrData.get("id_user").toString()
                    , Integer.parseInt(gbr_admin)
                    , Integer.parseInt(gbr_ref), gbrData.get("type").toString(), gbrData.get("url").toString()
                    , gbrData.get("gambar").toString(), Integer.parseInt(gbr_thumb), gbrData.get("name").toString(), gbrData.get("created_at").toString()
                    , gbrData.get("updated_at").toString());


            //get data Portofolio
            JSONArray portofolio = JO.getJSONArray("portofolio");


            for (int loop_Port = 0; loop_Port < portofolio.length(); loop_Port++) {
                String id_port, id_UP, id_AP, act;

                JSONObject dataPortofolio = (JSONObject) portofolio.get(loop_Port);

                id_port = dataPortofolio.get("id_portofolio").toString();
                id_UP = dataPortofolio.get("id_user").toString();
                id_AP = dataPortofolio.get("id_admin").toString();
                act = dataPortofolio.get("is_active").toString();

                JSONObject url = (JSONObject) dataPortofolio.get("gambar_kecil");

                portofolioData.add(new Portofolio(Integer.parseInt(id_port), Integer.parseInt(id_UP)
                        , Integer.parseInt(id_AP), dataPortofolio.get("nama").toString(), dataPortofolio.get("teks").toString()
                        , Integer.parseInt(act), dataPortofolio.get("created_at").toString(), dataPortofolio.get("updated_at").toString()
                        , url.get("url").toString()
                ));
            }

            //get data data_Layanan
            JSONArray layanan = JO.getJSONArray("layanan");

            for (int loop_Layanan = 0; loop_Layanan < layanan.length(); loop_Layanan++) {
                String id_layanan, id_UL, id_AL, act_L, vL;

                JSONObject dataLayanan = (JSONObject) layanan.get(loop_Layanan);

                id_layanan = dataLayanan.get("id_layanan").toString();
                id_UL = dataLayanan.get("id_user").toString();
                id_AL = dataLayanan.get("id_admin").toString();
                act_L = dataLayanan.get("is_active").toString();
                vL = dataLayanan.get("visits").toString();

                JSONObject gambar = (JSONObject) dataLayanan.get("gambar_kecil");
                String id, admin, ref, thumb;

                id = gambar.get("id").toString();
                admin = gambar.get("id_admin").toString();
                ref = gambar.get("id_ref").toString();
                thumb = gambar.get("is_thumb").toString();

                gambarKecil = new GambarKecil(Integer.parseInt(id), gambar.get("id_user").toString()
                            , Integer.parseInt(admin), Integer.parseInt(ref), gambar.get("type").toString()
                            , gambar.get("url").toString(), gambar.get("gambar").toString(), Integer.parseInt(thumb)
                            , gambar.get("name").toString(), gambar.get("created_at").toString(), gambar.get("updated_at").toString()
                );

                layananData.add(new Layanan(Integer.parseInt(id_layanan), Integer.parseInt(id_UL)
                        , Integer.parseInt(id_AL), dataLayanan.get("bidang").toString(), dataLayanan.get("sub_bidang").toString()
                        , dataLayanan.get("nama").toString(), dataLayanan.get("teks").toString(), dataLayanan.get("tag").toString()
                        , Integer.parseInt(act_L), Integer.parseInt(vL), dataLayanan.get("created_at").toString()
                        , dataLayanan.get("updated_at").toString(), gambarKecil
                ));
            }

            //set hasilLayanan json
            id_user = JO.get("id_user").toString();
            hp = JO.get("hp").toString();
            wa = JO.get("wa").toString();
            id_bidang = JO.get("id_bidang").toString();
            is_active = JO.get("is_active").toString();

            switch (typeReq){
                case 1:
                    pekerjaDetail.detailPekerja = new DataPekerja(Integer.parseInt(id_user), JO.get("name").toString()
                            , JO.get("email").toString(), JO.get("gender").toString(), JO.get("hp").toString()
                            , JO.get("wa").toString(), JO.get("alamat").toString(), JO.get("tanggal_lahir").toString()
                            , Integer.parseInt(id_bidang), JO.get("pendidikan_terakhir").toString()
                            , JO.get("deskripsi").toString(), Integer.parseInt(is_active), JO.get("created_at").toString()
                            , JO.get("updated_at").toString(), lokasiData, bidangData, subBidangData
                            , gambarkcl, portofolioData, layananData);
                    break;
                case 2:
                    layananDetail.urlUser = gambarkcl.getUrl().toString();
                    break;
            }

            error = errorData.dataError(0);
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
//            error = errorData.dataError(1);
            error = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
//            error = errorData.dataError(2);
            error = e.toString();
        } catch (JSONException e) {
            e.printStackTrace();
//            error = errorData.dataError(3);
            error = e.toString();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (error.equals("tidak")) {
        } else {
            Toast.makeText(view, "Error!  " + error, Toast.LENGTH_SHORT).show();
        }
    }
}
