package com.example.lab3_1_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "schoolManager";
    private static final String TABLE_NAME = "students";
    private static final int DATABASE_VERSION = 2;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CLASS = "class";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_student_table = "CREATE TABLE " +
                TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CLASS + " TEXT" + ")";
        db.execSQL(create_student_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues myValues = new ContentValues();
        myValues.put(KEY_ID, student.getMssv());
        myValues.put(KEY_NAME, student.getFullName());
        myValues.put(KEY_CLASS, student.getMaLop());
        db.insert(TABLE_NAME, null, myValues);
        db.close();
    }

    public Student getStudent(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, null, "id = ?", new String[]{id}, null, null, null);
        if(c != null)
            c.moveToFirst();
        Student student = new Student(c.getString(0), c.getString(1), c.getString(2));
        db.close();
        return student;

    }
    public List<Student> getAllStudent() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Student> ls = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        c.moveToFirst();
        while(c.isAfterLast() == false) {
            Student student = new Student(c.getString(0), c.getString(1), c.getString(2));
            ls.add(student);
            c.moveToNext();
        }
        db.close();
        return ls;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_NAME, student.getFullName());
        value.put(KEY_CLASS, student.getMaLop());
        db.update(TABLE_NAME, value, "id = ?", new String[]{student.getMssv()});
        db.close();
    }

    public void deleteStudent(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{id});
        db.close();
    }

    public void deleteAllStudent(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
