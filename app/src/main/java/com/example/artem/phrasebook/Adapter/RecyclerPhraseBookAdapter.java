package com.example.artem.phrasebook.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.artem.phrasebook.Item.RecyclerPhraseBookItem;
import com.example.artem.phrasebook.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerPhraseBookAdapter extends RecyclerView.Adapter<RecyclerPhraseBookAdapter.ViewHolder>{
    private List<RecyclerPhraseBookItem> listItems;
    private Activity activity;

    public RecyclerPhraseBookAdapter(List<RecyclerPhraseBookItem> listItems, Activity activity) {
        this.listItems = listItems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_phrasebook_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        holder.txtUkrWord.setText(listItems.get(position).getPhrase());
        holder.txtEngWord.setText(listItems.get(position).getTranslate());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ukrWord)
        public TextView txtUkrWord;
        @BindView(R.id.engWord)
        public TextView txtEngWord;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
