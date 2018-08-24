package com.example.artem.phrasebook.AlertDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.R;

public class AlertDialogEditWord extends DialogFragment implements View.OnClickListener {
    DatabaseHelper databaseHelper;
    String Eng, Ukr;
    View view = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());
        view = inflater.inflate(R.layout.edit_dialog, null);
        view.findViewById(R.id.button).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
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
        EditText editText = (EditText) view.findViewById(R.id.editText);
        EditText editText2 = (EditText) view.findViewById(R.id.editText2);
        Eng = editText.getText().toString();
        Ukr = editText2.getText().toString();
        switch (v.getId()) {
            case R.id.button:
                databaseHelper.editItemWord(id, Eng, Ukr);
                Log.d("Log", Eng + " " + Ukr);
                Log.d("Log", "Ok");
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