package com.halal.triz.trizna;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                //Menyelesaikan Splashscreen
                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub}}, splashInterval);
            }
        }, 2000);

    }
}
