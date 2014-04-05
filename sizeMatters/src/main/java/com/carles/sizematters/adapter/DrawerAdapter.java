package com.carles.sizematters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.carles.sizematters.R;

public class DrawerAdapter extends ArrayAdapter<DrawerOption> {

    private final LayoutInflater inflater;

    public DrawerAdapter(Context context) {
        super(context, R.layout.drawer_item, DrawerOption.values());
        // super(context, , android.R.layout.simple_list_item_1, DrawerOption.values());
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.drawer_item, null);
        }

        final DrawerOption item = getItem(position);
        final TextView text = (TextView) convertView.findViewById(R.id.drawer_item_text);
        // final TextView text = (TextView) convertView.findViewById(android.R.id.text1);
        text.setText(getContext().getString(item.menuId));
        /*- set icon for each item in the navigation drawer */
        text.setCompoundDrawablesWithIntrinsicBounds(item.iconId, 0, 0, 0);

        return convertView;
    }

}
