package com.example.artem.phrasebook;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.artem.phrasebook.AlertDialog.AlertDialogPhrase;
import com.example.artem.phrasebook.AlertDialog.AlertDialogSE;
import com.example.artem.phrasebook.AlertDialog.AlertDialogWord;
import com.example.artem.phrasebook.Fragment.DictionaryFragment;
import com.example.artem.phrasebook.Fragment.PBFragment;
import com.example.artem.phrasebook.Fragment.TabFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    DialogFragment dialogWord;
    DialogFragment dialogPhrase;
    DialogFragment dialogSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogWord = new AlertDialogWord();
        dialogPhrase = new AlertDialogPhrase();
        dialogSE = new AlertDialogSE();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

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
                    pbfragmentTransaction.replace(R.id.containerView,new PBFragment()).addToBackStack(null).commit();
                }

                if (menuItem.getItemId() == R.id.nav_main) {
                    FragmentTransaction tfragmentTransaction = mFragmentManager.beginTransaction();
                    tfragmentTransaction.replace(R.id.containerView,new TabFragment()).addToBackStack(null).commit();
                }

                return false;
            }

        });

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

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

}