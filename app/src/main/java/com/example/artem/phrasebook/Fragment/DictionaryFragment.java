package com.example.artem.phrasebook.Fragment;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.artem.phrasebook.Database.DatabaseHelper;
import com.example.artem.phrasebook.R;

public class DictionaryFragment extends Fragment {
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private EditText editText;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dictionary_layout,null);
        editText = (EditText) v.findViewById(R.id.search_edit);
        listView = (ListView) v.findViewById(R.id.listView);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            databaseHelper = new DatabaseHelper(getActivity());
            databaseHelper.openDatabase();
            String[] headers = new String[]{"phrase", "translate"};
            adapter = new SimpleCursorAdapter(getContext(), android.R.layout.two_line_list_item,
                    cursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
            editText.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s.toString());
                }
            });

            adapter.setFilterQueryProvider(new FilterQueryProvider() {
                @Override
                public Cursor runQuery(CharSequence constraint) {
                    if (constraint == null || constraint.length() == 0) {
                        return databaseHelper.database.rawQuery("select * from " + "phrasebook" + " where " + "id_theme = 14 and " +
                                "phrase" + " like ?", new String[]{"%" + constraint.toString() + "%"} );
                    }
                    else {
                        return databaseHelper.database.rawQuery("select * from " + "phrasebook" + " where " + "id_theme = 14 and " +
                                "phrase" + " like ?", new String[]{"%" + constraint.toString() + "%"} );
                    }
                }
            });
            listView.setAdapter(adapter);
        }
        catch (SQLException ex){}
    }
}