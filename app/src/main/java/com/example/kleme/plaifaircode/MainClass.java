package com.example.kleme.plaifaircode;

import android.app.Activity;
import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kleme on 14.04.2017.
 */

public class MainClass extends Activity{
    Functions func;
    EditText password,text;
    LinearLayout back;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);


    }
    public void Handler (View v){
        password = (EditText)findViewById(R.id.pasw);
        text = (EditText)findViewById(R.id.text);

        switch (v.getId()){
            case R.id.subbut:
                //back = (LinearLayout)findViewById(R.id.layout);
                func = new Functions();
                func.GetPassword(password.getEditableText(),this);
                setContentView(R.layout.second);
                break;
            case R.id.endbut:
                System.exit(1);
                break;
            case R.id.trans:
                String res = func.Decode(text.getEditableText().toString());
                setContentView(R.layout.translation);
                ((TextView)findViewById(R.id.trn)).setText(res);
                break;
            case R.id.Untranslate:
                String unr = func.unDecode(text.getEditableText().toString());
                setContentView(R.layout.untranslation);
                ((TextView)findViewById(R.id.unt)).setText(unr);
                break;
            case R.id.bckbut:
                setContentView(R.layout.layout);
                break;
        }
    }
    @Override
    public void onBackPressed (){
        System.out.println("Back pressed");
        setContentView(R.layout.second);


    }
}
