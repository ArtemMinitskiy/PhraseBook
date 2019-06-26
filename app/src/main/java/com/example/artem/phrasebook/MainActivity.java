package com.example.artem.phrasebook;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.artem.phrasebook.AlertDialog.AlertDialogPhrase;
import com.example.artem.phrasebook.AlertDialog.AlertDialogSE;
import com.example.artem.phrasebook.AlertDialog.AlertDialogWord;
import com.example.artem.phrasebook.Fragment.DictionaryFragment;
import com.example.artem.phrasebook.Fragment.PhraseBookThemeFragment;
import com.example.artem.phrasebook.Fragment.TabFragment;
import com.example.artem.phrasebook.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navLayout)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    DialogFragment dialogWord;
    DialogFragment dialogPhrase;
    DialogFragment dialogSE;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mRef;

    private TextView userEmail, userFullName;
    private ImageView userImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();

        dialogWord = new AlertDialogWord();
        dialogPhrase = new AlertDialogPhrase();
        dialogSE = new AlertDialogSE();

        View navHeaderView = mNavigationView.getHeaderView(0);

        userEmail = (TextView) navHeaderView.findViewById(R.id.userEmail);
        userFullName = (TextView) navHeaderView.findViewById(R.id.userName);
        userImage = (ImageView) navHeaderView.findViewById(R.id.userImageView);

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    getAccountDetails();
                }
                else {
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                }
            }
        };

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_item_dictionary) {
                    FragmentTransaction dfragmentTransaction = mFragmentManager.beginTransaction();
                    dfragmentTransaction.replace(R.id.containerView,new DictionaryFragment()).addToBackStack(null).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_phrasebook) {
                    FragmentTransaction pbfragmentTransaction = mFragmentManager.beginTransaction();
                    pbfragmentTransaction.replace(R.id.containerView,new PhraseBookThemeFragment()).addToBackStack(null).commit();
                }

                if (menuItem.getItemId() == R.id.nav_main) {
                    FragmentTransaction tfragmentTransaction = mFragmentManager.beginTransaction();
                    tfragmentTransaction.replace(R.id.containerView,new TabFragment()).addToBackStack(null).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    mAuth.signOut();
                }

                return false;
            }

        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    private void getAccountDetails() {
        FirebaseDatabase.getInstance().getReference("Users").child(mFirebaseUser.getEmail().replace(".", ","))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Users users = dataSnapshot.getValue(Users.class);
                        Glide.with(MainActivity.this).load(mFirebaseUser.getPhotoUrl()).into(userImage);
                        userFullName.setText(users.getUser());
                        userEmail.setText(users.getEmail());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                dialogWord.show(getFragmentManager(), " ");
                break;
            case R.id.fab2:
                dialogPhrase.show(getFragmentManager(), " ");
                break;
            case R.id.fab3:
                dialogSE.show(getFragmentManager(), " ");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

}