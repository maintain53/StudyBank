package com.example.nwokoye.studybank;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class question extends AppCompatActivity {
    public static String dquiz;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        String tittl = spinner.course;
        ab.setTitle(tittl);


        final EditText quest = (EditText) findViewById (R.id.quest);
        Button saveq = (Button) findViewById (R.id.save);
        saveq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean testing = true;
                String quiz = quest.getText().toString();
                // TODO Auto-generated method stub
                // Gets the data repository in write mode
                try {

                    if (quiz.trim().length() > 0) {
                        // database handler
                        Databasehandler db = new Databasehandler(
                                getApplicationContext());
                        // inserting new label into database
                        db.insertquiz(quiz);
                        // making input filed text to blank
                        quest.setText("");
                        // loading spinner with newly added data
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter question",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    testing = false;
                    if (testing) {
                        Dialog dd = new Dialog(question.this);
                        String error = e.toString();
                        dd.setTitle("Error");
                        TextView tv = new TextView(getApplicationContext());
                        tv.setText(error);
                        dd.setContentView(tv);
                        dd.show();
                    }
                } finally {
                    if (testing) {
                        AlertDialog dd = new AlertDialog.Builder(question.this).create();
                        if (quiz.trim().length() > 0) {
                            dd.setTitle("Success");
                            dd.setMessage("quiz updated");
                            dd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                public void onClick
                                        (DialogInterface dd, int which) {
                                    dd.dismiss();
                                    return;

                                }
                            });
                            dd.show();
                        } else {
                            dd.setTitle("warning");
                            dd.setMessage("no question input");
                            dd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                public void onClick
                                        (DialogInterface dd, int which) {
                                    dd.dismiss();
                                    return;
                                }
                            });
                            dd.show();
                        }
                    }
                }}});}}


