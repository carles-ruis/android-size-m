package com.carles.sizematters.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.carles.sizematters.C;
import com.carles.sizematters.R;
import com.carles.sizematters.activity.MainActivity;
import com.carles.sizematters.helper.FileHelper;
import com.carles.sizematters.helper.PrefHelper;
import com.carles.sizematters.model.WomenSizesConversion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WomenSizesFragment extends Fragment {

    protected Map<String, WomenSizesConversion> sizes;
    protected int rawResourceId;
    protected int layoutResourceId;
    private String unitsSelectedConstant;

    public WomenSizesFragment() {
        this.rawResourceId = R.raw.sizes_women;
        this.layoutResourceId = R.layout.fragment_size_women;
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        InputStream is = getResources().openRawResource(rawResourceId);

        unitsSelectedConstant = ((MainActivity) activity).getApp().getUnitsSelectedConstant();
        boolean showInInches = C.USER_PREF_UNITS_INCHES.equals(unitsSelectedConstant);

        sizes = FileHelper.readWomenSizesConversionResource(is, showInInches);

        /*- store parent activity as listener if needed*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*- allows the fragment to contribute in the action bar */
        // setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(layoutResourceId, container, false);

        final TextView sizeBust = (TextView) view.findViewById(R.id.item_size_bust);
        final TextView sizeWaist = (TextView) view.findViewById(R.id.item_size_waist);
        final TextView sizeHip = (TextView) view.findViewById(R.id.item_size_hip);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_sizes);
        List<String> sizeCodes = new ArrayList<String>(sizes.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
                sizeCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence key = ((TextView) view).getText();
                WomenSizesConversion selection = sizes.get(key);
                sizeBust.setText(selection.getBust());
                sizeWaist.setText(selection.getWaist());
                sizeHip.setText(selection.getHip());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        final TextView footer = (TextView) view.findViewById(R.id.item_footer);
        String unitsSelected = PrefHelper.getUnitsSelectedPreferenceString(getActivity(), unitsSelectedConstant);
        footer.setText(getString(R.string.footer_units_of_measure, unitsSelected));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        /*- add code in case the fragment modifies the action bar options menu */
        menu.findItem(R.id.action_spinner).setVisible(true);
    }

}
