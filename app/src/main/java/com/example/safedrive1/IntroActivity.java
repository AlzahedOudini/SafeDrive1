package com.example.safedrive1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import Adapter.IntroViewPagerAdapter;
import model.ScreenItem;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter mIntroViewPagerAdapter;
    TabLayout mTabIndicator;
    Button mBtnNext;
    Button mBtnStart;
    Animation mBtnAnim;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(restorePrefData()){
            Intent Login = new Intent(getApplicationContext(), Login.class);
            startActivity(Login);
            finish();
        }

        setContentView(R.layout.activity_intro);

        // initialisation de la view
        mBtnNext = findViewById(R.id.btn_next);
        mBtnStart = findViewById(R.id.btn_start);
        mTabIndicator = findViewById(R.id.tabIndicator);
        mBtnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);


        // liste de slider
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem(R.drawable.pic1, "Bienvenue sur SafeDrive, votre application de gestion de vos véhicules."));
        mList.add(new ScreenItem(R.drawable.pic2, "SaveDrive vous rappelle les évènements importants liés à vos véhicules."));
        mList.add(new ScreenItem(R.drawable.pic3, "SaveDrive vous fourni un rapport complet sur les activités liées à vos véhicules."));

        screenPager = findViewById(R.id.screen_view_pager);
        mIntroViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(mIntroViewPagerAdapter);


        mTabIndicator.setupWithViewPager(screenPager);

        // next button
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if(position < mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if(position == mList.size() -1){
                    afficheNewSlide();
                }
            }
        });


        mTabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mList.size() -1){
                    afficheNewSlide();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), com.example.safedrive1.Login.class);
                startActivity(Login);
                savePrefsData();
                finish();
            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend", false);
        return isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();
    }

    private void afficheNewSlide() {
        mBtnNext.setVisibility(View.INVISIBLE);
        mTabIndicator.setVisibility(View.INVISIBLE);
        mBtnStart.setVisibility(View.VISIBLE);

        // pour l'animation

        mBtnStart.setAnimation(mBtnAnim);
    }
}
