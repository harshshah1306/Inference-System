package com.example.harsh.myapplication1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Firstpage extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        final Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally{

                    Intent open=new Intent(Firstpage.this,Second.class);
                    startActivity(open);
                }

            }

        };
        timer.start();

    }
}
