package com.example.bluejack_pharmacy_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    private EditText inputName, inputEmail, inputPass, inputConfPass, inputPhoneNum;

    private Button buttonBack, toLogin, buttRegister;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.register_name);
        inputEmail = findViewById(R.id.register_email);
        inputPass = findViewById(R.id.register_password);
        inputConfPass = findViewById(R.id.register_conPass);
        inputPhoneNum = findViewById(R.id.register_phoNum);

        toLogin = (Button) findViewById(R.id.register_toLogin);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(register.this, login.class));
            }
        });

        buttRegister = (Button) findViewById(R.id.register_butt);
        buttRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper =new DBHelper(register.this);
                SQLiteDatabase db =dbHelper.getWritableDatabase();

                if(TextUtils.isEmpty(inputName.getText().toString()) || inputName.getText().length() < 5){
                    Toast.makeText(register.this, "Please enter your Valid Name", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(inputEmail.getText().toString()) || !inputEmail.getText().toString().endsWith(".com")){
                    Toast.makeText(register.this, "Please enter your valid Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(inputPass.getText().toString())){
                    Toast.makeText(register.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(inputConfPass.getText().toString())){
                    Toast.makeText(register.this, "Please enter your Confirm Password", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(inputPhoneNum.getText().toString())){
                    Toast.makeText(register.this, "Please enter your Phone Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    String nama = inputName.getText().toString();
                    String email = inputEmail.getText().toString();
                    String password = inputPass.getText().toString();
                    String phone = inputPhoneNum.getText().toString();

                    ContentValues values = new ContentValues();
                    values.put("nama", nama);
                    values.put("email", email);
                    values.put("password", password);
                    values.put("phone", phone);

                    long newRowId = db.insert("user", null, values);
                    db.close();
                    Toast.makeText(register.this, "Login successful, please login to continue!", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(register.this, login.class));
                }

            }
        });

        buttonBack = (Button) findViewById(R.id.backRegister);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}