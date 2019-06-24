package com.example.artem.phrasebook.Adapter;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.artem.phrasebook.Item.RecyclerTransitionItem;
import com.example.artem.phrasebook.Fragment.PhraseBookFragment;
import com.example.artem.phrasebook.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerTransitionAdapter extends RecyclerView.Adapter<RecyclerTransitionAdapter.ViewHolder>{
    private List<RecyclerTransitionItem> listItems;
    private Activity activity;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    public RecyclerTransitionAdapter(List<RecyclerTransitionItem> listItems, Activity activity, FragmentTransaction fragmentTransaction) {
        this.listItems = listItems;
        this.activity = activity;
        this.fragmentTransaction = fragmentTransaction;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_transition_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int id = (listItems.get(position).getId());
        holder.btnTransition.setText(listItems.get(position).getName());
        holder.btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.btnTransition:
                                fragment = new PhraseBookFragment().newInstance(id);
                                fragmentTransaction.replace(R.id.containerView, fragment).addToBackStack(null).commit();
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
        @BindView(R.id.btnTransition)
        public Button btnTransition;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
