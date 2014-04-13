package com.carles.sizematters.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.carles.sizematters.R;

public class DividerView extends RelativeLayout {

    public DividerView(Context context) {
        super(context);
        initView(context);
    }

    public DividerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_divider, null);

//        int heightDps = ViewUtil.getDps(context, R.dimen.divider_height);
//        int heightPx = ViewUtil.dipsToPixels(context, heightDps);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, heightPx);
//
//        int marginDps = ViewUtil.getDps(context, R.dimen.size_countries_divider_margin);
//        int marginPx = ViewUtil.dipsToPixels(context, marginDps);
//        lp.setMargins(marginPx, 0, marginPx, 0);
//
//        setLayoutParams(lp);
//
//        setBackgroundColor(getResources().getColor(R.color.list_divider));

    }
}
