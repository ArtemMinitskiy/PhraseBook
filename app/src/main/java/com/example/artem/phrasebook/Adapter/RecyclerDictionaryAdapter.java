package com.example.artem.phrasebook.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.artem.phrasebook.R;

public class RecyclerDictionaryAdapter extends RecyclerView.Adapter<RecyclerDictionaryAdapter.ViewHolder>{

    Context context;
    Cursor cursor;

    public RecyclerDictionaryAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_dictionary_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.bindCursor(cursor);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView wordEng;
        TextView wordUkr;

        public ViewHolder(View itemView) {
            super(itemView);
            wordEng = (TextView) itemView.findViewById(R.id.wordEng);
            wordUkr = (TextView) itemView.findViewById(R.id.wordUkr);

        }

        public void bindCursor(Cursor cursor) {
            wordEng.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow("phrase")
            ));
            wordUkr.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow("translate")
            ));

        }
    }
}
