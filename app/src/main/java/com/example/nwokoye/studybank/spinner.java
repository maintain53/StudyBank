package com.example.nwokoye.studybank;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class spinner extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    Button choose, save;
    String label;
    Spinner select;
    EditText inputcos;
    SQLiteDatabase db;
    Databasehandler test;
    public static String course;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        choose = (Button) findViewById (R.id.register);
        save = (Button) findViewById (R.id.save);
        select = (Spinner) findViewById(R.id.spinner);
        inputcos = (EditText) findViewById (R.id.inputcos);
        choose.setOnClickListener(this);
        save.setOnClickListener(this);
        select.setOnItemSelectedListener(this);
        select.setPrompt("Select your favorite course");
        loadSpinnerData();




        }



    private void loadSpinnerData() {
        // TODO Auto-generated method stub
        Databasehandler db = new Databasehandler(getApplicationContext());
        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();
        lables.add(0,"select your course");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      select.setAdapter(dataAdapter);


     //
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Boolean testing = true;
        switch (v.getId()) {
            case R.id.register:
                save.setVisibility(View.VISIBLE);
                inputcos.setVisibility(View.VISIBLE);
                break;
            case R.id.save:
                try {
                    label = inputcos.getText().toString().trim().replaceAll(" ","_");
                    if (label.trim().length() > 0) {
                        // database handler
                        Databasehandler db = new Databasehandler(
                                getApplicationContext());
                        // inserting new label into database
                        db.insertLabel(label);

                        // making input filed text to blank
                        inputcos.setText("");
                        // Hiding the keyboard
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(inputcos.getWindowToken(), 0);
                        // loading spinner with newly added data
                        loadSpinnerData();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter course",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    testing = false;
                    if (testing) {
                        Dialog dd = new Dialog(this);
                        String error = e.toString();
                        dd.setTitle("Error");
                        TextView tv = new TextView(this);
                        tv.setText(error);
                        dd.setContentView(tv);
                        dd.show();
                    }
                } finally {
                    if (testing) {
                        AlertDialog dd = new AlertDialog.Builder(this).create();
                        TextView tv = new TextView(this);
                        if (label.trim().length() > 0) {
                            dd.setTitle("Success");
                            dd.setMessage("course sucessfully added");
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
                            dd.setMessage("no course input");
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
                    if(label.trim().length() > 0){
                    Databasehandler db = new Databasehandler(
                            getApplicationContext());
                    // inserting new label into database
                    db.Createtable(label);
                    }

                    break;

                }


        }

        }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        course = select.getSelectedItem().toString().trim();
      if(flag == position){
            return; }//do nothing}
       flag = position;
//get selected course
        //creating database table for each label of the list view
        Intent i =    new Intent (this, MainActivity.class);
        startActivity (i);
        // Showing selected spinner item

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


}






