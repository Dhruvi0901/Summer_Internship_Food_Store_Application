package com.example.internshipproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Locale;

import java.util.Objects;
import ProductList.ProductList;

import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView,rv;

    String[] productNameArray = {"Cauliflower","Tomato","Apple","Watermelon","Green Chilli","Red Chilli","Almond","Cashew Nuts","Gulab jamun","KjuKatri"};
    int[] productImageArray = {R.drawable.coliflower,R.drawable.tomato,R.drawable.apple,R.drawable.watermelon,R.drawable.green_chilli,R.drawable.red_chilli,R.drawable.almond,R.drawable.cashew_nuts,R.drawable.gulab_jamun,R.drawable.kaju_katri};

    String[] categoryNameArray = {"Vegetables","Fruits","Berries","Chilli","DryFruits","Sweets","Spices","Biscuits"};
    int[] categoryImageArray = {R.drawable.vegetables,R.drawable.fruits,R.drawable.berries,R.drawable.chilli,R.drawable.dry_fruits,R.drawable.sweets,R.drawable.spices,R.drawable.buiscuits};

    String[] productPrice = {"40","60","70","100","40","50","100","50","100","200"};
    String[] Unit = {"500 GM","1 KG","250 GM","2 KG","250 GM","250 GM","250 GM","100 GM","100 GM","250 GM"};

    ArrayList<CategoryList> arrayCategoryList;
    ArrayList<ProductList> arrayProductList;

   SearchView searchView;

  //EditText searchEdit;
   CategoryAdapter adapter;
    ProductAdapter productAdapter;
    //  AutoCompleteTextView autoCompleteTextView;
   //ArrayList<String> categoryNameArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      Objects.requireNonNull(getSupportActionBar()).setTitle("FoodStuff");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(resourcecolor));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80D2042D")));
     //   Objects.requireNonNull(getSupportActionBar()).hide();

    /* searchEdit = findViewById(R.id.home_search);

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().trim().equalsIgnoreCase("")){
                    adapter.filter("");
                }
                else{
                    adapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        searchView = findViewById(R.id.home_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.trim().equalsIgnoreCase("")){
                    adapter.filter("");
                    productAdapter.filter("");
                }
                else{
                    adapter.filter(newText);
                    productAdapter.filter(newText);
                }
                return false;
            }
        });

        rv = findViewById(R.id.home_product);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        rv.setItemAnimator(new DefaultItemAnimator());

        recyclerView = findViewById(R.id.home_category);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayProductList = new ArrayList<>();
        //productNameArrayList = new ArrayList<>();
        for(int i=0;i<productNameArray.length;i++){
            ProductList list = new ProductList();
            list.setProductName(productNameArray[i]);
            list.setProductImage(productImageArray[i]);
            list.setPrice(productPrice[i]);
            list.setUnit(Unit[i]);
            arrayProductList.add(list);
            //  categoryNameArrayList.add(categoryNameArray[i]);

        }

        arrayCategoryList = new ArrayList<>();
      // categoryNameArrayList = new ArrayList<>();
        for(int i=0;i<categoryNameArray.length;i++){
            CategoryList list = new CategoryList();
            list.setCategoryName(categoryNameArray[i]);
            list.setCategoryImage(categoryImageArray[i]);
            arrayCategoryList.add(list);
        //  categoryNameArrayList.add(categoryNameArray[i]);

        }

       adapter = new CategoryAdapter(HomeActivity.this, arrayCategoryList);

        productAdapter = new ProductAdapter(HomeActivity.this, arrayProductList);

       // CategoryAdapter adapter = new CategoryAdapter(HomeActivity.this,categoryNameArray,categoryImageArray);
        recyclerView.setAdapter(adapter);
        rv.setAdapter(productAdapter);
    /*    autoCompleteTextView = findViewById(R.id.home_search);
        ArrayAdapter autoAdapter = new ArrayAdapter(HomeActivity.this, android.R.layout.simple_list_item_1,categoryNameArrayList);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(autoAdapter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finishAffinity();
        }
        if (id==R.id.shopping_bag){
            new CommonMethod(HomeActivity.this,ShoppingActivity.class);
        }
        if (id == R.id.menu_message) {
            new CommonMethod(HomeActivity.this, msgActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        //super.onBackPressed();
    }
   private static class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

        Context context;
        ArrayList<CategoryList> arrayCategoryList;
        ArrayList<CategoryList> searchList;

        public CategoryAdapter(HomeActivity homeActivity, ArrayList<CategoryList> arrayCategoryList) {
            this.context = homeActivity;
            this.arrayCategoryList = arrayCategoryList;
            searchList = new ArrayList<>();
            searchList.addAll(arrayCategoryList);
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);
            return new MyHolder(view);
        }

        public void filter(String s) {
            s = s.toLowerCase(Locale.getDefault());
            arrayCategoryList.clear();
            if(s.length()==0){
                arrayCategoryList.addAll(searchList);
            }
            else{
                for(CategoryList cat : searchList){
                    if(cat.getCategoryName().toLowerCase(Locale.getDefault()).contains(s)){
                        arrayCategoryList.add(cat);
                    }
                }
            }
            notifyDataSetChanged();

        }
        public static class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView name;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.custom_name);
                imageView = itemView.findViewById(R.id.custom_image);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.name.setText(arrayCategoryList.get(position).getCategoryName());
            holder.imageView.setImageResource(arrayCategoryList.get(position).getCategoryImage());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CommonMethod(context,ProductActivity.class);
                }
            });
        }
        @Override
        public int getItemCount() {
            return arrayCategoryList.size();
        }

    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {

        Context context1;
        ArrayList<ProductList> arrayProductList;
        ArrayList<ProductList> searchList;

        public ProductAdapter(HomeActivity homeActivity, ArrayList<ProductList> arrayProductList) {
            this.context1 = homeActivity;
            this.arrayProductList = arrayProductList;
           searchList = new ArrayList<>();
          searchList.addAll(arrayProductList);
        }

        @NonNull
        @Override
        public ProductAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product,parent,false);
            return new ProductAdapter.Holder(view);
        }

        public void filter(String s) {
            s = s.toLowerCase(Locale.getDefault());
            arrayProductList.clear();
            if (s.length()==0){
                arrayProductList.addAll(searchList);
            }
            else {
                for (ProductList cat: searchList){
                    if (cat.getProductName().toLowerCase(Locale.getDefault()).contains(s)){
                        arrayProductList.add(cat);
                    }
                }
            }
            notifyDataSetChanged();
        }
                public class Holder extends RecyclerView.ViewHolder {

            ImageView imageView1;
            TextView name1,price;

            public Holder(@NonNull View itemView) {
                super(itemView);
                name1 = itemView.findViewById(R.id.custom_name1);
                imageView1 = itemView.findViewById(R.id.custom_image1);
                price = itemView.findViewById(R.id.custom_price);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder1, int position) {
            holder1.name1.setText(arrayProductList.get(position).getProductName());
            holder1.imageView1.setImageResource(arrayProductList.get(position).getProductImage());
            holder1.price.setText((getResources().getString(R.string.price_symbol)+arrayProductList.get(position).getPrice()+"/"+arrayProductList.get(position).getUnit()));
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                Intent intent = new Intent(context1,Product_detailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",arrayProductList.get(position).getProductName());
                bundle.putString("price",getResources().getString(R.string.price_symbol) + arrayProductList.get(position).getPrice() + "/" + arrayProductList.get(position).getUnit());
                bundle.putInt("image",arrayProductList.get(position).getImage());
                intent.putExtras(bundle);
                context1.startActivity(intent);
            }
        });
    }

        @Override
        public int getItemCount() {
            return arrayProductList.size();
        }

    }

   /* private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

        Context context;
        String[] categoryNameArray;
        int[] categoryImageArray;

        public CategoryAdapter(HomeActivity homeActivity, String[] categoryNameArray, int[] categoryImageArray) {
            this.context = homeActivity;
            this.categoryNameArray = categoryNameArray;
            this.categoryImageArray = categoryImageArray;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);
            return new MyHolder(view);
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView name;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.custom_name);
                imageView = itemView.findViewById(R.id.custom_image);
            }

        }
        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.name.setText(categoryNameArray[position]);
            holder.imageView.setImageResource(categoryImageArray[position]);
        }

       @Override
        public int getItemCount() {
            return categoryNameArray.length;
        }

    }*/
}

