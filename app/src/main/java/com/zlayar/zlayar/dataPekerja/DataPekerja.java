
package com.zlayar.zlayar.dataPekerja;

import java.util.ArrayList;
import java.util.List;

public class DataPekerja {

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
    private Integer isActive;
    private String createdAt;
    private String updatedAt;
    private ArrayList<Lokasi> lokasi = null;
    private Bidang bidang;
    private ArrayList<SubBidang> subBidang = null;
    private GambarKecil gambarKecil;
    private ArrayList<Portofolio> portofolio = null;
    private ArrayList<Layanan> layanan = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DataPekerja() {
    }

    /**
     * 
     * @param hp
     * @param idBidang
     * @param gambarKecil
     * @param subBidang
     * @param pendidikanTerakhir
     * @param tanggalLahir
     * @param layanan
     * @param deskripsi
     * @param updatedAt
     * @param isActive
     * @param bidang
     * @param email
     * @param createdAt
     * @param name
     * @param lokasi
     * @param idUser
     * @param gender
     * @param wa
     * @param portofolio
     * @param alamat
     */
    public DataPekerja(Integer idUser, String name, String email, String gender, String hp, String wa, String alamat, String tanggalLahir, Integer idBidang, String pendidikanTerakhir, String deskripsi, Integer isActive, String createdAt, String updatedAt, ArrayList<Lokasi> lokasi, Bidang bidang, ArrayList<SubBidang> subBidang, GambarKecil gambarKecil, ArrayList<Portofolio> portofolio, ArrayList<Layanan> layanan) {
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
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lokasi = lokasi;
        this.bidang = bidang;
        this.subBidang = subBidang;
        this.gambarKecil = gambarKecil;
        this.portofolio = portofolio;
        this.layanan = layanan;
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

    public ArrayList<Lokasi> getLokasi() {
        return lokasi;
    }

    public void setLokasi(ArrayList<Lokasi> lokasi) {
        this.lokasi = lokasi;
    }

    public Bidang getBidang() {
        return bidang;
    }

    public void setBidang(Bidang bidang) {
        this.bidang = bidang;
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

    public ArrayList<Portofolio> getPortofolio() {
        return portofolio;
    }

    public void setPortofolio(ArrayList<Portofolio> portofolio) {
        this.portofolio = portofolio;
    }

    public ArrayList<Layanan> getLayanan() {
        return layanan;
    }

    public void setLayanan(ArrayList<Layanan> layanan) {
        this.layanan = layanan;
    }

}
