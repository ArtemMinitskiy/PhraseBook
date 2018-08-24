package com.example.artem.phrasebook.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static final String DATABASE_NAME = "PhraseBook.sqlite";
    private static final int DATABASE_VERSION = 3;
    public SQLiteDatabase database;
    private Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        try {
            //получаем путь до БД вместе с именем.
            DB_PATH = myContext.getDatabasePath(DATABASE_NAME).toString();
            checkAndCopyDatabase();
            openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void checkAndCopyDatabase() throws IOException {

        boolean dbExist = checkDataBase();

        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try {

            checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {
            // база не существует
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLException {
        database = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if(database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor QueryData(String query){
        return database.rawQuery(query,null);
    }

    public void addItemWord(String Eng, String Ukr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phrase", Eng);
        contentValues.put("translate", Ukr);
        contentValues.put("id_theme", "1");
        database.insert("phrasebook", null, contentValues);
    }

    public boolean deleteWordById(int id) {
        openDatabase();
        int result = database.delete("phrasebook", "_id = ?", new String[]{String.valueOf(id)});
        close();
        return result !=0;
    }

    public void editItemWord(int id, String Eng, String Ukr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phrase", Eng);
        contentValues.put("translate", Ukr);
        database.update("phrasebook", contentValues, "_id = ?", new String[]{String.valueOf(id)});
    }

    public void addItemPhrase(String Eng, String Ukr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phrase", Eng);
        contentValues.put("translate", Ukr);
        contentValues.put("id_theme", "2");
        database.insert("phrasebook", null, contentValues);
    }

    public void addItemSE(String Eng, String Ukr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phrase", Eng);
        contentValues.put("translate", Ukr);
        contentValues.put("id_theme", "3");
        database.insert("phrasebook", null, contentValues);
    }

}
