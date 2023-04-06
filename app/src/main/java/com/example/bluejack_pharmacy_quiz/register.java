package com.example.bluejack_pharmacy_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class register extends AppCompatActivity {
    private Button buttonBack;
    private Button toLogin;
    private Button buttRegister;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
                Toast.makeText(register.this, "Login successful, please login", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(register.this, login.class));
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