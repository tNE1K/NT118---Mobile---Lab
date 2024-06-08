package com.example.lab3_12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NUM = "phone_number";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " +
                TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NUM + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues myValues = new ContentValues();
        myValues.put(KEY_NAME, contact.getName());
        myValues.put(KEY_PH_NUM, contact.getPhoneNumber());
        db.insert(TABLE_CONTACTS, null, myValues);
        db.close();
    }
    // Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_CONTACTS, null, "id = ?", new String[]{Integer.toString(id)}, null, null, null);
        if(c != null)
            c.moveToFirst();
        Contact contact = new Contact(c.getInt(0), c.getString(1), c.getString(2));
        db.close();
        return contact;
    }
    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> ls = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_CONTACTS, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Contact contact = new Contact(c.getInt(0), c.getString(1), c.getString(2));
            ls.add(contact);
            c.moveToNext();
        }
        db.close();
        return ls;
    }
    // Updating single contact
    public int updateContact(Contact contact) {
        return 0;
    }
    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);
        db.close();
    }
}
