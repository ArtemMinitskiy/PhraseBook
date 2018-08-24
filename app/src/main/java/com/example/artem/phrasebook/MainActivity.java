package com.example.artem.phrasebook;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.artem.phrasebook.Adapter.TabAdapter;
import com.example.artem.phrasebook.AlertDialog.AlertDialogPhrase;
import com.example.artem.phrasebook.AlertDialog.AlertDialogSE;
import com.example.artem.phrasebook.AlertDialog.AlertDialogWord;
import com.example.artem.phrasebook.Fragment.AboutFragment;
import com.example.artem.phrasebook.Fragment.DictionaryFragment;
import com.example.artem.phrasebook.Fragment.PBFragment;
import com.example.artem.phrasebook.Fragment.TabFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;//Используеться для взаимодействия между фрагментами
    FragmentTransaction mFragmentTransaction;//Используется для транзакций (добавление, удаление, замена)
    DialogFragment dialogWord;
    DialogFragment dialogPhrase;
    DialogFragment dialogSE;
    TabAdapter adapter;

//    String word = getResources().getString(R.string.word);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogWord = new AlertDialogWord();
        dialogPhrase = new AlertDialogPhrase();
        dialogSE = new AlertDialogSE();

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */
//        getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new Communication()).addToBackStack(null).commit();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.nav_item_dictionary) {
                    FragmentTransaction dfragmentTransaction = mFragmentManager.beginTransaction();//Экземпляр класса FragmentTransaction можно получить от объекта Activity следующим образом
                    dfragmentTransaction.replace(R.id.containerView,new DictionaryFragment()).addToBackStack(null).commit();//Позволяет заменять фрагменты используя TabLayout
                }

                if (menuItem.getItemId() == R.id.nav_item_phrasebook) {//Замена фрагмента
                    FragmentTransaction pbfragmentTransaction = mFragmentManager.beginTransaction();// получаем экземпляр FragmentTransaction
                    pbfragmentTransaction.replace(R.id.containerView,new PBFragment()).addToBackStack(null).commit();// вызываем commit для совершения действий FragmentTransaction
                }

                if (menuItem.getItemId() == R.id.nav_main) {//Замена фрагмента
                    FragmentTransaction tfragmentTransaction = mFragmentManager.beginTransaction();// получаем экземпляр FragmentTransaction
                    tfragmentTransaction.replace(R.id.containerView,new TabFragment()).addToBackStack(null).commit();// вызываем commit для совершения действий FragmentTransaction
                }

                if (menuItem.getItemId() == R.id.nav_item_about) {//Замена фрагмента
                    FragmentTransaction afragmentTransaction = mFragmentManager.beginTransaction();// получаем экземпляр FragmentTransaction
                    afragmentTransaction.replace(R.id.containerView,new AboutFragment()).addToBackStack(null).commit();// вызываем commit для совершения действий FragmentTransaction
                }
                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name, R.string.app_name);//Выдвигаем панель нажатием на значок программы в панели действий ActionBar. Для этого используется класс ActionBarDrawerToggle
        mDrawerLayout.setDrawerListener(mDrawerToggle);//Для прослушки событий выдвигания и задвигания панели используется метод setDrawerListener() компонента DrawerLayout
        mDrawerToggle.syncState();//?

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