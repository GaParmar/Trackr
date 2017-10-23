package com.gaparmar.traq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityInfo extends AppCompatActivity {

    Button button5;
    Button timeBut;
    Button locBut;

    String name;
    String loc;
    String time;
    String titleColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        loc = i.getStringExtra("loc");
        time = i.getStringExtra("time");
        titleColor = i.getStringExtra("color");

        button5 = (Button)findViewById(R.id.button5);
        button5.setText(name);
        timeBut = (Button)findViewById(R.id.timeBut);
        timeBut.setText((time));
        locBut = (Button)findViewById(R.id.locBut);
        locBut.setText((loc));

        button5.setBackgroundColor(Color.parseColor(titleColor));

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (ActivityInfo.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
