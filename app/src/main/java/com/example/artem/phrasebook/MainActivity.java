package com.example.artem.phrasebook;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.artem.phrasebook.AlertDialog.AlertDialogPhrase;
import com.example.artem.phrasebook.AlertDialog.AlertDialogSE;
import com.example.artem.phrasebook.AlertDialog.AlertDialogWord;
import com.example.artem.phrasebook.Fragment.DictionaryFragment;
import com.example.artem.phrasebook.Fragment.PBFragment;
import com.example.artem.phrasebook.Fragment.TabFragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dialogWord = new AlertDialogWord();
        dialogPhrase = new AlertDialogPhrase();
        dialogSE = new AlertDialogSE();

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