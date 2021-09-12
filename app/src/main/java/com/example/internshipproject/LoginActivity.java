package com.example.internshipproject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    TextView createAccount;
    Button login;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,10}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80D2042D")));
            email = findViewById(R.id.login_email);
            password = findViewById(R.id.login_password);
            createAccount = findViewById(R.id.login_create_account);
            login = findViewById(R.id.login_button);

        createAccount.setOnClickListener(view -> new CommonMethod(LoginActivity.this,SignupActivity.class));


        login.setOnClickListener(view -> {
            if (email.getText().toString().trim().equals("")){
                email.setError("Please Enter Your Email Id");
        }
            else if(!email.getText().toString().trim().matches(emailPattern)){
                email.setError("Please Enter Valid Email Id");
            }
            else if (password.getText().toString().trim().equals("")){
                password.setError("Please Enter Your Password");
            }
                else if(!password.getText().toString().trim().matches(passwordPattern)) {
                password.setError("Please Enter Strong Password");
            }
            else {
                    if(email.getText().toString().trim().equals("test@gmail.com") && password.getText().toString().trim().equalsIgnoreCase("test@123")) {
                        System.out.println("Login Successfully");
                        Log.d("LOGIN", "Login Successfilly");
                        Log.e("LOGIN", "Login Successfully");


                        //Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                        new CommonMethod(LoginActivity.this, "Login Successfully");
                        //Snackbar.make(view,"Login Successfully", Snackbar.LENGTH_LONG).show();
                        new CommonMethod(view, "Login Successfully");
                /*Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);*/
                        new CommonMethod(LoginActivity.this, HomeActivity.class);
                    }
                        else{
                            new CommonMethod(LoginActivity.this, "Login Unsuccessfully");
                            new CommonMethod(view,"Login Unsuccessfully");
                        }
                    }
            });
        }
                @Override
                public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    if(id==android.R.id.home){
                        //onBackPressed();
                        alertMethod();
                    }
                    return super.onOptionsItemSelected(item);

    }
    private void alertMethod() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Quit Alert!!!");
        builder.setIcon(R.mipmap.ic_launcher_dialog);
        builder.setMessage("Do You Really Want To Close The Application?");

        builder.setPositiveButton(Html.fromHtml("<font color='#F31101'>Cancle</font><b>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(Html.fromHtml("<font color='#238A28'>Ok</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        builder.setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new CommonMethod(LoginActivity.this,"Rate Us");
                dialogInterface.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
        
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        alertMethod();
    }

}