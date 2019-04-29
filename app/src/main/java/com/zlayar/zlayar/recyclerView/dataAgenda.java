package com.zlayar.zlayar.recyclerView;

/**
 * Created by IrfanRZ on 25/09/2018.
 */

public class dataAgenda {
    String title, text;
    String image;

    public dataAgenda(String image, String title, String text) {
        this.image = image;
        this.title = title;
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
