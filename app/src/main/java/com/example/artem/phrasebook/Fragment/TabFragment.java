package com.example.artem.phrasebook.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.artem.phrasebook.Adapter.TabAdapter;
import com.example.artem.phrasebook.R;

public class TabFragment extends Fragment {

    public TabLayout tabLayout;
    public ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Чтобы привязать фрагмент к определенной разметке нужно определить в нем метод onCreateView(). Этот метод возвращает View, которому и принадлежит ваш фрагмент.
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager()));//Используя адаптер Возвращаем private FragmentManager для размещения и управления фрагментами внутри этого фрагмента.

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

}