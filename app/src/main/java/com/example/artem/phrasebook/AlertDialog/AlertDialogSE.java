package com.example.artem.phrasebook.AlertDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.R;

public class AlertDialogSE extends DialogFragment implements View.OnClickListener{
    public DatabaseHelper databaseHelper;
    private String Eng, Ukr;
    private EditText editEng, editUkr;
    View view = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());
        view = inflater.inflate(R.layout.add_dialog, null);
        view.findViewById(R.id.add).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        editEng = (EditText) view.findViewById(R.id.editEng);
        editUkr = (EditText) view.findViewById(R.id.editUkr);
        Eng = editEng.getText().toString();
        Ukr = editUkr.getText().toString();
        switch (v.getId()) {
            case R.id.add:
                databaseHelper.addItemSE(Eng, Ukr);
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

}