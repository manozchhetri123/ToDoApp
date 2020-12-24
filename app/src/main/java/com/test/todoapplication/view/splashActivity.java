package com.test.todoapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.test.todoapplication.R;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences=getApplicationContext().getSharedPreferences("todo_app",0);
                Boolean authentication=preferences.getBoolean("authentication", false);
                if (authentication)
                {
                    Intent i=new Intent(splashActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(splashActivity.this, loginActivity.class);
                    startActivity(i);
                }

                finish();
            }
        }, 4000);
    }
}
