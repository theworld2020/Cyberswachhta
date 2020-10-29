package com.protect.guide;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class MainActivity extends AppCompatActivity {
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public String name;
    private static final int DIALOG_ALERT = 10;
    SharedPreferences sharedpreferences;
    WebView wb;
    InterstitialAd mInterstitialAd;
    private Button button, camera;
    private RadioRealButton bt1, bt2, bt3;
    private RadioRealButtonGroup group1;
    private AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showDialog(DIALOG_ALERT);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(mypreference,
                MainActivity.MODE_PRIVATE);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        bt1 = (RadioRealButton) findViewById(R.id.bt1);
        bt2 = (RadioRealButton) findViewById(R.id.bt2);
        bt3 = (RadioRealButton) findViewById(R.id.bt3);

        name = sharedpreferences.getString(Name, "");
       /* findViewById(R.id.pink_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value=name;

                if (value.equals("yes")) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                            MainActivity.this);
                    // Setting Dialog Title
                    alertDialog.setTitle(" ");
                    // Setting Dialog Message
                    alertDialog.setMessage("Congratulations.!!! \n\n Open Phone Settings-> Google-> Verify Apps.\n\n Be Safe with Google Play Protect");
                    // Setting Icon to Dialog
                    // alertDialog.setIcon(R.drawable.ban);
                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("Okay",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {


                                }

                            });
                    alertDialog.show();

                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                            MainActivity.this);
                    // Setting Dialog Title
                    alertDialog.setTitle(" ");
                    // Setting Dialog Message
                    alertDialog.setMessage("Rate 5* to enable Google Lens");
                    // Setting Icon to Dialog
                    // alertDialog.setIcon(R.drawable.ban);
                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("No Thanks",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {


                                }

                            });
                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("Let me try!",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {


                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(Name, "yes");

                                    editor.commit();
                                    finish();
                                    Uri uri = Uri
                                            .parse("market://details?id=com.protect.guide");
                                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                    try {
                                        MainActivity.this.startActivity(goToMarket);
                                    } catch (ActivityNotFoundException e) {
                                        MainActivity.this.startActivity(new Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse("https://play.google.com/store/apps/details?id=com.protect.guide")));
                                    }

                                }
                            });
                    // Showing Alert Message
                    alertDialog.show();
                }
            }


        });*/


       /* bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showIntrest();
                wb.loadUrl("file:///android_asset/Lens/announcement.html");
                wb.getSettings().setBuiltInZoomControls(true);

            }
        });
        bt2.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showIntrest();
                        wb.loadUrl("file:///android_asset/Lens/Searchbox.html");
                        wb.getSettings().setBuiltInZoomControls(true);
                    }
                });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showIntrest();
                wb.loadUrl("file:///android_asset/Lens/intro.html");
                wb.getSettings().setBuiltInZoomControls(true);
            }
        });*/
        button = (Button) findViewById(R.id.button);

        group1 = (RadioRealButtonGroup) findViewById(R.id.radioRealButtonGroup);

        // button.setTransformationMethod(null);

        // updateText(group1.getPosition());

        wb = (WebView) this.findViewById(R.id.webView);
        wb.setWebViewClient(new MyBrowser());
        wb.loadUrl("file:///android_asset/Protect/intro.html");


        group1.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {

            @Override

            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {

                updateText(currentPosition);
                //   Toast.makeText(MainActivity.this, "Long Clicked! Position: " + currentPosition, Toast.LENGTH_SHORT).show();

                if(currentPosition==0){
                    showIntrest();
                    wb.loadUrl("file:///android_asset/Protect/intro.html");
                    wb.getSettings().setBuiltInZoomControls(true);
                }
                if(currentPosition==1){
                    showIntrest();
                    wb.loadUrl("file:///android_asset/Protect/about.html");
                    wb.getSettings().setBuiltInZoomControls(true);
                }
                if(currentPosition==2){
                    showIntrest();
                    wb.loadUrl("file:///android_asset/Protect/howtoget.html");
                    wb.getSettings().setBuiltInZoomControls(true);
                }

            }

        });


        group1.setOnLongClickedButtonListener(new RadioRealButtonGroup.OnLongClickedButtonListener() {

            @Override

            public boolean onLongClickedButton(RadioRealButton button, int position) {

                Toast.makeText(MainActivity.this, "Long Clicked! Position: " + position, Toast.LENGTH_SHORT).show();


                return false;

            }

        });


        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                int position = group1.getPosition();

                position = ++position % group1.getNumberOfButtons();

                group1.setPosition(position);
            }
        });


    }

    private void updateText(int position) {
        button.setText("Position: " + position);
    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    public void showIntrest() {
        // Load ads into Interstitial Ads
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

  @Override
    public void onBackPressed() {
        // Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle(" ");
        // Setting Dialog Message
        alertDialog.setMessage("Want to update your phone?");
        // Setting Icon to Dialog
        // alertDialog.setIcon(R.drawable.ban);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("No Thanks",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                    }

                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Let me check!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        Uri uri = Uri
                                .parse("market://details?id=com.updates.system.update.android.Play.store.ex");
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        try {
                            MainActivity.this.startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            MainActivity.this.startActivity(new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.updates.system.update.android.Play.store.ex")));
                        }

                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView browser, String urlStrOne) {
            browser.loadUrl(urlStrOne);
            return true;
        }
    }
}





















