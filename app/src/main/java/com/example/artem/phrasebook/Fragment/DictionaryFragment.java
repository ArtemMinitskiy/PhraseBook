package com.example.artem.phrasebook.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.artem.phrasebook.Adapter.RecyclerDictionaryAdapter;
import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryFragment extends Fragment {
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.search_button)
    Button searchButton;
    private DatabaseHelper databaseHelper;
    private Context context;
    private Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dictionary_layout,null);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFromDB();
            }
        });

        return view;
    }

    private void readFromDB() {
        String searchWord = searchEdit.getText().toString();
        databaseHelper = new DatabaseHelper(getActivity());
        try {
            databaseHelper.checkAndCopyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseHelper.openDatabase();

        String[] selectionArgs = {"%" + searchWord + "%"};

        cursor = databaseHelper.database.rawQuery("select * from " + "phrasebook" + " where " + "id_theme = 14 and " +
                "phrase" + String.format(" like ?"), selectionArgs);
        recyclerView.setAdapter(new RecyclerDictionaryAdapter(context, cursor));
    }
}