package com.example.nwokoye.studybank;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class vnote extends AppCompatActivity {
    public static String vnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        String tittl = spinner.course;
        ab.setTitle(tittl);
        Databasehandler db = new Databasehandler(getApplicationContext());
        final ListView notelist = (ListView) findViewById(R.id.listnote);
        // question elements
        List<String> note = db.getAllnote();

        // Creating adapter for quiz
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.notetext, note);

        notelist.setAdapter(dataAdapter);
        notelist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = adapterView.getItemAtPosition(i);
                vnote = o.toString().trim();
                deletenote();
                return true;
            }
        });


}
    public void deletenote (){
        final String coursename = spinner.course;
        final   AlertDialog dd = new AlertDialog.Builder(vnote.this).create();
        dd.setTitle("DELETE ALL NOTES");
        dd.setMessage("ARE YOU SURE");
        dd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick
                    (DialogInterface dd, int which) {
                Databasehandler db = new Databasehandler(getApplicationContext());
                db.deletenote();

            }
        });
        dd.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dd.dismiss();
                return;

            }
        });
        dd.show();}
}


