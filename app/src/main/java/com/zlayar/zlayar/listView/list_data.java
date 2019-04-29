package com.zlayar.zlayar.listView;

/**
 * Created by IrfanRZ on 5/21/2018.
 */

public class list_data {

    private int id;
    private String company, description, address;

    public list_data(int id, String company, String description, String address) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
