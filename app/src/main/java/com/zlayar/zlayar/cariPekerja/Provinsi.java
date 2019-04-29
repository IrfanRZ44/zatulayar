
package com.zlayar.zlayar.cariPekerja;


public class Provinsi {

    private Integer idKota;
    private Integer idProvinsi;
    private String kota;
    private String tglEn;
    private String tglEd;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Provinsi() {
    }

    /**
     * 
     * @param tglEd
     * @param kota
     * @param idKota
     * @param tglEn
     * @param idProvinsi
     */
    public Provinsi(Integer idKota, Integer idProvinsi, String kota, String tglEn, String tglEd) {
        super();
        this.idKota = idKota;
        this.idProvinsi = idProvinsi;
        this.kota = kota;
        this.tglEn = tglEn;
        this.tglEd = tglEd;
    }

    public Integer getIdKota() {
        return idKota;
    }

    public void setIdKota(Integer idKota) {
        this.idKota = idKota;
    }

    public Integer getIdProvinsi() {
        return idProvinsi;
    }

    public void setIdProvinsi(Integer idProvinsi) {
        this.idProvinsi = idProvinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTglEn() {
        return tglEn;
    }

    public void setTglEn(String tglEn) {
        this.tglEn = tglEn;
    }

    public String getTglEd() {
        return tglEd;
    }

    public void setTglEd(String tglEd) {
        this.tglEd = tglEd;
    }

}
