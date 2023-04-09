package com.example.bluejack_pharmacy_quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class PhoneLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText inputNumber, inputCode;
    private Button sendCode, verifyMyCode, backButtonLogin;

    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        inputNumber = findViewById(R.id.phone_NumberInput);
        inputCode = findViewById(R.id.phone_OtpInput);

        sendCode = findViewById(R.id.phone_getCode);
        verifyMyCode = findViewById(R.id.phone_verifyCode);
        backButtonLogin = findViewById(R.id.backLogin);

        mAuth = FirebaseAuth.getInstance();

        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(inputNumber.getText().toString())){
                    Toast.makeText(PhoneLogin.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }else {
                    String phone = "+62" + inputNumber.getText().toString();
                    sendVerificationCode(phone);
                }
                sendCode.setEnabled(false);
                new CountDownTimer(90000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        sendCode.setEnabled(true);
                    }
                };
            }
        });

        verifyMyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(inputCode.getText().toString())){
                    Toast.makeText(PhoneLogin.this, "Please enter a valid code", Toast.LENGTH_SHORT).show();
                }else {
                    verifyCode(inputCode.getText().toString());
                }
            }
        });

        backButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        //Checking verify code & redirect to homepage
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(PhoneLogin.this, HomePage.class));
                            finish();
                        } else {
                            Toast.makeText(PhoneLogin.this, "Invalid Verify, PLease Re-Send code", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)
                        .setTimeout(90L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBack)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks

            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            final String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                inputCode.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(PhoneLogin.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }
}