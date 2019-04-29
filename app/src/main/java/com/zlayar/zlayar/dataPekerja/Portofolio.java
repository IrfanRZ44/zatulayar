
package com.zlayar.zlayar.dataPekerja;


public class Portofolio {

    private Integer idPortofolio;
    private Integer idUser;
    private Integer idAdmin;
    private String nama;
    private String teks;
    private Integer isActive;
    private String createdAt;
    private String updatedAt;
    private String Url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Portofolio() {
    }

    /**
     * 
     * @param updatedAt
     * @param isActive
     * @param teks
     * @param createdAt
     * @param idAdmin
     * @param idUser
     * @param nama
     * @param idPortofolio
     */
    public Portofolio(Integer idPortofolio, Integer idUser, Integer idAdmin, String nama, String teks, Integer isActive, String createdAt, String updatedAt, String Url) {
        super();
        this.idPortofolio = idPortofolio;
        this.idUser = idUser;
        this.idAdmin = idAdmin;
        this.nama = nama;
        this.teks = teks;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.Url = Url;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Integer getIdPortofolio() {
        return idPortofolio;
    }

    public void setIdPortofolio(Integer idPortofolio) {
        this.idPortofolio = idPortofolio;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTeks() {
        return teks;
    }

    public void setTeks(String teks) {
        this.teks = teks;
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

}
