package com.copypasteit.handwriting.startapp_ads_intregration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.VideoListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.ads.splash.SplashConfig;

public class MainActivity extends AppCompatActivity  {

    //STEP 1  FOR REWARD VIDEO =========================================
    public StartAppAd rewardedVideo = new StartAppAd(this);
    //END STEP 1  FOR REWARD VIDEO =====================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // NOTE always use test ads during development and testing
        StartAppSDK.setTestAdsEnabled(BuildConfig.DEBUG);

        //for init ads suru hotei dekabe and return ads
        //StartAppSDK.init(this, "208887547", false);
        //end for init ads suru hotei dekabe

        //splash ads start
        StartAppAd.showSplash(this, savedInstanceState,
                new SplashConfig()
                        .setTheme(SplashConfig.Theme.GLOOMY)
                        .setAppName("TestAds")
                        .setLogo(R.drawable.ic_launcher_background)   // resource ID
                        .setOrientation(SplashConfig.Orientation.PORTRAIT)
        );
        //splash ads end

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //STEP 2  FOR REWARD VIDEO =====================================
        rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO);
        //END STEP 2  FOR REWARD VIDEO =================================

    }

    //STEP 3  FOR REWARD VIDEO =========================================
    public void showRewardedVideo() {
        //final StartAppAd rewardedVideo = new StartAppAd(this);

        rewardedVideo.setVideoListener(new VideoListener() {
            @Override
            public void onVideoCompleted() {
                Toast.makeText(getApplicationContext(), "Grant the reward to user", Toast.LENGTH_SHORT).show();
            }
        });

        rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
                rewardedVideo.showAd();
            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {
                Toast.makeText(getApplicationContext(), "Can't show rewarded video", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //END STEP 3  FOR REWARD VIDEO =====================================


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


        //STEP 4  FOR REWARD VIDEO =====================================
        showRewardedVideo();
        //END STEP 4 FOR REWARD VIDEO ==================================
    }
    

    public void HomeActivityClicked(View view) {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
