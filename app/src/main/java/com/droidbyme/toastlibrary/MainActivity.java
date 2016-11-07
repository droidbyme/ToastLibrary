package com.droidbyme.toastlibrary;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.droidbyme.toastlib.ToastEnum;
import com.droidbyme.toastlib.ToastLib;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ToastLib.Builder(this, "Hello").show();

        new ToastLib.Builder(this, "Login Success")
                .duration(ToastEnum.SHORT)
                .backgroundColor(ContextCompat.getColor(this, R.color.green))
                .textColor(ContextCompat.getColor(this, R.color.black))
                .textSize(18)
                .typeface(Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf"))
                .isBold(true)
                .icon(getString(R.string.fa_success))
                .icon(R.drawable.ic_check_black_24dp)
                .iconColor(ContextCompat.getColor(this, R.color.black))
                .iconSize(24, 24)
                .corner(8)
                .margin(56)
                .padding(36)
                .spacing(16)
                .stroke(2, ContextCompat.getColor(this, R.color.dark_green))
                .gravity(Gravity.BOTTOM)
                .show();

        ToastLib.success(this, "Success", Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf"));

        ToastLib.error(this, "Ooops! Try Again..", Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf"));

        ToastLib.progress(this, "Loading..", Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf"));
    }
}
