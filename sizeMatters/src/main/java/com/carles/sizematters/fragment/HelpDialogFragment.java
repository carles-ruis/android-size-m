package com.carles.sizematters.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.carles.sizematters.R;

public class HelpDialogFragment extends DialogFragment {

    private static final String ARG_TITLE_ID = "titleId";
    private static final String ARG_MESSAGE_ID = "messageId";

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */

    public static HelpDialogFragment newInstance(int titleId, int messageId) {
        HelpDialogFragment ret = new HelpDialogFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_ID, titleId);
        args.putInt(ARG_MESSAGE_ID, messageId);
        ret.setArguments(args);

        return ret;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getString(getArguments().getInt(ARG_TITLE_ID));
        String message = getString(getArguments().getInt(ARG_MESSAGE_ID));

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_help, null);
        builder.setView(view);

        final Dialog alert = builder.create();

        /*- findViewById from the root view of the inflated hierarchy */
        view.findViewById(R.id.help_content).setOnClickListener(new View.OnClickListener() {
            @Override
            /*- touching inside the dialog dismisses it too */
            public void onClick(View v) {
                dismiss();
            }
        });

        ((TextView) view.findViewById(R.id.help_title)).setText(title);
        ((TextView) view.findViewById(R.id.help_message)).setText(message);

        alert.setCanceledOnTouchOutside(true);

        return alert;
    }
}
