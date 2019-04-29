package com.zlayar.zlayar.dataBidang;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.zlayar.zlayar.FragmentMain.FragmentCariJasa;
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

public class getBidang extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private Context view;
    private errorGetData errorData;
    private URL url;
    private String error;
    private ArrayList<SubBidang> dataSubBidang = new ArrayList<SubBidang>();
    private GambarKecil gbr = null;

    public getBidang(Context view) {
        errorData = new errorGetData();
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            url = new URL("https://zatulayar.com/api/bidang");

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
                dataSubBidang = new ArrayList<SubBidang>();
                JSONObject JO = (JSONObject) JA.get(i);
                String id_bidang;

                id_bidang = JO.get("id_bidang").toString();

                JSONArray subBidang = (JSONArray) JO.get("sub_bidang");

                if (subBidang.length() == 0) {
                    dataSubBidang = null;
                } else {
                    for (int a = 0; a < subBidang.length(); a++) {
                        String id, bidang, act;

                        JSONObject dataSub = (JSONObject) subBidang.get(a);

                        id = dataSub.get("id_sub_bidang").toString();
                        bidang = dataSub.get("id_bidang").toString();
                        act = dataSub.get("is_active").toString();

//                        if ((dataSub.toString().isEmpty()) || (dataSub == null)) {
//                            dataFilterBidang = null;
//                        } else {
                            dataSubBidang.add(new SubBidang(Integer.parseInt(id), dataSub.get("id_admin").toString()
                                    , Integer.parseInt(bidang), dataSub.get("nama").toString(), Integer.parseInt(act)
                                    , dataSub.get("deskripsi_singkat").toString(), dataSub.get("created_at").toString()
                                    , dataSub.get("updated_at").toString()
                            ));
//                        }
                    }
                }

                //get data gambar
                JSONObject gbrData = (JSONObject) JO.get("gambar_kecil");

                String gbr_id, gbr_admin, gbr_ref, gbr_thumb;

                gbr_id = gbrData.get("id").toString();
                gbr_admin = gbrData.get("id_admin").toString();
                gbr_ref = gbrData.get("id_ref").toString();
                gbr_thumb = gbrData.get("is_thumb").toString();

                gbr = new GambarKecil(Integer.parseInt(gbr_id)
                        , gbrData.get("id_user").toString()
                        , Integer.parseInt(gbr_admin)
                        , Integer.parseInt(gbr_ref), gbrData.get("type").toString(), gbrData.get("url").toString()
                        , gbrData.get("gambar").toString(), Integer.parseInt(gbr_thumb), gbrData.get("name").toString()
                        , gbrData.get("created_at").toString()
                        , gbrData.get("updated_at").toString());

                FragmentCariJasa.hasilBidang.add(new BidangKategori(Integer.parseInt(id_bidang)
                        , JO.get("nama").toString(), dataSubBidang, gbr));

                error = errorData.dataError(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            error = errorData.dataError(1);
//            error = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            error = errorData.dataError(2);
//            error = e.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            error = errorData.dataError(3);
//            error = e.toString();
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
