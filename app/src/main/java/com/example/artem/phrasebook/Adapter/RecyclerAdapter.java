package com.example.artem.phrasebook.Adapter;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.artem.phrasebook.AlertDialog.AlertDialogDeleteWord;
import com.example.artem.phrasebook.AlertDialog.AlertDialogEditWord;
import com.example.artem.phrasebook.Item.RecyclerItem;
import com.example.artem.phrasebook.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<RecyclerItem> listItems;
    private Context context;

    public RecyclerAdapter(Context context, List<RecyclerItem> listItems) {
        this.context = context;
        this.listItems = listItems;

    }
    public void dataChanged( List<RecyclerItem> list){
        listItems = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
                        final Context context = holder.itemView.getContext();
                        final int s = (listItems.get(position).getId());
                        holder.txtEng.setText(listItems.get(position).getTitle());
                        holder.txtUkr.setText(listItems.get(position).getDescription());
                        holder.Option.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Display option menu
                                final DialogFragment deleteDialog = new AlertDialogDeleteWord().newInstance(s);
                                final DialogFragment editDialog = new AlertDialogEditWord().newInstance(s);
                                PopupMenu popupMenu = new PopupMenu(context, holder.Option);
                                popupMenu.inflate(R.menu.optionmenu);
                                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        switch (item.getItemId()) {
                                            case R.id.edit:
                                                editDialog.show(((AppCompatActivity) context).getFragmentManager(), "");
//                                                Toast.makeText(context, "Edited", Toast.LENGTH_LONG).show();
//                                                notifyDataSetChanged();
//                                                notifyItemChanged(position);
                                                break;
                                            case R.id.delete:
                                                //Delete item
                                                deleteDialog.show(((AppCompatActivity) context).getFragmentManager(), "");
                                                listItems.remove(position);
                                                Log.d("Log", "" + s);
//                                                notifyItemRemoved(position);
//                                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                                                break;
                                            default:
                                                break;
                                        }
                                        return false;
                                    }
                                });
                                popupMenu.show();
                            }
                        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtEng;
        public TextView txtUkr;
        public TextView Option;
        public ViewHolder(View itemView) {
            super(itemView);
            txtEng = (TextView) itemView.findViewById(R.id.txtEng);
            txtUkr = (TextView) itemView.findViewById(R.id.txtUkr);
            Option = (TextView) itemView.findViewById(R.id.txtOption);

        }

    }

}