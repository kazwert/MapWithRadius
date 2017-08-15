package com.halal.triz.trizna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tutorial);
    }
}
