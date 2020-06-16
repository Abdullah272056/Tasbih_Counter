package com.example.tasbihcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    Context context;

    public DataBaseHelper(@Nullable Context context) {
        super (context, Constants.TABLE_NAME, null, Constants.DATABASE_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (Constants.CREATE_TABLE);
        Toast.makeText (context, " DataBase is created", Toast.LENGTH_SHORT).show ();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
    }


    public long insertData(Note note){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Constants.COLUMN_ZIKIR_NAME,note.getZikirName ());
        contentValues.put(Constants.COLUMN_ZIKIR_COUNT_VALUE,note.getCountValue ());
        contentValues.put(Constants.COLUMN_DATE,note.getTime ());
        long id=sqLiteDatabase.insert(Constants.TABLE_NAME,null,contentValues);
        return id;
    }


    public List<Note> getAllNote(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<Note> dataList = new ArrayList<> ();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+Constants.TABLE_NAME,null);
        if (cursor.moveToFirst()){
            do {
                Note note = new Note(cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Constants.COLUMN_ZIKIR_NAME)),
                        cursor.getString(cursor.getColumnIndex(Constants.COLUMN_DATE)),
                        cursor.getInt (cursor.getColumnIndex(Constants.COLUMN_ZIKIR_COUNT_VALUE)));

                dataList.add(note);
            }while (cursor.moveToNext());
        }
        return dataList;
    }
}
