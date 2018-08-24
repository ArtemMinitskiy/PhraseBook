package com.example.artem.phrasebook.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.artem.phrasebook.Adapter.RecyclerTransitionAdapter;
import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.Item.RecyclerTransitionItem;
import com.example.artem.phrasebook.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PBFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerTransitionAdapter adapter;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private List<RecyclerTransitionItem> listItems;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pb_layout,null);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listItems = new ArrayList<>();
        adapter = new RecyclerTransitionAdapter(listItems, getActivity());
        recyclerView.setAdapter(adapter);
        loadDatabase();
        return v;
    }

    public void loadDatabase(){
        databaseHelper = new DatabaseHelper(getActivity());
        try {
            databaseHelper.checkAndCopyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseHelper.openDatabase();
        cursor = databaseHelper.QueryData("select * from theme");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    RecyclerTransitionItem item = new RecyclerTransitionItem(cursor.getInt(0), cursor.getString(1));
                    listItems.add(item);
                } while (cursor.moveToNext());
            }
        }

    }

}