
package com.zlayar.zlayar.dataBidang;

import java.util.ArrayList;
import java.util.List;

public class BidangKategori {

    private Integer idBidang;
    private String nama;
    private ArrayList<SubBidang> subBidang = null;
    private GambarKecil gambarKecil;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BidangKategori() {
    }

    /**
     * 
     * @param idBidang
     * @param gambarKecil
     * @param subBidang
     * @param nama
     */
    public BidangKategori(Integer idBidang, String nama, ArrayList<SubBidang> subBidang, GambarKecil gambarKecil) {
        super();
        this.idBidang = idBidang;
        this.nama = nama;
        this.subBidang = subBidang;
        this.gambarKecil = gambarKecil;
    }

    public Integer getIdBidang() {
        return idBidang;
    }

    public void setIdBidang(Integer idBidang) {
        this.idBidang = idBidang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ArrayList<SubBidang> getSubBidang() {
        return subBidang;
    }

    public void setSubBidang(ArrayList<SubBidang> subBidang) {
        this.subBidang = subBidang;
    }

    public GambarKecil getGambarKecil() {
        return gambarKecil;
    }

    public void setGambarKecil(GambarKecil gambarKecil) {
        this.gambarKecil = gambarKecil;
    }

}
