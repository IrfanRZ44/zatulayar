package com.zlayar.zlayar.cariPekerja;

import java.util.List;

public class CariPekerja {

    private Integer idUser;
    private String name;
    private String email;
    private String gender;
    private String hp;
    private String wa;
    private String alamat;
    private String tanggalLahir;
    private Integer idBidang;
    private String pendidikanTerakhir;
    private String deskripsi;
    private Integer isKomunitas;
    private String createdAt;
    private String updatedAt;
    private List<Lokasi> lokasi = null;
    private Bidang bidang;
    private GambarKecil gambarKecil;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CariPekerja() {
    }

    /**
     * 
     * @param hp
     * @param idBidang
     * @param gambarKecil
     * @param pendidikanTerakhir
     * @param tanggalLahir
     * @param deskripsi
     * @param updatedAt
     * @param bidang
     * @param email
     * @param createdAt
     * @param name
     * @param lokasi
     * @param idUser
     * @param gender
     * @param isKomunitas
     * @param wa
     * @param alamat
     */
    public CariPekerja(Integer idUser, String name, String email, String gender, String hp, String wa, String alamat, String tanggalLahir, Integer idBidang, String pendidikanTerakhir, String deskripsi, Integer isKomunitas, String createdAt, String updatedAt, List<Lokasi> lokasi, Bidang bidang, GambarKecil gambarKecil) {
        super();
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.hp = hp;
        this.wa = wa;
        this.alamat = alamat;
        this.tanggalLahir = tanggalLahir;
        this.idBidang = idBidang;
        this.pendidikanTerakhir = pendidikanTerakhir;
        this.deskripsi = deskripsi;
        this.isKomunitas = isKomunitas;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lokasi = lokasi;
        this.bidang = bidang;
        this.gambarKecil = gambarKecil;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getWa() {
        return wa;
    }

    public void setWa(String wa) {
        this.wa = wa;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Integer getIdBidang() {
        return idBidang;
    }

    public void setIdBidang(Integer idBidang) {
        this.idBidang = idBidang;
    }

    public String getPendidikanTerakhir() {
        return pendidikanTerakhir;
    }

    public void setPendidikanTerakhir(String pendidikanTerakhir) {
        this.pendidikanTerakhir = pendidikanTerakhir;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getIsKomunitas() {
        return isKomunitas;
    }

    public void setIsKomunitas(Integer isKomunitas) {
        this.isKomunitas = isKomunitas;
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

    public List<Lokasi> getLokasi() {
        return lokasi;
    }

    public void setLokasi(List<Lokasi> lokasi) {
        this.lokasi = lokasi;
    }

    public Bidang getBidang() {
        return bidang;
    }

    public void setBidang(Bidang bidang) {
        this.bidang = bidang;
    }

    public GambarKecil getGambarKecil() {
        return gambarKecil;
    }

    public void setGambarKecil(GambarKecil gambarKecil) {
        this.gambarKecil = gambarKecil;
    }

}
