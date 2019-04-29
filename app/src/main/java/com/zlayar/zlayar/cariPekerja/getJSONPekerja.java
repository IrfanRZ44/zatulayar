package com.zlayar.zlayar.cariPekerja;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.zlayar.zlayar.FragmentCari.fragmentPekerja;
import com.zlayar.zlayar.data.errorGetData;

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

public class getJSONPekerja extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private int typeSearch, idUser, bidang, subBidang;;
    private static ArrayList<Lokasi> lokasiData = new ArrayList<Lokasi>();
    private static Bidang bidangData = null;
    private Context view;
    private String error, lokasi, text;
    private errorGetData errorData;
    private URL url;

    public getJSONPekerja(String text,int bidang, int subBidang, String lokasi, int idUser, int typeSearch, Context view) {
        errorData = new errorGetData();
        this.typeSearch = typeSearch;
        this.bidang = bidang;
        this.text = text;
        this.subBidang = subBidang;
        this.lokasi = lokasi;
        this.idUser = idUser;
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            if (typeSearch == 1) {
                url = new URL("https://zatulayar.com/api/pekerja");
            } else if (typeSearch == 2){
                url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang +"&subbidang=" + subBidang);
            }
            else {
                if ((!lokasi.isEmpty()) && (!text.isEmpty()) && (bidang>0) && (subBidang>0)){
                    url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang +"&subbidang=" + subBidang +
                            "&lokasi="+lokasi+"&text="+text);
                }
                else if ((!lokasi.isEmpty()) && (!text.isEmpty()) && (bidang>0) && (subBidang==0)){
                    url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang +
                            "&lokasi="+lokasi+"&text="+text);
                }
                else if ((!lokasi.isEmpty()) && (text.isEmpty()) && (bidang==0) && (subBidang==0)){
                    url = new URL("https://zatulayar.com/api/pekerja?" + "&lokasi="+lokasi);
                }
                else if ((lokasi.isEmpty()) && (text.isEmpty()) && (bidang==0) && (subBidang==0)){
                    url = new URL("https://zatulayar.com/api/pekerja");
                }
                else if ((!lokasi.isEmpty()) && (text.isEmpty()) && (bidang>0) && (subBidang>0)){
                    url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang +"&subbidang=" + subBidang +
                            "&lokasi="+lokasi);
                }
                else if ((lokasi.isEmpty()) && (!text.isEmpty()) && (bidang>0) && (subBidang>0)){
                    url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang +"&subbidang=" + subBidang +
                            "&text="+text);
                }
                else if ((lokasi.isEmpty()) && (text.isEmpty()) && (bidang>0) && (subBidang>0)){
                    url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang +"&subbidang=" + subBidang);
                }
                else if ((!lokasi.isEmpty()) && (text.isEmpty()) && (bidang>0) && (subBidang==0)){
                    url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang +
                            "&lokasi="+lokasi);
                }
                else if ((lokasi.isEmpty()) && (!text.isEmpty()) && (bidang>0) && (subBidang==0)){
                    url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang+
                            "&text="+text);
                }
                else if ((lokasi.isEmpty()) && (text.isEmpty()) && (bidang>0) && (subBidang>0)){
                    url = new URL("https://zatulayar.com/api/pekerja?bidang="+ bidang);
                }
            }

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String dataUser = "";
            while (dataUser != null) {
                dataUser = bufferedReader.readLine();
                data = data + dataUser;
            }
            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                String id_user, hp, wa, id_bidang, is_active, id_bid, id_admin;
                GambarKecil gambarkcl = null;

                lokasiData = new ArrayList<Lokasi>();


                //typeSearch data Lokasi
                JSONArray lokasi = JO.getJSONArray("lokasi");
                for (int a = 0; a < lokasi.length(); a++) {
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


                //typeSearch data Bidang
                JSONObject dataBidang = (JSONObject) JO.get("bidang");

                id_bid = dataBidang.get("id_bidang").toString();
                id_admin = dataBidang.get("id_admin").toString();

                bidangData = new Bidang(Integer.parseInt(id_bid), dataBidang.get("id_admin").toString()
                        , dataBidang.get("nama").toString(), dataBidang.get("created_at").toString()
                        , dataBidang.get("updated_at").toString());


                //typeSearch data gambar
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

                //set hasil Layanan json
                id_user = JO.get("id_user").toString();
                hp = JO.get("hp").toString();
                wa = JO.get("wa").toString();
                id_bidang = JO.get("id_bidang").toString();
                is_active = JO.get("is_active").toString();

                fragmentPekerja.hasilPekerja.add(new CariPekerja(Integer.parseInt(id_user), JO.get("name").toString()
                        , JO.get("email").toString(), JO.get("gender").toString(), JO.get("hp").toString()
                        , JO.get("wa").toString(), JO.get("alamat").toString(), JO.get("tanggal_lahir").toString()
                        , Integer.parseInt(id_bidang), JO.get("pendidikan_terakhir").toString()
                        , JO.get("deskripsi").toString()
                        , Integer.parseInt(is_active), JO.get("created_at").toString(), JO.get("updated_at").toString()
                        , lokasiData, bidangData, gambarkcl));

                error = errorData.dataError(0);
            }
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
