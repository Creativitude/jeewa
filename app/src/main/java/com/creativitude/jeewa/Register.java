package com.creativitude.jeewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.creativitude.jeewa.models.User;

public class Register extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText contact_no;
    private EditText email;
    private EditText password;
    private EditText confirm_password;


    private Spinner district;
    private Spinner bg;

    private RadioGroup gender;
    private RadioButton male;

    private User user;
    private int error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_register);

        initialize();
    }

    private void initialize() {
        name = findViewById(R.id.et_reg_name);
        age = findViewById(R.id.et_reg_age);
        contact_no = findViewById(R.id.et_reg_contact);
        district = findViewById(R.id.sp_districts);
        bg = findViewById(R.id.sp_blood_groups);
        gender = findViewById(R.id.gender_group);
        male = findViewById(R.id.male);
        email = findViewById(R.id.et_reg_email);
        password = findViewById(R.id.et_reg_password);
        confirm_password = findViewById(R.id.et_reg_confirm_password);

        user = new User();
        error = 0;


    }


    public void register(View view) {
        validateInputs();


    }

    public void registerToLogin(View view) {

        startActivity(new Intent(this,Login.class));
        finish();
    }


    private void validateInputs() {

        if(!TextUtils.isEmpty(name.getText().toString())) {
            user.setName(name.getText().toString().trim());
        } else {
            name.setError(getString(R.string.empty_field));
            error++;
        }

        if(!TextUtils.isEmpty(age.getText().toString())) {
            user.setAge(age.getText().toString().trim());
        } else {
            age.setError(getString(R.string.empty_field));
            error++;
        }

        if(!TextUtils.isEmpty(contact_no.getText().toString())) {
            user.setContact_no(contact_no.getText().toString().trim());
        } else {
            contact_no.setError(getString(R.string.empty_field));
            error++;
        }

        if(!TextUtils.isEmpty(email.getText().toString())) {
            user.setEmail(email.getText().toString().trim());
        } else {
            email.setError(getString(R.string.empty_field));
            error++;
        }

        if(!TextUtils.isEmpty(password.getText().toString())) {
            user.setPassword(password.getText().toString().trim());
        } else {
            password.setError(getString(R.string.empty_field));
        }

        if(!confirm_password.getText().toString().equals(user.getPassword())) {
            confirm_password.setError(getString(R.string.mismatch));
            error++;
        }

        if(!TextUtils.isEmpty(confirm_password.getText().toString())) {
            user.setConfirm_password(confirm_password.getText().toString().trim());
        } else {
            confirm_password.setError(getString(R.string.empty_field));
            error++;
        }


        switch (gender.getCheckedRadioButtonId()) {
            case R.id.male: {
                user.setGender("Male");
                break;
            }

            case R.id.female:{
                user.setGender("Female");
                break;
            }

            default:{
                male.setError(getString(R.string.empty_field));
                error++;
                break;
            }
        }

        if(district.isSelected()) {
            user.setDistrict(district.getSelectedItem().toString());
        } else {
            error++;
            Toast.makeText(getApplicationContext(),getString(R.string.empty_field),Toast.LENGTH_SHORT).show();
        }

        if(bg.isSelected()) {
            user.setBg(bg.getSelectedItem().toString());
        } else {
            error++;
            Toast.makeText(getApplicationContext(),getString(R.string.empty_field),Toast.LENGTH_SHORT).show();
        }



    }
}
