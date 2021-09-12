package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        setTitle("Shopping Bag");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80D2042D")));
    }
}