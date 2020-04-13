package com.example.safedrive1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Handler mHandler;
    Runnable mRunnable;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.imageLogo);
        mImageView.animate().alpha(4000).setDuration(0);

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(dsp);
                finish();
            }
        }, 4000);
    }
}
