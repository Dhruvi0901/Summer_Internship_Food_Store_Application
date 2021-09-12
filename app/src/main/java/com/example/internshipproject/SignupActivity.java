package com.example.internshipproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    EditText name, email, contact, password, confirmPassword, dob;
    ImageView dobIv,showIv,hideIv,showConfirmIv,hideConfirmIv;
    Spinner spinner,stateSpinner;
    Button register;
    RadioGroup gender;
    Calendar calendar;

    String sGender, sCity, sState;

    //String[] cityArray = {"Ahmedabad","Vadodara","Surat","Rajkot"};

    ArrayList<String> arrayList;
    ArrayList<String> stateArrayList;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,10}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Signup Page");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80D2042D")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        contact = findViewById(R.id.signup_contact);
        password = findViewById(R.id.signup_password);
        confirmPassword = findViewById(R.id.signup_confirm_password);
        dob = findViewById(R.id.signup_dob);
        dobIv = findViewById(R.id.signup_calendar);
        spinner = findViewById(R.id.signup_city);
        stateSpinner = findViewById(R.id.signup_State);
        register = findViewById(R.id.signup_login);
        gender = findViewById(R.id.signup_gender);

        showIv = findViewById(R.id.signup_show_iv);
        hideIv = findViewById(R.id.signup_hide_iv);
        showConfirmIv = findViewById(R.id.signup_confirm_show_iv);
        hideConfirmIv = findViewById(R.id.signup_confirm_hide_iv);


        hideConfirmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideConfirmIv.setVisibility(View.GONE);
                showConfirmIv.setVisibility(View.VISIBLE);
                confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        showConfirmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideConfirmIv.setVisibility(View.VISIBLE);
                showConfirmIv.setVisibility(View.GONE);
                confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        hideIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideIv.setVisibility(View.GONE);
                showIv.setVisibility(View.VISIBLE);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        showIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideIv.setVisibility(View.VISIBLE);
                showIv.setVisibility(View.GONE);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });


        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = gender.getCheckedRadioButtonId(); //R.id.sigup_transgender
                RadioButton rb = findViewById(id);
                sGender = rb.getText().toString();
                new CommonMethod(SignupActivity.this, sGender);
            }
        });
        calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                dob.setText(dateFormat.format(calendar.getTime()));
            }
        };

        dobIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(SignupActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
            }
        });
        stateArrayList = new ArrayList<>();
        stateArrayList.add("Gujarat");
        stateArrayList.add("Maharashtra");
        stateArrayList.add("Rajasthan");

        ArrayAdapter stateAdapter = new ArrayAdapter(SignupActivity.this, R.layout.my_selected_item, stateArrayList);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        stateSpinner.setAdapter(stateAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int i, long l) {
                sState = stateArrayList.get(i);
                new CommonMethod(SignupActivity.this, sState);
                setCityData(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("Please Enter Your Name");
                } else if (email.getText().toString().trim().equalsIgnoreCase("")) {
                    email.setError("Please Enter Your Email Id");
                } else if (!email.getText().toString().matches(emailPattern)) {
                    email.setError("Please Enter Valid Email Id");
                } else if (contact.getText().toString().trim().equalsIgnoreCase("")) {
                    contact.setError("Please Enter Contact No.");
                } else if (password.getText().toString().trim().equalsIgnoreCase("")) {
                    password.setError("Password Required");
                } else if (!password.getText().toString().matches(passwordPattern)) {
                    password.setError("Strong Password Required");
                } else if (confirmPassword.getText().toString().trim().equalsIgnoreCase("")) {
                    confirmPassword.setError("Confirm Password Required");
                } else if (!confirmPassword.getText().toString().matches(password.getText().toString())) {
                    confirmPassword.setError("Password Does Not Match");
                } else if (dob.getText().toString().trim().equalsIgnoreCase("")) {
                    dob.setError("Date Of Birth Required");
                } else if (gender.getCheckedRadioButtonId() == -1) {
                    new CommonMethod(SignupActivity.this, "Please Select Gender");
                } else {
                /*new CommonMethod(SignupActivity.this,"Signup Successfully");
                //onBackPressed();
                new CommonMethod(SignupActivity.this,LoginActivity.class);*/
                    if (new ConnectionDetector(SignupActivity.this).isConnectingToInternet()) {
                        new signupData().execute();
                    } else {
                        new ConnectionDetector(SignupActivity.this).connectiondetect();
                    }
                }
            }
        });


    }

    private void setCityData(int i) {
        arrayList = new ArrayList<>();
        if(i==0) {
            arrayList.add("Ahmedabad");
            arrayList.add("Vadodara");
            arrayList.add("Gandhinagar");
            arrayList.add("Anand");
            arrayList.add("Test");
            arrayList.add("Bhavnagar");

            arrayList.set(0, "Ahmedabad");
            arrayList.remove(4);
        }
        if(i==1){
            arrayList.add("Mumbai");
            arrayList.add("Pune");
        }
        if(i==2){
            arrayList.add("Kota"); 
        }
        ArrayAdapter adapter = new ArrayAdapter(SignupActivity.this,R.layout.my_selected_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String s = cityArray[i];
                //String s = adapterView.getItemAtPosition(i)+"";
                //((TextView)adapterView.getChildAt(i)).setTextColor(Color.BLACK);
                sCity =arrayList.get(i);
                new CommonMethod(SignupActivity.this,sCity);
            }
            
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private class signupData extends AsyncTask<String, String, String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(SignupActivity.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("username", name.getText().toString());
            hashMap.put("email", email.getText().toString());
            hashMap.put("contact", contact.getText().toString());
            hashMap.put("password", password.getText().toString());
            hashMap.put("dob", dob.getText().toString());
            hashMap.put("gender", sGender);
            hashMap.put("city", sCity);
            hashMap.put("state", sState);
            return new MakeServiceCall().MakeServiceCall(ConstantUrl.URL + "signup.php",MakeServiceCall.POST, hashMap);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();

            try {
                JSONObject object = new JSONObject(s);
                if (object.getBoolean("Status") == true){
                    new CommonMethod(SignupActivity.this, object.getString("Message"));
                    onBackPressed();
                }
                else{
                    new CommonMethod(SignupActivity.this, object.getString("Message"));
                }
            }

            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}



