package com.example.artem.phrasebook.AlertDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.R;

public class AlertDialogDeleteWord extends DialogFragment implements View.OnClickListener {
    public DatabaseHelper databaseHelper;
    public AlertDialogDeleteWord() {
        super();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getActivity());
        View view = inflater.inflate(R.layout.delete_dialog, null);
        view.findViewById(R.id.delete).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        return view;
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
            case R.id.delete:
                databaseHelper.deleteWordById(id);
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
