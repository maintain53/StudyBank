

package com.example.nwokoye.studybank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nwokoye.studybank.R;

public class intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
                Thread timer = new Thread(){
                    public void run(){
                        try{
                            sleep(4000);
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();}
                        finally{
                            Intent peace = new Intent ("android.intent.action.SPINNER");
                            startActivity(peace);
                        }
                    }

                };
                timer.start();}

            @Override
            protected void onPause() {
                // TODO Auto-generated method stub
                super.onPause();
                finish();
            }





        }









