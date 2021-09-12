package com.example.internshipproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import ProductList.ProductList;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ProductList> arrayList;
    ProductAdapter adapter;

    String[] productNameArray = {"Cauliflower","Tomato","Apple","Watermelon","Green Chilli","Red Chilli","Almond","Cashew Nuts","Gulab jamun","KjuKatri"};
    int[] productImageArray = {R.drawable.coliflower,R.drawable.tomato,R.drawable.apple,R.drawable.watermelon,R.drawable.green_chilli,R.drawable.red_chilli,R.drawable.almond,R.drawable.cashew_nuts,R.drawable.gulab_jamun,R.drawable.kaju_katri};
    String[] productPrice = {"40","60","70","100","40","50","100","50","100","200"};
    String[] Unit = {"500 GM","1 KG","250 GM","2 KG","250 GM","250 GM","250 GM","100 GM","100 GM","250 GM"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Products");
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80D2042D")));
        recyclerView = findViewById(R.id.product_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        for (int i = 0; i < productNameArray.length; i++) {
            ProductList list = new ProductList();
            list.setProductName(productNameArray[i]);
            list.setImage(productImageArray[i]);
            list.setPrice(productPrice[i]);
            list.setUnit(Unit[i]);
            arrayList.add(list);

    }
        adapter = new ProductAdapter(ProductActivity.this, arrayList);
        recyclerView.setAdapter(adapter);
}
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> {

        Context context;
        ArrayList<ProductList> arrayList;

        public ProductAdapter(ProductActivity productActivity, ArrayList<ProductList> arrayList) {
            this.context = productActivity;
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_horizontal, parent, false);
            return new MyHolder(view);
        }
        public class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView name, price;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.custom_product_horizontal_image);
                name = itemView.findViewById(R.id.custom_product_horizontal_name);
                price = itemView.findViewById(R.id.custom_product_horizontal_price);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.imageView.setImageResource(arrayList.get(position).getImage());
            holder.name.setText(arrayList.get(position).getProductName());
            holder.price.setText(getResources().getString(R.string.price_symbol) + arrayList.get(position).getPrice() + "/" + arrayList.get(position).getUnit());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,Product_detailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",arrayList.get(position).getProductName());
                    bundle.putString("price",getResources().getString(R.string.price_symbol) + arrayList.get(position).getPrice() + "/" + arrayList.get(position).getUnit());
                    bundle.putInt("image",arrayList.get(position).getImage());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}
