package com.example.officekitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class FlashScreen extends AppCompatActivity {

  public static int SPLASH_TIME_OUT = 4000;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flash_screen);


//Splashscreen Timer
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(FlashScreen.this, SignUp.class);
        startActivity(intent);
        finish();
      }
    }, SPLASH_TIME_OUT);
  }}