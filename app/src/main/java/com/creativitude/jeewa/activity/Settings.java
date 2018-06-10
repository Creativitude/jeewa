package com.creativitude.jeewa.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.creativitude.jeewa.R;
import com.creativitude.jeewa.helpers.Connectivity;
import com.creativitude.jeewa.helpers.SelectLanguage;
import com.creativitude.jeewa.helpers.Transitions;

public class Settings extends Drawer {

    private Connectivity connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams") View contentView = inflater.inflate(R.layout.activity_settings, null, false);
        drawerLayout.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.settings);
        connectivity = new Connectivity(this);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transitions.init(Settings.this);
        }

        LinearLayout language = findViewById(R.id.set_Language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, SelectLanguage.class));
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            finishAfterTransition();

        } else {
            overridePendingTransition(R.anim.left_in, R.anim.right_out);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.settings);
        connectivity.checkConnectionState(navigationView);


    }
}
