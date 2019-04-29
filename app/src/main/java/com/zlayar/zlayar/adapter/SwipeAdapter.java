package com.zlayar.zlayar.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.ImageView;

import com.zlayar.zlayar.R;

/**
 * Created by IrfanRZ on 6/1/2018.
 */

public class SwipeAdapter extends PagerAdapter {
    public static int[] image_resources = {R.drawable.gambar_slide1,
            R.drawable.gambar_slide2,
            R.drawable.gambar_slide3,
            R.drawable.gambar_slide4,
            R.drawable.gambar_slide5};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public SwipeAdapter(Context ctx){
        this.ctx=ctx;
    }


    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        android.widget.ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        imageView.setImageResource(image_resources[position]);
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }

}
