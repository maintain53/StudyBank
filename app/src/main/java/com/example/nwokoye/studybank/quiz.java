package com.example.nwokoye.studybank;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class quiz extends AppCompatActivity {
    ListView quizlist;
    public static String delquiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        String tittle = spinner.course;
        ab.setTitle(tittle);
        loadquizData();
    }


    private void loadquizData() {
        // TODO Auto-generated method stub
        Databasehandler db = new Databasehandler(getApplicationContext());
        final ListView quizlist = (ListView) findViewById(R.id.listnote);
        // question elements
        List<String> quiz = db.getAllquestions();
        // Creating adapter for quiz
        try {
            final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.notetext, quiz);
            quizlist.setAdapter(dataAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        quizlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = adapterView.getItemAtPosition(i);
                delquiz = o.toString().trim();
                deletequiz();
                return true;
            }
        });
    }

    public void deletequiz() {
        final AlertDialog dd = new AlertDialog.Builder(quiz.this).create();
        dd.setTitle("DELETE QUIZ");
        dd.setMessage("ARE YOU SURE ");
        dd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick
                    (DialogInterface dd, int which) {
                Databasehandler db = new Databasehandler(getApplicationContext());
                db.deletequiz();

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