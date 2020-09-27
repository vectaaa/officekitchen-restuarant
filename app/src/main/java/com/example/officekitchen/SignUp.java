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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables


    TextInputLayout regName, regUserName, regEmail, regPhoneNo, regPassword, regConfirmPassword;
    Button regBtn;


    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        Button btn = (Button)findViewById(R.id.reg_login_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

        initUi();

    }

    public void initUi(){
        regName = findViewById(R.id.reg_namee);
        regUserName = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regConfirmPassword = findViewById(R.id.reg_confirm_password);
        regBtn = findViewById(R.id.reg_btn);

        regBtn.setOnClickListener(this::buildAccount);
    }
    private boolean validateForm() {
        boolean valid = true;

        String $name = regName.getEditText().getText().toString();
        if (TextUtils.isEmpty($name)) {
            regName.setError("Required.");
            valid = false;
        } else {
            regName.setError(null);
        }
        String $username = regUserName.getEditText().getText().toString();
        if (TextUtils.isEmpty($username)) {
            regUserName.setError("Required.");
            valid = false;
        } else {
            regUserName.setError(null);
        }

        String $phone = regPhoneNo.getEditText().getText().toString();
        if (TextUtils.isEmpty($phone)) {
            regPhoneNo.setError("Required.");
            valid = false;
        } else {
            regPhoneNo.setError(null);
        }

        String $email = regEmail.getEditText().getText().toString();
        if (TextUtils.isEmpty($email)) {
            regEmail.setError("Required.");
            valid = false;
        } else {
            regEmail.setError(null);
        }


        String $password = regPassword.getEditText().getText().toString();
        if (TextUtils.isEmpty($password)) {
            regPassword.setError("Required.");
            valid = false;
        } else {
            regPassword.setError(null);
        }

        String $cpassword = regConfirmPassword.getEditText().getText().toString();
        if (TextUtils.isEmpty($cpassword)) {
            regConfirmPassword.setError("Required.");
            valid = false;
        } else {
            regPassword.setError(null);
        }

        if(!($password.equals($cpassword))){
            regConfirmPassword.setError("Does not match password.");
        }

        return valid;
    }

    public void buildAccount(View view) {
        if (!validateForm()) {
            Toast.makeText(SignUp.this, "Make sure to fill all the required fields",
                    Toast.LENGTH_SHORT).show();
        } else {
            progressDialog = new ProgressDialog(SignUp.this);
            progressDialog.setMessage("Creating Account");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();

            String $email = String.valueOf(regEmail.getEditText().getText());
            String $password = String.valueOf(regPassword.getEditText().getText());

            mAuth.createUserWithEmailAndPassword($email, $password)
                    .addOnCompleteListener(SignUp.this, task -> {

                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    progressDialog.dismiss();
                                    saveData();
                                    Intent intent = new Intent(SignUp.this, SignIn.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }


                            }
                    );

        }

    }

    private void saveData() {
        /*int walletBalance = 100000;*/
        final String userSpecificId = mAuth.getCurrentUser().getUid();

        DatabaseReference userId = database.getReference().child("kitchen_users");

        regName = findViewById(R.id.reg_namee);
        regUserName = findViewById(R.id.reg_username);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
/*        regConfirmPassword = findViewById(R.id.reg_confirm_password);*/

        String $name = String.valueOf(regName.getEditText().getText());
        String $username = String.valueOf(regUserName.getEditText().getText());
        String $phoneNo = String.valueOf(regPhoneNo.getEditText().getText());
        String $email = String.valueOf(regEmail.getEditText().getText()).toLowerCase();
        String $password = String.valueOf(regPassword.getEditText().getText());
        /*String $confirmpassword = String.valueOf(regConfirmPassword.getEditText().getText());
*/
        userId.child("name").setValue($name);
        userId.child("username").setValue($username);
        userId.child("phoneNo").setValue($phoneNo);
        userId.child("email").setValue($email);
        userId.child("password").setValue($password);
        /*userId.child("confirmpassword").setValue($confirmpassword);*/
        /*userId.child("walletBalance").setValue(walletBalance);*/
    }


}
    /*//Hooks to all xml elements in the activity_sign_up.
        regName = findViewById(R.id.reg_namee);
        regUserName = findViewById(R.id.reg_username);
        regEmail =findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLogIn = findViewById(R.id.reg_login_btn);

        //Save data in firebase on button click.
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //Get all the values.
                String name = regName.getEditText().getText().toString();
                String username = regUserName.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
                reference.child(phoneNo).setValue(helperClass);
            }

        });//Register button method end.

        TextView signing = (TextView)findViewById(R.id.reg_login_btn);

        signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, SignIn.class);
                startActivity(i);
            }
        });
    }//onCreate Method end.
}
*//*TextView signing = (TextView)findViewById(R.id.reg_login_btn);

        signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, SignIn.class);
                startActivity(i);
            }
        });*/