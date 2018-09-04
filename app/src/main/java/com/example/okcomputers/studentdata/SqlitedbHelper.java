package com.example.okcomputers.studentdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OK Computers on 3/20/2018.
 */

public class SqlitedbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentData";
    private static final int DATABASE_VERSION = 2;
    private static final String STUDENT_TABLE = "thestudent";
    public static final String KEY_ROWID = "id";
    public static final String KEY_ROWFIRST_NAME = "firstname";
    public static final String KEY_ROWLAST_NAME = "lastname";
    SQLiteDatabase db;
    public SqlitedbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase dbs) {

        String CREATE_STUDENT_TABLE =" CREATE TABLE " + STUDENT_TABLE + "(" +KEY_ROWID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_ROWFIRST_NAME+
                " TEXT, " +KEY_ROWLAST_NAME+ " TEXT);";
        dbs.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
    }

    public long InsertData(String fname, String lname) {
      db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ROWFIRST_NAME,fname);
        values.put(KEY_ROWLAST_NAME,lname);
        return db.insert(STUDENT_TABLE,null,values);
    }

    public String getData() {
     db = this.getReadableDatabase();
     String[] column = new String[]{KEY_ROWID,KEY_ROWFIRST_NAME,KEY_ROWLAST_NAME};
        Cursor c = db.query(STUDENT_TABLE,column,null,null,null,null,null);
        String result = "";
        int iRowId = c.getColumnIndex(KEY_ROWID);
        int iRowFName = c.getColumnIndex(KEY_ROWFIRST_NAME);
        int iROWLName = c.getColumnIndex(KEY_ROWLAST_NAME);

        for(c.moveToFirst() ; !c.isAfterLast() ; c.moveToNext())
        {
           result = result + c.getString(iRowId) + " " +  c.getString(iRowFName) + " " + c.getString(iROWLName) + "\n";
        }
        return result;
    }

    public String getStudentFirstName(long l) {
        db = this.getReadableDatabase();
        String[] column = new String[]{KEY_ROWID,KEY_ROWFIRST_NAME,KEY_ROWLAST_NAME};
        Cursor c = db.query(STUDENT_TABLE,column,KEY_ROWID +"="+ l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;
    }

    public String getStudentLastName(long l) {
        db = this.getReadableDatabase();
        String[] column = new String[]{KEY_ROWID,KEY_ROWFIRST_NAME,KEY_ROWLAST_NAME};
        Cursor c = db.query(STUDENT_TABLE,column,KEY_ROWID +"="+ 1,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String name = c.getString(2);
            return name;
        }
        return null;
    }

    public void updateStudent(long l, String fname, String lname) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWFIRST_NAME,fname);
        cv.put(KEY_ROWLAST_NAME,lname);
        db.update(STUDENT_TABLE,cv,KEY_ROWID +"= " + l, null);
        db.close();
    }

    public void DeleteStudent(long l) {
        db = this.getWritableDatabase();
        db.delete(STUDENT_TABLE,KEY_ROWID+ "="+l,null);
    }
}
