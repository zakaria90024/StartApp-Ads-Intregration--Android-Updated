package com.copypasteit.handwriting.startapp_ads_intregration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.startapp.sdk.adsbase.StartAppAd;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }
}
