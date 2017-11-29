package com.example.nwokoye.studybank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    Button vnote, anote, quiz, setq, dcos, dnote, dquiz;
    Intent w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        String tittl = spinner.course;
        ab.setTitle(tittl);
        vnote = (Button) findViewById(R.id.vnote);
        anote = (Button) findViewById(R.id.note);
        setq = (Button) findViewById(R.id.question);
        dcos = (Button) findViewById(R.id.delcos);
        quiz = (Button) findViewById(R.id.quiz);
        vnote.setOnClickListener(this);
        dcos.setOnClickListener(this);
        anote.setOnClickListener(this);
        setq.setOnClickListener(this);
        quiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.quiz:
                w = new Intent("android.intent.action.QUIZ");
                startActivity(w);
                break;
            case R.id.vnote:
                w = new Intent("android.intent.action.VNOTE");
                startActivity(w);
                break;
            case R.id.note:
                w = new Intent("android.intent.action.NOTE");
                startActivity(w);

                break;
            case R.id.question:
                w = new Intent("android.intent.action.QUESTION");
                startActivity(w);
                break;
            case R.id.delcos:
                deletecos();
                break;

        }
    }
    // method to delete the cousrse from database

    public void deletecos() {
        final AlertDialog dd = new AlertDialog.Builder(MainActivity.this).create();
        dd.setTitle("DELETE COURSE");
        dd.setMessage("ARE YOU SURE");
        dd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick
                    (DialogInterface dd, int which) {
                Databasehandler db = new Databasehandler(getApplicationContext());
                db.deletecourse();
                w = new Intent(getApplicationContext(),spinner.class);
                startActivity(w);

            }
        });
        dd.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dd.dismiss();
                return;

            }
        });
        dd.show();



    }
}








