
package com.zlayar.zlayar.dataPekerja;


public class SubBidang_ {

    private Integer idSubBidang;
    private String idAdmin;
    private Integer idBidang;
    private String nama;
    private Integer isActive;
    private String deskripsiSingkat;
    private String createdAt;
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubBidang_() {
    }

    /**
     * 
     * @param updatedAt
     * @param isActive
     * @param idBidang
     * @param createdAt
     * @param idAdmin
     * @param idSubBidang
     * @param nama
     * @param deskripsiSingkat
     */
    public SubBidang_(Integer idSubBidang, String idAdmin, Integer idBidang, String nama, Integer isActive, String deskripsiSingkat, String createdAt, String updatedAt) {
        super();
        this.idSubBidang = idSubBidang;
        this.idAdmin = idAdmin;
        this.idBidang = idBidang;
        this.nama = nama;
        this.isActive = isActive;
        this.deskripsiSingkat = deskripsiSingkat;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getIdSubBidang() {
        return idSubBidang;
    }

    public void setIdSubBidang(Integer idSubBidang) {
        this.idSubBidang = idSubBidang;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getDeskripsiSingkat() {
        return deskripsiSingkat;
    }

    public void setDeskripsiSingkat(String deskripsiSingkat) {
        this.deskripsiSingkat = deskripsiSingkat;
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
