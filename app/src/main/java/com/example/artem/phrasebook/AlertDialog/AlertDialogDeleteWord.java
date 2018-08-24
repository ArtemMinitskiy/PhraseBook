package com.example.artem.phrasebook.AlertDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.R;

public class AlertDialogDeleteWord extends DialogFragment implements View.OnClickListener {
    DatabaseHelper databaseHelper;
    public AlertDialogDeleteWord() {
        super();
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());
        View v = inflater.inflate(R.layout.delete_dialog, null);
        getDialog().setTitle(getResources().getString(R.string.record));
        v.findViewById(R.id.button).setOnClickListener(this);
        v.findViewById(R.id.button2).setOnClickListener(this);
        return v;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    public static AlertDialogDeleteWord newInstance(int id){
        AlertDialogDeleteWord alertDialogDelete = new AlertDialogDeleteWord();
        Bundle args = new Bundle();
        args.putInt("id", id);
        alertDialogDelete.setArguments(args);
        return alertDialogDelete;
    }

    public void onClick(View v) {
        int id = getArguments().getInt("id");
        switch (v.getId()) {
            case R.id.button:
                databaseHelper.deleteWordById(id);

                Log.d("LogId", getArguments().getInt("id") + "");
                dismiss();
                break;
            case R.id.button2:
                Log.d("Log", "Cancel");
                dismiss();
                break;
            default:
                break;
        }
    }

}
