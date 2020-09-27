package com.example.officekitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    TextInputLayout email, password;
    TextView forgotPassword;
    Button login, signUp;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        Button btn = (Button)findViewById(R.id.button12);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

       /* Intent intent = new Intent(SignIn.this, MainActivity.class);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(intent);
            finish();
        }*/
        initUi();
    }
    public void initUi(){
        email = findViewById(R.id.log_email);
        password = findViewById(R.id.log_password);
        login = findViewById(R.id.loginBtn);

        forgotPassword = findViewById(R.id.forgot_password);
        signUp = findViewById(R.id.signUpText);

        forgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        });
        login.setOnClickListener(view -> {
            if (!validateForm()) {
                Toast.makeText(SignIn.this, "Make sure to fill all the required fields",
                        Toast.LENGTH_SHORT).show();
            }else{
                final Intent intent = new Intent(SignIn.this, MainActivity.class);
                progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Logging in");
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();


                String $email = String.valueOf(email.getEditText().getText());
                String $password = String.valueOf(password.getEditText().getText());

                mAuth.signInWithEmailAndPassword($email, $password)
                        .addOnCompleteListener(SignIn.this, task -> {
                            if (task.isSuccessful()) {
                                final FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignIn.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                            progressDialog.dismiss();
                        });
            }
        });

    }

    private boolean validateForm() {
        boolean valid = true;

        String $email = email.getEditText().getText().toString();
        if (TextUtils.isEmpty($email)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }


        String $password = password.getEditText().getText().toString();
        if (TextUtils.isEmpty($password)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(SignIn.this, SignIn.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(intent);
            finish();
        } else {
        }
    }

}
/*
        //Intent on the button to open the signup activity
        TextView signup = (TextView)findViewById(R.id.signUpText);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this, SignUp.class);
                startActivity(i);
            }
        });
    }*/


/*
public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView forgotPassword, signUp;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = new Intent(LoginActivity.this, OrderByLocationActivity.class);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(intent);
            finish();
        }
        initUi();

    }

    public void initUi(){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        forgotPassword = findViewById(R.id.forgot_password);
        signUp = findViewById(R.id.signUp);

        forgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });


        login.setOnClickListener(view -> {
            if (!validateForm()) {
                Toast.makeText(LoginActivity.this, "Make sure to fill all the required fields",
                        Toast.LENGTH_SHORT).show();
            }else{
                final Intent intent = new Intent(LoginActivity.this, OrderByLocationActivity.class);
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Logging in");
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();


                String $email = String.valueOf(email.getText());
                String $password = String.valueOf(password.getText());

                mAuth.signInWithEmailAndPassword($email, $password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                final FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                            progressDialog.dismiss();
                        });
            }
        });

    }

    private boolean validateForm() {
        boolean valid = true;

        String $email = email.getText().toString();
        if (TextUtils.isEmpty($email)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }


        String $password = password.getText().toString();
        if (TextUtils.isEmpty($password)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(intent);
            finish();
        } else {
        }
    }

}*/
