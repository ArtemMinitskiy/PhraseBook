package com.example.artem.phrasebook.AlertDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AlertDialogEditWord extends DialogFragment implements View.OnClickListener {
    public DatabaseHelper databaseHelper;
    private String Eng, Ukr;
    private EditText editEng, editUkr;

    private DatabaseReference wordReference;
    private FirebaseUser firebaseUser;

    private View view = null;

    private String itemId, pageId;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        itemId = getArguments().getString("itemId");
        pageId = getArguments().getString("pageId");

        databaseHelper = new DatabaseHelper(getActivity());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        wordReference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(firebaseUser.getEmail().replace(".", ","));
        wordReference.keepSynced(true);

        view = inflater.inflate(R.layout.edit_dialog, null);
        view.findViewById(R.id.edit).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        editEng = (EditText) view.findViewById(R.id.editEng);
        editUkr = (EditText) view.findViewById(R.id.editUkr);

        displayDialogEditText(pageId);

        return view;
    }

    private void displayDialogEditText(final String pageId) {
        switch (pageId){
            case "0":
                wordReference.child("Word").child(itemId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        editEng.setText(dataSnapshot.child("engWord").getValue().toString());
                        editUkr.setText(dataSnapshot.child("ukrWord").getValue().toString());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                break;
            case "1":
                wordReference.child("Phrase").child(itemId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        editEng.setText(dataSnapshot.child("engPhrase").getValue().toString());
                        editUkr.setText(dataSnapshot.child("ukrPhrase").getValue().toString());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                break;
            case "2":
                wordReference.child("StableExpression").child(itemId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        editEng.setText(dataSnapshot.child("engSE").getValue().toString());
                        editUkr.setText(dataSnapshot.child("ukrSE").getValue().toString());

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                break;
            default:break;
        }


    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    public static AlertDialogEditWord newInstance(String itemId, String pageId){
        AlertDialogEditWord alertDialogEdit = new AlertDialogEditWord();
        Bundle args = new Bundle();
        args.putString("itemId", itemId);
        args.putString("pageId", pageId);
        alertDialogEdit.setArguments(args);
        return alertDialogEdit;
    }

    public void onClick(View v) {
        Eng = editEng.getText().toString();
        Ukr = editUkr.getText().toString();
        switch (v.getId()) {
            case R.id.edit:
                databaseHelper.editItem(pageId, itemId, Eng, Ukr);
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