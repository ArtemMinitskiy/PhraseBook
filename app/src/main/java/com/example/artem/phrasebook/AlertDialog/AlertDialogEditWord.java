package com.example.artem.phrasebook.AlertDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.R;

public class AlertDialogEditWord extends DialogFragment implements View.OnClickListener {
    public DatabaseHelper databaseHelper;
    private String Eng, Ukr;
    private EditText editEng, editUkr;
    View view = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());
        view = inflater.inflate(R.layout.edit_dialog, null);
        view.findViewById(R.id.edit).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        return view;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    public static AlertDialogEditWord newInstance(int id){
        AlertDialogEditWord alertDialogEdit = new AlertDialogEditWord();
        Bundle args = new Bundle();
        args.putInt("id", id);
        alertDialogEdit.setArguments(args);
        return alertDialogEdit;
    }

    public void onClick(View v) {
        int id = getArguments().getInt("id");
        editEng = (EditText) view.findViewById(R.id.editEng);
        editUkr = (EditText) view.findViewById(R.id.editUkr);
        Eng = editEng.getText().toString();
        Ukr = editUkr.getText().toString();
        switch (v.getId()) {
            case R.id.edit:
                databaseHelper.editItem(id, Eng, Ukr);
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