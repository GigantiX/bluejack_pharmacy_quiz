package com.example.bluejack_pharmacy_quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePage extends AppCompatActivity {
    private Button aboutUs, logout;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RVAdapter rvAdapter;

    FloatingActionButton toCart;

    int []arr = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = findViewById(R.id.homepage_recycler);
        layoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(layoutManager);
        rvAdapter = new RVAdapter(arr);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setHasFixedSize(true);

        toCart = (FloatingActionButton) findViewById(R.id.homepage_cart);
        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, Cart.class));
            }
        });
        aboutUs = (Button) findViewById(R.id.homepage_aboutus);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, AboutUs.class));
            }
        });

        logout = (Button) findViewById(R.id.hompage_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomePage.this, MainActivity.class));
            }
        });

    }
}