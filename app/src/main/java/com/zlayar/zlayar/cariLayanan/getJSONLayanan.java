package com.zlayar.zlayar.cariLayanan;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.zlayar.zlayar.FragmentCari.fragmentLayanan;
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

/**
 * Created by IrfanRZ on 6/24/2018.
 */

public class getJSONLayanan extends AsyncTask<Void, Void, Void> {
    private String data = "";
    GambarSedang gambarData = null;
    private Context view;
    private errorGetData errorData;
    private URL url;
    private int typeSearch, idUser, bidang, subBidang;
    private String error, lokasi, text;

    public getJSONLayanan(String text, int bidang, int subBidang, String lokasi, int idUser, int typeSearch, Context view) {
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
                url = new URL("https://zatulayar.com/api/layanan");
            } else if (typeSearch == 2){
                url = new URL("https://zatulayar.com/api/layanan?bidang=" + bidang +"&subbidang=" + subBidang);
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
                String id_layanan, id_user, id_admin, is_active, visits;

                id_layanan = JO.get("id_layanan").toString();
                id_user = JO.get("id_user").toString();
                id_admin = JO.get("id_admin").toString();
                is_active = JO.get("is_active").toString();
                visits = JO.get("visits").toString();

                //get data gambar
                JSONObject gbrData = (JSONObject) JO.get("gambar_sedang");

                String gbr_id, gbr_admin, gbr_ref, gbr_thumb;

                gbr_id = gbrData.get("id").toString();
                gbr_admin = gbrData.get("id_admin").toString();
                gbr_ref = gbrData.get("id_ref").toString();
                gbr_thumb = gbrData.get("is_thumb").toString();

                gambarData = new GambarSedang(Integer.parseInt(gbr_id)
                        , gbrData.get("id_user").toString()
                        , Integer.parseInt(gbr_admin)
                        , Integer.parseInt(gbr_ref), gbrData.get("type").toString(), gbrData.get("url").toString()
                        , gbrData.get("gambar").toString(), Integer.parseInt(gbr_thumb), gbrData.get("name").toString()
                        , gbrData.get("created_at").toString()
                        , gbrData.get("updated_at").toString());

                fragmentLayanan.hasilLayanan.add(new CariLayanan(Integer.parseInt(id_layanan)
                        , Integer.parseInt(id_user), Integer.parseInt(id_admin), JO.get("bidang").toString()
                        , JO.get("sub_bidang").toString(), JO.get("nama").toString(), JO.get("teks").toString()
                        , JO.get("tag").toString(), Integer.parseInt(is_active), Integer.parseInt(visits)
                        , JO.get("created_at").toString(), JO.get("updated_at").toString(), gambarData
                        ));

                error = errorData.dataError(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            error = errorData.dataError(1);
            error = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            error = errorData.dataError(2);
//            error = e.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            error = errorData.dataError(3);
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
