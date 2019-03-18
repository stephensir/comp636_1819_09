package com.stephensir.namecard;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /* <!-- NameCard --> */
    // Properties
    private static final String TAG = "MainActivity===>";
    private TextView information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Screen management
        // Orientation Sensor
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Init
        initToolbars();
        setImageView();

    }

    // setup header & footer toolbar
    private void initToolbars() {
        Log.d(TAG,"initToolbars()");
        android.support.v7.widget.Toolbar header, footer;

        // header
        header = findViewById(R.id.header);
        header.setBackgroundColor(Color.DKGRAY);
        header.setTitleTextColor(Color.YELLOW);
        header.setTitle(R.string.name);
        header.inflateMenu(R.menu.menu_header);

        // footer
        footer = findViewById(R.id.footer);
        footer.setBackgroundColor(Color.DKGRAY);
        footer.setTitle("");
        footer.inflateMenu(R.menu.menu_footer);

    } //initToolbars()

    private void setImageView(){
        Log.d(TAG,"setImageView()");
        // get screen dimension
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels/2;
        int width = metrics.widthPixels/2;

        ImageView imageView = findViewById(R.id.imageView);

        imageView.getLayoutParams().width = width;
        imageView.getLayoutParams().height = width;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    } //setImageView()

    public void menu_translate_click(MenuItem m){
        Log.d(TAG,"menu_translate_click()");
        setLocale();
    }

    // Change language
    private void setLocale(){
        Log.d(TAG,"setLocale()");
        String languageToLoad;
        final Resources resources = getResources();
        final Configuration configuration = resources.getConfiguration();

        Log.d(TAG,"configuration.locale="+configuration.locale);
        if (configuration.locale.equals(new Locale("en_us"))){
            Log.d(TAG,"configuration.locale="+configuration.locale+"->zh");
            languageToLoad = "zh";
        } else {
            Log.d(TAG,"configuration.locale="+configuration.locale+"->en_us");
            languageToLoad = "en_us";
        }

        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    } //setLocale()

    public void menu_call_click(MenuItem m){
        Log.d(TAG,"menu_call_click()");
        doCall();
    }
    private void doCall(){
        Log.d(TAG,"doCall()");

        Toast.makeText(this,"Call...",Toast.LENGTH_SHORT).show();
        // android.permission.CALL_PHONE permission required
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:+12345678"));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this,"No telephone!",Toast.LENGTH_SHORT).show();
        }
    }

    public void menu_email_click(MenuItem m){
        Log.d(TAG,"menu_email_click()");
        doEmail();
    }
    private void doEmail(){
        Log.d(TAG,"doEmail()");

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "StephenSir@gmail.com,svt_ngwt@mymail.hkct.edu.hk");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Help");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this,"No email app!",Toast.LENGTH_SHORT).show();
        }

    }

    public void menu_map_click(MenuItem m){
        Log.d(TAG,"menu_map_click()");
        doMap();
    }
    private void doMap(){
        Log.d(TAG,"doMap()");
        // android.permission.INTERNET permission required
        Uri uri = Uri.parse("geo:22.293564,114.16952?z=19");
        //Uri uri = Uri.parse("geo:22.293564,114.16952?q=Tsim+Sha+Tsui");
        //Uri uri = Uri.parse("google.streetview:cbll=22.293564,114.16952");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this,"No Google Map!",Toast.LENGTH_SHORT).show();
        }
    }

    public void menu_home_click(MenuItem m){
        Log.d(TAG,"");
        doHome();
    }
    public void doHome(){
        Log.d(TAG,"doHome()");

        // android.permission.INTERNET permission required
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://stephenngsir.wordpress.com"));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this,"No Browser!",Toast.LENGTH_SHORT).show();
        }
    }
    /* <!-- NameCard --> */
}
