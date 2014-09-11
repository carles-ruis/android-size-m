package com.carles.sizematters.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.carles.sizematters.R;
import com.carles.sizematters.activity.BaseActivity;
import com.carles.sizematters.model.SizeCountriesConversion;

public class SizeCountriesAdapter extends ArrayAdapter<SizeCountriesConversion> {

    private final LayoutInflater inflater;
    private boolean showSizesUk;
    private boolean showSizesJp;

    /*- holder can be static because don't access to the private members of SizeCountriesAdapter. This way it's no coupled and can be accessed from outside the parent class */
    static class Holder {
        public TextView size;
        public TextView sizeEu;
        public TextView sizeUs;
        public TextView sizeUk;
        public TextView sizeJp;
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */

    public SizeCountriesAdapter(Context context, List<SizeCountriesConversion> sizes) {
        super(context, R.layout.list_item_size_countries, sizes);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.showSizesUk = ((BaseActivity) context).getApp().getShowSizesUk();
        this.showSizesJp = ((BaseActivity) context).getApp().getShowSizesJp();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;

        if (convertView == null) {

            /*- avoid re-inflate the view */
            convertView = inflater.inflate(R.layout.list_item_size_countries, null);

            /*- avoid re-using findViewById by storing the views in a holder */
            holder = new Holder();
            holder.size = (TextView) convertView.findViewById(R.id.item_size);
            holder.sizeEu = (TextView) convertView.findViewById(R.id.item_size_eu);
            holder.sizeUs = (TextView) convertView.findViewById(R.id.item_size_us);
            holder.sizeUk = (TextView) convertView.findViewById(R.id.item_size_uk);
            holder.sizeJp = (TextView) convertView.findViewById(R.id.item_size_jp);

            if (showSizesUk) {
                holder.sizeUk.setVisibility(View.VISIBLE);
            }
            if (showSizesJp) {
                holder.sizeJp.setVisibility(View.VISIBLE);
            }

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        SizeCountriesConversion item = getItem(position);
        holder.size.setText(item.getSize());
        holder.sizeEu.setText(item.getEu());
        holder.sizeUs.setText(item.getUs());
        holder.sizeUk.setText(item.getUk());
        holder.sizeJp.setText(item.getJp());
        return convertView;
    }

    @Override
    /*- must set return to true in order to show the listDivider between items */
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    /*- items are not clickable */
    public boolean isEnabled(int position) {
        return false;
    }
}
