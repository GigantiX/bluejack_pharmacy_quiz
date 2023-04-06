package com.example.bluejack_pharmacy_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {
    private Button buttBack, toRegister, buttLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttBack = (Button) findViewById(R.id.backLogin);
        buttBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toRegister = (Button) findViewById(R.id.login_toRegister);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(login.this, register.class));
            }
        });

        buttLogin = (Button) findViewById(R.id.butt_login);
        buttLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                finish();
                startActivity(new Intent(login.this, HomePage.class));
            }
        });

    }
}