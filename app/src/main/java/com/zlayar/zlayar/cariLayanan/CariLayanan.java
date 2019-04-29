package com.zlayar.zlayar.cariLayanan;


public class CariLayanan {

    private Integer idLayanan;
    private Integer idUser;
    private Integer idAdmin;
    private String bidang;
    private String subBidang;
    private String nama;
    private String teks;
    private String tag;
    private Integer isActive;
    private Integer visits;
    private String createdAt;
    private String updatedAt;
    private GambarSedang gambarSedang;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CariLayanan() {
    }

    /**
     * 
     * @param updatedAt
     * @param isActive
     * @param teks
     * @param gambarSedang
     * @param bidang
     * @param createdAt
     * @param tag
     * @param subBidang
     * @param idAdmin
     * @param idUser
     * @param nama
     * @param idLayanan
     * @param visits
     */
    public CariLayanan(Integer idLayanan, Integer idUser, Integer idAdmin, String bidang, String subBidang, String nama, String teks, String tag, Integer isActive, Integer visits, String createdAt, String updatedAt, GambarSedang gambarSedang) {
        super();
        this.idLayanan = idLayanan;
        this.idUser = idUser;
        this.idAdmin = idAdmin;
        this.bidang = bidang;
        this.subBidang = subBidang;
        this.nama = nama;
        this.teks = teks;
        this.tag = tag;
        this.isActive = isActive;
        this.visits = visits;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.gambarSedang = gambarSedang;
    }

    public Integer getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(Integer idLayanan) {
        this.idLayanan = idLayanan;
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

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getSubBidang() {
        return subBidang;
    }

    public void setSubBidang(String subBidang) {
        this.subBidang = subBidang;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
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

    public GambarSedang getGambarSedang() {
        return gambarSedang;
    }

    public void setGambarSedang(GambarSedang gambarSedang) {
        this.gambarSedang = gambarSedang;
    }

}
