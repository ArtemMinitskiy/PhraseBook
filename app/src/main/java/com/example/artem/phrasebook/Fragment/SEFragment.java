package com.example.artem.phrasebook.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class SEFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference sEReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public Context context;

    private View view;
    private LinearLayoutManager layoutManager;

    private String currentUserId;

    public SEFragment() {
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
        FirebaseRecyclerOptions<Word> options =
                new FirebaseRecyclerOptions.Builder<Word>()
                        .setQuery(sEReference, Word.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Word, ViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder holder, int position, @NonNull final Word word) {
                final String wordId = getRef(position).getKey();
                sEReference.child(wordId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ukrPhrase = dataSnapshot.child("ukrSE").getValue().toString();
                        String engPhrase = dataSnapshot.child("engSE").getValue().toString();
                        holder.txtEng.setText(engPhrase);
                        holder.txtUkr.setText(ukrPhrase);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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
