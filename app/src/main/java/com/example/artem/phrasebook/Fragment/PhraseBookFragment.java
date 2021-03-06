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

import com.example.artem.phrasebook.Adapter.RecyclerPhraseBookAdapter;
import com.example.artem.phrasebook.Adapter.RecyclerTransitionAdapter;
import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.Item.RecyclerPhraseBookItem;
import com.example.artem.phrasebook.R;

import java.util.ArrayList;
import java.util.List;

public class PhraseBookFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerPhraseBookAdapter adapter;
    private RecyclerTransitionAdapter adapterT;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private List<RecyclerPhraseBookItem> listItems;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phrasebook_layout,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listItems = new ArrayList<>();
        adapter = new RecyclerPhraseBookAdapter(listItems, getActivity());
        recyclerView.setAdapter(adapter);

        loadDatabase();
        return view;
    }

    public void loadDatabase(){
        int id = getArguments().getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.openDataBase();
        cursor = databaseHelper.QueryData("select * from phrasebook where id_theme = " + id);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    RecyclerPhraseBookItem item = new RecyclerPhraseBookItem(cursor.getInt(0), cursor.getString(2), cursor.getString(1));
                    listItems.add(item);
                } while (cursor.moveToNext());
            }
        }

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    public Fragment newInstance(int id) {
        PhraseBookFragment phraseBookFragment = new PhraseBookFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        phraseBookFragment.setArguments(args);
        return phraseBookFragment;
    }
}
