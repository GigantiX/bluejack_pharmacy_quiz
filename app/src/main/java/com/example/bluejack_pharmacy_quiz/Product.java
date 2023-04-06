package com.example.bluejack_pharmacy_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Product extends AppCompatActivity {
    private Button addtocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        addtocart = (Button) findViewById(R.id.product_addToCart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Product.this, Cart.class));
            }
        });
    }
}