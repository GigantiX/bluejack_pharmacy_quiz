package com.example.bluejack_pharmacy_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private Button buttBack, toRegister, buttLogin;
    EditText inputEmail, inputPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.loginEmail);
        inputPassword = findViewById(R.id.loginPassword);

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
                DBHelper dbHelper = new DBHelper(login.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Query ke tabel user untuk mencari baris dengan email dan password yang cocok
                String[] columns = {"id"};
                String selection = "email=? and password=?";
                String[] selectionArgs = {email, password};
                Cursor cursor = db.query("user", columns, selection, selectionArgs, null, null, null);

                // Memeriksa apakah ada baris yang cocok
                if (cursor.moveToFirst()) {
                    // Login sukses
                    @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex("id"));
                    // Lakukan aksi selanjutnya, misalnya menavigasi ke halaman utama aplikasi
                    startActivity(new Intent(login.this,HomePage.class));
                    finish();
                } else {
                    // Login gagal, tampilkan pesan kesalahan
                    Toast.makeText(login.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                db.close();
            }
        });

    }
}