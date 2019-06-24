package com.example.artem.phrasebook.Fragment;

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

import com.example.artem.phrasebook.Adapter.RecyclerAdapter;
import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.Item.RecyclerItem;
import com.example.artem.phrasebook.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhraseFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private List<RecyclerItem> listItems;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phrase_layout,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listItems = new ArrayList<>();
        adapter = new RecyclerAdapter(getActivity(), listItems);
        recyclerView.setAdapter(adapter);
        loadDatabase();
        return view;
    }

    public void loadDatabase(){
        databaseHelper = new DatabaseHelper(getActivity());
//        try {
//            databaseHelper.checkAndCopyDatabase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        databaseHelper.openDatabase();
        databaseHelper.openDataBase();
        cursor = databaseHelper.QueryData("select * from phrasebook where id_theme = 2");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    RecyclerItem item = new RecyclerItem(cursor.getInt(0), cursor.getString(2), cursor.getString(1));
                    listItems.add(item);
                } while (cursor.moveToNext());
            }
        }

    }
}