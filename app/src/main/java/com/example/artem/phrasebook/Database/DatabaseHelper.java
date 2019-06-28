package com.example.artem.phrasebook.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "PhraseBook.sqlite";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    public SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    private FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("Users");

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    public Cursor QueryData(String query){
        return mDataBase.rawQuery(query,null);
    }

    public void addItemWord(String engWord, String ukrWord) {

        DatabaseReference wordRef = userReference
                .child(mFirebaseUser.getEmail().replace(".", ","))
                .child("Word")
                .push();
        final Map<String, Object> wordMap = new HashMap<>();
        wordMap.put("ukrWord", ukrWord);
        wordMap.put("engWord", engWord);
        wordRef.setValue(wordMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void addItemPhrase(String engPhrase, String ukrPhrase) {
        DatabaseReference PhraseRef = userReference
                .child(mFirebaseUser.getEmail().replace(".", ","))
                .child("Phrase")
                .push();
        final Map<String, Object> PhraseMap = new HashMap<>();
        PhraseMap.put("ukrPhrase", ukrPhrase);
        PhraseMap.put("engPhrase", engPhrase);
        PhraseRef.setValue(PhraseMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void addItemSE(String engSE, String ukrSE) {
        DatabaseReference sERef = userReference
                .child(mFirebaseUser.getEmail().replace(".", ","))
                .child("StableExpression")
                .push();
        final Map<String, Object> sEMap = new HashMap<>();
        sEMap.put("ukrSE", ukrSE);
        sEMap.put("engSE", engSE);
        sERef.setValue(sEMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void editItem(String pageId, String id, String engTxt, String ukrTxt) {
        switch (pageId){
            case "0":
                DatabaseReference wordRef = userReference
                        .child(mFirebaseUser.getEmail().replace(".", ","))
                        .child("Word")
                        .child(id);
                final Map<String, Object> wordMap = new HashMap<>();
                wordMap.put("ukrWord", ukrTxt);
                wordMap.put("engWord", engTxt);
                wordRef.setValue(wordMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            case "1":
                DatabaseReference PhraseRef = userReference
                        .child(mFirebaseUser.getEmail().replace(".", ","))
                        .child("Phrase")
                        .child(id);
                final Map<String, Object> PhraseMap = new HashMap<>();
                PhraseMap.put("ukrPhrase", ukrTxt);
                PhraseMap.put("engPhrase", engTxt);
                PhraseRef.setValue(PhraseMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                break;
            case "2":
                DatabaseReference wordSE = userReference
                        .child(mFirebaseUser.getEmail().replace(".", ","))
                        .child("StableExpression")
                        .child(id);
                final Map<String, Object> sEMap = new HashMap<>();
                sEMap.put("ukrSE", ukrTxt);
                sEMap.put("engSE", engTxt);
                wordSE.setValue(sEMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                break;
            default:
                break;

        }

    }

    public void deleteItemById(String pageId, String id) {
        switch (pageId) {
            case "0":
                DatabaseReference wordRef = userReference
                        .child(mFirebaseUser.getEmail().replace(".", ","))
                        .child("Word")
                        .child(id);
                wordRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            case "1":
                DatabaseReference PhraseRef = userReference
                        .child(mFirebaseUser.getEmail().replace(".", ","))
                        .child("Phrase")
                        .child(id);
                PhraseRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                break;
            case "2":
                DatabaseReference wordSE = userReference
                        .child(mFirebaseUser.getEmail().replace(".", ","))
                        .child("StableExpression")
                        .child(id);
                wordSE.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                break;
            default:
                break;

        }
    }
}
