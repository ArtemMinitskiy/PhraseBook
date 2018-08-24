package com.example.artem.phrasebook.PhraseBookFragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.artem.phrasebook.Adapter.RecyclerPhraseBookAdapter;
import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.Item.RecyclerPhraseBookItem;
import com.example.artem.phrasebook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 22.02.2017.
 */
public class Transport extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerPhraseBookAdapter adapter;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private List<RecyclerPhraseBookItem> listItems;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.phrasebook_layout,null);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listItems = new ArrayList<>();
        adapter = new RecyclerPhraseBookAdapter(listItems, getActivity());
        recyclerView.setAdapter(adapter);
        loadDatabase();
        return v;
    }
    public void loadDatabase(){
        databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.openDatabase();
        cursor = databaseHelper.QueryData("select * from phrasebook where id_theme = 12");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    RecyclerPhraseBookItem item = new RecyclerPhraseBookItem(cursor.getInt(0), cursor.getString(2), cursor.getString(1));
                    listItems.add(item);
                } while (cursor.moveToNext());
            }
        }

    }
}