package com.carles.sizematters.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.carles.sizematters.R;
import com.carles.sizematters.activity.MainActivity;
import com.carles.sizematters.adapter.SizeCountriesAdapter;
import com.carles.sizematters.helper.FileHelper;
import com.carles.sizematters.model.SizeCountriesConversion;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class BaseSizeCountriesFragment extends Fragment {

    protected List<SizeCountriesConversion> sizes;
    protected int rawResourceId;

    protected abstract int getRawResourceId();

    private View view;

    public BaseSizeCountriesFragment() {
        super();
        rawResourceId = getRawResourceId();
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        loadSizeConversionsByCountry();

        /*- store parent activity as listener if needed*/
        /*- ... */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_size_countries, container, false);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            final ListView sizesList = (ListView) view.findViewById(android.R.id.list);
            sizesList.setAdapter(new SizeCountriesAdapter(getActivity(), sizes));

        } else {

            LinearLayout horizontalContainer = (LinearLayout) view.findViewById(R.id.horizontal_container);

            View column;
            TextView size;
            TextView sizeEu;
            TextView sizeUs;
            TextView sizeUk;
            TextView sizeJp;

            for (SizeCountriesConversion scc : sizes) {

                column = inflater.inflate(R.layout.list_item_size_countries, null);
                size = (TextView) column.findViewById(R.id.item_size);
                sizeEu = (TextView) column.findViewById(R.id.item_size_eu);
                sizeUs = (TextView) column.findViewById(R.id.item_size_us);
                sizeUk = (TextView) column.findViewById(R.id.item_size_uk);
                sizeJp = (TextView) column.findViewById(R.id.item_size_jp);

                size.setText(scc.getSize());
                sizeEu.setText(scc.getEu());
                sizeUs.setText(scc.getUs());
                sizeUk.setText(scc.getUk());
                sizeJp.setText(scc.getJp());

                if (activity().getApp().getShowSizesUk()) {
                    sizeUk.setVisibility(View.VISIBLE);
                }
                if (activity().getApp().getShowSizesJp()) {
                    sizeJp.setVisibility(View.VISIBLE);
                }

                horizontalContainer.addView(column);
                horizontalContainer.addView(getDivider());

            }

            /*- use getParentFragment in case we need to access to the parent of this nested fragment */
            /*- ((BaseContainerFragment)getParentFragment())  ... */
        }

        return view;
    }

    private void loadSizeConversionsByCountry() {
        try {
            InputStream is = getResources().openRawResource(rawResourceId);
            sizes = FileHelper.readCountriesConversionResource(is);
            is.close();
        } catch (IOException e) {
            // TODO report error
        }
    }

    private View getDivider() {
        View ret = new View(getActivity());
        LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        ret.setLayoutParams(lp);

        if (android.os.Build.VERSION.SDK_INT >= 16) {
            ret.setBackground(getResources().getDrawable(R.color.list_divider));
        } else {
            ret.setBackgroundDrawable(getResources().getDrawable(R.color.list_divider));
        }

        return ret;
    }

    private MainActivity activity() {
        return (MainActivity) getActivity();
    }
}
