package com.gaparmar.traq;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by rtmurase on 10/21/17.
 */

public class Pop extends Activity {
    Button closeBut;
    EditText phoneNum;
    EditText secWord;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.7), (int)(height * 0.4));

        closeBut = (Button)findViewById(R.id.closeBut);
        phoneNum = (EditText)findViewById(R.id.phoneNum);
        secWord = (EditText)findViewById(R.id.secWord);


        phoneNum.setText("" + DataHolder.getInstance().getPhoneNum());
        secWord.setText(DataHolder.getInstance().getSecretWord());

        closeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getInstance().updateNumandWord(phoneNum.getText().toString(), secWord.getText().toString());
                new SmsSender(secWord.getText().toString());
                Pop.super.onBackPressed();
            }
        });
    }
}
