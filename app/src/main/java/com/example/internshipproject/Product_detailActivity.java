package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class Product_detailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imageView = findViewById(R.id.product_detail_image);
        name = findViewById(R.id.product_detail_name);
        price = findViewById(R.id.product_detail_price);

        Bundle bundle = getIntent().getExtras();
        String pName = bundle.getString("name");
        String pPrice = bundle.getString("price");
        int pImage = bundle.getInt("image");

        Objects.requireNonNull(getSupportActionBar()).setTitle(pName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80D2042D")));

        name.setText(pName);
        price.setText(pPrice);
        imageView.setImageResource(pImage);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}