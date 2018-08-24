package com.example.artem.phrasebook.Adapter;


import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.artem.phrasebook.FragmentWorkHelper;
import com.example.artem.phrasebook.Item.RecyclerTransitionItem;
import com.example.artem.phrasebook.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerTransitionAdapter extends RecyclerView.Adapter<RecyclerTransitionAdapter.ViewHolder>{
    private List<RecyclerTransitionItem> listItems;
    private Activity activity;

    public RecyclerTransitionAdapter(List<RecyclerTransitionItem> listItems, Activity activity) {
        this.listItems = listItems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_transition_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final FragmentWorkHelper helper = new FragmentWorkHelper((FragmentActivity) activity);
        final int id = (listItems.get(position).getId());
        final String s1 = (listItems.get(position).getName());
        holder.button.setText(listItems.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.buttonT:
                                helper.replaceFragment(id);
                                break;
                            default:
                                break;
                        }
                    }
            });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.buttonT)
        public Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
