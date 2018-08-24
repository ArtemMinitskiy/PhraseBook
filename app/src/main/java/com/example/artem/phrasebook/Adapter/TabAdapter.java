package com.example.artem.phrasebook.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.artem.phrasebook.Fragment.PhraseFragment;
import com.example.artem.phrasebook.Fragment.SEFragment;
import com.example.artem.phrasebook.Fragment.WordFragment;


public class TabAdapter extends FragmentPagerAdapter {
    private static int int_items = 3 ;
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0 : return new WordFragment();
            case 1 : return new PhraseFragment();
            case 2 : return new SEFragment();
        }
        return null;
    }


    @Override
    public int getCount() {
        return int_items;

    }

    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Слова";
            case 1 :
                return "Фрази";
            case 2 :
                return "Стійкі Вирази";
        }
        return null;
    }
}