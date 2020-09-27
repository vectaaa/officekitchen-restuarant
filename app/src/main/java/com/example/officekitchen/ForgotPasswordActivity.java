package com.example.officekitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputLayout forEmail;
    Button send;

    public ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        initUi();

    }

    public void initUi(){
        forEmail = (TextInputLayout) findViewById(R.id.forg_password_email);
        send = (Button) findViewById(R.id.send);

        mAuth = FirebaseAuth.getInstance();

     send.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String $email = String.valueOf(forEmail.getEditText().getText());
            if(TextUtils.isEmpty($email)){
                Toast.makeText(ForgotPasswordActivity.this, "Please enter your email!", Toast.LENGTH_SHORT);
            }else{
                progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
                progressDialog.setMessage("Creating Account");
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();

                FirebaseAuth.getInstance().sendPasswordResetEmail($email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Email Status", "Email sent.");
                                    progressDialog.dismiss();
                                    Toast.makeText(ForgotPasswordActivity.this, "Email sent successfully!", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(ForgotPasswordActivity.this, "Invalid email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    });
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(ForgotPasswordActivity.this, SignIn.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

/*
public class ForgotPasswordActivity extends AppCompatActivity {
    EditText email;
    Button send;

    public ProgressDialog progressDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUi();

    }

    public void initUi(){
        email = (EditText) findViewById(R.id.email);
        send = (Button) findViewById(R.id.send);

        mAuth = FirebaseAuth.getInstance();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String $email = String.valueOf(email.getText());
                if(TextUtils.isEmpty($email)){
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your email!", Toast.LENGTH_SHORT);
                }else{
                    progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
                    progressDialog.setMessage("Creating Account");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();

                    FirebaseAuth.getInstance().sendPasswordResetEmail($email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Email Status", "Email sent.");
                                        progressDialog.dismiss();
                                        Toast.makeText(ForgotPasswordActivity.this, "Email sent successfully!", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(ForgotPasswordActivity.this, "Invalid email!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}*/
