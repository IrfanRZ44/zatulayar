package com.zlayar.zlayar.data;

import com.zlayar.zlayar.cariLayanan.GambarSedang;

/**
 * Created by IrfanRZ on 01/10/2018.
 */

public class dataFavorit {
    String title, deskripsi, bidang, gbr;
    int id, typeFav;

    public dataFavorit(String title, String deskripsi, String bidang, int id, int typeFav, String gbr) {
        this.title = title;
        this.deskripsi = deskripsi;
        this.bidang = bidang;
        this.id = id;
        this.typeFav = typeFav;
        this.gbr = gbr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeFav() {
        return typeFav;
    }

    public void setTypeFav(int typeFav) {
        this.typeFav = typeFav;
    }

    public String getGbr() {
        return gbr;
    }

    public void setGbr(String gbr) {
        this.gbr = gbr;
    }
}
