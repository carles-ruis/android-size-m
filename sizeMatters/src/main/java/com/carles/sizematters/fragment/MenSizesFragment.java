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

import com.carles.sizematters.R;
import com.carles.sizematters.activity.MainActivity;
import com.carles.sizematters.helper.FileHelper;
import com.carles.sizematters.model.MenSizesConversion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenSizesFragment extends Fragment {

    protected Map<String, MenSizesConversion> sizes;
    protected int rawResourceId;
    protected int layoutResourceId;
    private String unitsSelected;

    private View view;

    public MenSizesFragment() {
        this.rawResourceId = R.raw.sizes_men;
        this.layoutResourceId = R.layout.fragment_size_men;
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        InputStream is = getResources().openRawResource(rawResourceId);

        unitsSelected = ((MainActivity) activity).getApp().getUnitsSelected();
        boolean showInInches = getString(R.string.settings_units_inches).equals(unitsSelected);

        sizes = FileHelper.readMenSizeConversionResource(is, showInInches);

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
        view = inflater.inflate(layoutResourceId, container, false);

        final TextView sizeNeck = (TextView) view.findViewById(R.id.item_size_neck);
        final TextView sizeChest = (TextView) view.findViewById(R.id.item_size_chest);
        final TextView sizeSleeve = (TextView) view.findViewById(R.id.item_size_sleeve);
        final TextView sizeWaist = (TextView) view.findViewById(R.id.item_size_waist);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_sizes);
        List<String> sizeCodes = new ArrayList<String>(sizes.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sizeCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence key = ((TextView) view).getText();
                MenSizesConversion selection = sizes.get(key);
                sizeNeck.setText(selection.getNeck());
                sizeChest.setText(selection.getChest());
                sizeSleeve.setText(selection.getSleeve());
                sizeWaist.setText(selection.getWaist());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        final TextView footer = (TextView) view.findViewById(R.id.item_footer);
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
