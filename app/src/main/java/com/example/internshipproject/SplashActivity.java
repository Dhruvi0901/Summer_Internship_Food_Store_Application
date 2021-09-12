package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();

        imageView=findViewById(R.id.splash_iv);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2500);
        imageView.setAnimation(alphaAnimation);

        new Handler().postDelayed(() -> {
            Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        },3000);
    }
}