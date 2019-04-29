package com.zlayar.zlayar.dataPekerja;


public class Bidang {

    private Integer idBidang;
    private String idAdmin;
    private String nama;
    private String createdAt;
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Bidang() {
    }

    /**
     * 
     * @param updatedAt
     * @param idBidang
     * @param createdAt
     * @param idAdmin
     * @param nama
     */
    public Bidang(Integer idBidang, String idAdmin, String nama, String createdAt, String updatedAt) {
        super();
        this.idBidang = idBidang;
        this.idAdmin = idAdmin;
        this.nama = nama;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdBidang() {
        return idBidang;
    }

    public void setIdBidang(Integer idBidang) {
        this.idBidang = idBidang;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

}
