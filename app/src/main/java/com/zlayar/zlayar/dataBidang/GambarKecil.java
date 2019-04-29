
package com.zlayar.zlayar.dataBidang;


public class GambarKecil {

    private Integer id;
    private String idUser;
    private Integer idAdmin;
    private Integer idRef;
    private String type;
    private String url;
    private String gambar;
    private Integer isThumb;
    private String name;
    private String createdAt;
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GambarKecil() {
    }

    /**
     * 
     * @param updatedAt
     * @param id
     * @param gambar
     * @param createdAt
     * @param name
     * @param idAdmin
     * @param isThumb
     * @param idUser
     * @param idRef
     * @param type
     * @param url
     */
    public GambarKecil(Integer id, String idUser, Integer idAdmin, Integer idRef, String type, String url, String gambar, Integer isThumb, String name, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.idUser = idUser;
        this.idAdmin = idAdmin;
        this.idRef = idRef;
        this.type = type;
        this.url = url;
        this.gambar = gambar;
        this.isThumb = isThumb;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdRef() {
        return idRef;
    }

    public void setIdRef(Integer idRef) {
        this.idRef = idRef;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Integer getIsThumb() {
        return isThumb;
    }

    public void setIsThumb(Integer isThumb) {
        this.isThumb = isThumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
