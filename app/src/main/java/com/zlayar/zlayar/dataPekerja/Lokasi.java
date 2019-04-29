
package com.zlayar.zlayar.dataPekerja;


public class Lokasi {

    private Integer idRelasiUserDanLokasi;
    private Integer idUser;
    private Integer idProvinsi;
    private Integer idKota;
    private Integer isActive;
    private String createdAt;
    private String updatedAt;
    private Kota kota;
    private Provinsi provinsi;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Lokasi() {
    }

    /**
     * 
     * @param updatedAt
     * @param isActive
     * @param provinsi
     * @param idRelasiUserDanLokasi
     * @param createdAt
     * @param idUser
     * @param kota
     * @param idKota
     * @param idProvinsi
     */
    public Lokasi(Integer idRelasiUserDanLokasi, Integer idUser, Integer idProvinsi, Integer idKota, Integer isActive, String createdAt, String updatedAt, Kota kota, Provinsi provinsi) {
        super();
        this.idRelasiUserDanLokasi = idRelasiUserDanLokasi;
        this.idUser = idUser;
        this.idProvinsi = idProvinsi;
        this.idKota = idKota;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.kota = kota;
        this.provinsi = provinsi;
    }

    public Integer getIdRelasiUserDanLokasi() {
        return idRelasiUserDanLokasi;
    }

    public void setIdRelasiUserDanLokasi(Integer idRelasiUserDanLokasi) {
        this.idRelasiUserDanLokasi = idRelasiUserDanLokasi;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdProvinsi() {
        return idProvinsi;
    }

    public void setIdProvinsi(Integer idProvinsi) {
        this.idProvinsi = idProvinsi;
    }

    public Integer getIdKota() {
        return idKota;
    }

    public void setIdKota(Integer idKota) {
        this.idKota = idKota;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

}
