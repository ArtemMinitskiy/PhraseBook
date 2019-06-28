package com.example.artem.phrasebook.Fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import com.example.artem.phrasebook.Model.StableExpression;
import com.example.artem.phrasebook.Model.Word;
import com.example.artem.phrasebook.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StableExpressionFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference sEReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public Context context;

    private View view;
    private LinearLayoutManager layoutManager;

    private String currentUserId;

    public StableExpressionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.se_layout,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUserId = firebaseAuth.getCurrentUser().getUid();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        sEReference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(firebaseUser.getEmail().replace(".", ",")).child("StableExpression");

        sEReference.keepSynced(true);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<StableExpression> options =
                new FirebaseRecyclerOptions.Builder<StableExpression>()
                        .setQuery(sEReference, StableExpression.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<StableExpression, ViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder holder, int position, @NonNull final StableExpression expression) {
                final String wordId = getRef(position).getKey();
                sEReference.child(wordId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        holder.txtEng.setText(expression.getEngSE());
                        holder.txtUkr.setText(expression.getUkrSE());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                holder.Option.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final DialogFragment deleteDialog = new AlertDialogDeleteWord().newInstance(wordId, "2");
                        final DialogFragment editDialog = new AlertDialogEditWord().newInstance(wordId, "2");
                        PopupMenu popupMenu = new PopupMenu(getContext(), holder.Option);
                        popupMenu.inflate(R.menu.optionmenu);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.edit:
                                        editDialog.show(((AppCompatActivity) getContext()).getFragmentManager(), "");
                                        break;
                                    case R.id.delete:
                                        deleteDialog.show(((AppCompatActivity) getContext()).getFragmentManager(), "");
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
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
                return new ViewHolder(view);
            }

        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtEng, txtUkr, Option;
        public ViewHolder(View itemView) {
            super(itemView);
            txtEng = (TextView) itemView.findViewById(R.id.txtEng);
            txtUkr = (TextView) itemView.findViewById(R.id.txtUkr);
            Option = (TextView) itemView.findViewById(R.id.txtOption);

        }

    }

}
