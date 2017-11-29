package com.example.nwokoye.studybank;

/**
 * Created by NWOKOYE on 6/19/2017.
 */



    import java.util.ArrayList;
    import java.util.List;
    import java.util.Locale;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.SQLException;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;

public class Databasehandler  extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "spinnerExample";

    // Labels table name
    private static final String TABLE_LABELS = "labels";

    // Labels Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NOTE = "notes";
    private static final String KEY_QUIZES = "quiz";
    Cursor cursor;

    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_LABELS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT )";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABELS);
        // Create tables again
        onCreate(db);


    }


    public String Createtable(String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + course);
        // Category table create
       // db.execSQL("DROP TABLE IF EXISTS " + course);
        String sql = "CREATE TABLE " + course  + "("
                + KEY_NOTE + " TEXT, " + KEY_QUIZES + " TEXT)";
        db.execSQL(sql);
        return sql;

    }


    /**
     * Inserting new labels into labels table
     */
    public void insertLabel(String label) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put (KEY_NAME,"NULL");
        values.put(KEY_NAME, label);
        // Inserting Row
        db.insert(TABLE_LABELS, null, values);
        db.close();
    }// Closing database connection

    // inserting quiz in the database

    public  long insertquiz(String quiz){
        String coursename=spinner.course;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUIZES, quiz);
        // Inserting Row
       return db.insert(coursename, null, values);


    }

    //inserting note to the database
    public long insertnote(String note) {
        String coursename = spinner.course;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, note);
        // Inserting Row
        return db.insert(coursename, null, values);

    }

    /**
     * Getting all labels
     * returns list of labels
     */
    public List<String> getAllLabels() {
        List<String> labels = new ArrayList<String>();


        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LABELS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());

        }


        // closing connection
        cursor.close();
        db.close();

        // returning labels
        return labels;
    }

    // selecting the quiz
    public List<String> getAllquestions() {
        List<String> quiz = new ArrayList<String>();
        String coursename = spinner.course;
        // Select All Query

        String selectQuery = "SELECT " + KEY_QUIZES + " FROM " + coursename + " WHERE " + KEY_QUIZES + " IS NOT NULL " ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if(cursor!=null && cursor.getCount() > 0){
            if (cursor.moveToFirst()) {
                do {
                    quiz.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }}
            cursor.close();
        db.close();
        // return category list
        return quiz;}
// reading notes
    public List<String> getAllnote () {
        List<String> note = new ArrayList<String>();
        String coursename = spinner.course;
        // Select All Query

        String selectQuery = "SELECT " + KEY_NOTE + " FROM " + coursename + " WHERE " + KEY_NOTE + " IS NOT NULL " ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if(cursor!=null && cursor.getCount() > 0){
            if (cursor.moveToFirst()) {
                do {
                    note.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }}
        cursor.close();
        db.close();
        // return category list
        return note;}


    // delecting a course
    public void deletecourse (){
        String coursename = spinner.course;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + coursename);
        db.execSQL("DELETE FROM " + TABLE_LABELS + " WHERE " +KEY_NAME + " = '" + coursename + "'");
        db.close();
    }

    // delecting quiz in a row

    public void deletequiz(){
        String delquiz = quiz.delquiz;
        String coursename = spinner.course;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + coursename + " WHERE " +KEY_QUIZES + " = '" + delquiz + "'");
        db.close();

    }

    public void deletenote() {
        String coursename = spinner.course;
        String delnote = vnote.vnote;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + coursename + " WHERE " +KEY_NOTE + " = '" + delnote + "'");
        db.close();
    }

    }









