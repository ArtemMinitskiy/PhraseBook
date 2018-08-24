package com.example.artem.phrasebook;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.artem.phrasebook.Fragment.PhraseFragment;
import com.example.artem.phrasebook.Fragment.SEFragment;
import com.example.artem.phrasebook.Fragment.WordFragment;
import com.example.artem.phrasebook.PhraseBookFragment.Communication;
import com.example.artem.phrasebook.PhraseBookFragment.Farewell;
import com.example.artem.phrasebook.PhraseBookFragment.InTheCity;
import com.example.artem.phrasebook.PhraseBookFragment.Money;
import com.example.artem.phrasebook.PhraseBookFragment.Purchases;
import com.example.artem.phrasebook.PhraseBookFragment.Restaurant;
import com.example.artem.phrasebook.PhraseBookFragment.Sensation;
import com.example.artem.phrasebook.PhraseBookFragment.Transport;
import com.example.artem.phrasebook.PhraseBookFragment.Weather;
import com.example.artem.phrasebook.PhraseBookFragment.Welcome;

public class FragmentWorkHelper {
    private final FragmentActivity activity;
    FragmentTransaction fragmentTransaction;

    public FragmentWorkHelper(FragmentActivity activity) {
        this.activity = activity;
    }

    public void replaceFragment(int id) {
        fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        switch (id) {
            case 1:
                fragmentTransaction.replace(R.id.containerView, new WordFragment()).addToBackStack(null).commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.containerView, new PhraseFragment()).addToBackStack(null).commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.containerView, new SEFragment()).addToBackStack(null).commit();
                break;
            case 4:
                fragmentTransaction.replace(R.id.containerView, new Communication()).addToBackStack(null).commit();
                break;
            case 5:
                fragmentTransaction.replace(R.id.containerView, new Welcome()).addToBackStack(null).commit();
                break;
            case 6:
                fragmentTransaction.replace(R.id.containerView, new Farewell()).addToBackStack(null).commit();
                break;
            case 7:
                fragmentTransaction.replace(R.id.containerView, new Sensation()).addToBackStack(null).commit();
                break;
            case 8:
                fragmentTransaction.replace(R.id.containerView, new Weather()).addToBackStack(null).commit();
                break;
            case 9:
                fragmentTransaction.replace(R.id.containerView, new InTheCity()).addToBackStack(null).commit();
                break;
            case 10:
                fragmentTransaction.replace(R.id.containerView, new Money()).addToBackStack(null).commit();
                break;
            case 11:
                fragmentTransaction.replace(R.id.containerView, new Purchases()).addToBackStack(null).commit();
                break;
            case 12:
                fragmentTransaction.replace(R.id.containerView, new Transport()).addToBackStack(null).commit();
                break;
            case 13:
                fragmentTransaction.replace(R.id.containerView, new Restaurant()).addToBackStack(null).commit();
                break;
            default:
                break;
        }
    }

}
