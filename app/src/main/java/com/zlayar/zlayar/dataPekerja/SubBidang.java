
package com.zlayar.zlayar.dataPekerja;


public class SubBidang {

    private Integer idRelasiUserDanSubBidang;
    private Integer idUser;
    private Integer idBidang;
    private Integer idSubBidang;
    private Integer isActive;
    private String createdAt;
    private String updatedAt;
    private SubBidang_ subBidang;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubBidang() {
    }

    /**
     * 
     * @param updatedAt
     * @param isActive
     * @param idBidang
     * @param createdAt
     * @param subBidang
     * @param idSubBidang
     * @param idUser
     * @param idRelasiUserDanSubBidang
     */
    public SubBidang(Integer idRelasiUserDanSubBidang, Integer idUser, Integer idBidang, Integer idSubBidang, Integer isActive, String createdAt, String updatedAt, SubBidang_ subBidang) {
        super();
        this.idRelasiUserDanSubBidang = idRelasiUserDanSubBidang;
        this.idUser = idUser;
        this.idBidang = idBidang;
        this.idSubBidang = idSubBidang;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.subBidang = subBidang;
    }

    public Integer getIdRelasiUserDanSubBidang() {
        return idRelasiUserDanSubBidang;
    }

    public void setIdRelasiUserDanSubBidang(Integer idRelasiUserDanSubBidang) {
        this.idRelasiUserDanSubBidang = idRelasiUserDanSubBidang;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdBidang() {
        return idBidang;
    }

    public void setIdBidang(Integer idBidang) {
        this.idBidang = idBidang;
    }

    public Integer getIdSubBidang() {
        return idSubBidang;
    }

    public void setIdSubBidang(Integer idSubBidang) {
        this.idSubBidang = idSubBidang;
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

    public SubBidang_ getSubBidang() {
        return subBidang;
    }

    public void setSubBidang(SubBidang_ subBidang) {
        this.subBidang = subBidang;
    }

}
