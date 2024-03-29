package com.royal.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";
    public static final String TABLE_NAME = "PEOPLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    private SQLiteDatabase database;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table  in onCreate method
        db.execSQL("create table " + TABLE_NAME +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRST_NAME + " VARCHAR, "
                + COLUMN_LAST_NAME + " VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /*insert data*/
    public void insertRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();// use of Data add
        contentValues.put(COLUMN_FIRST_NAME, contact.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, contact.getLastName());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }
    /*over insert data*/

    /*Alternate method of insert */
    public void insertRecordAlternate(ContactModel contact) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO " + TABLE_NAME +
                "(" + COLUMN_FIRST_NAME + ","
                + COLUMN_LAST_NAME + ") VALUES('" + contact.getFirstName()
                + "','" + contact.getLastName() + "')");
        database.close();
    }

    /*over alternate method */
    public ArrayList<ContactModel> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,
                null, null, null, null,
                null, null);
        ArrayList<ContactModel> contactModelArrayList = new ArrayList<ContactModel>();
        if (cursor.getCount() > 0) {

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                ContactModel  contactModel = new ContactModel();
                contactModel.setID(cursor.getString(0));
                contactModel.setFirstName(cursor.getString(1));
                contactModel.setLastName(cursor.getString(2));
                contactModelArrayList.add(contactModel);
            }
        }
        cursor.close();
        database.close();
        return contactModelArrayList;
    }

    /*Select data*/
    public ArrayList<ContactModel> getAllRecordsAlternate() {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(0));
                contactModel.setFirstName(cursor.getString(1));
                contactModel.setLastName(cursor.getString(2));
                contacts.add(contactModel);
            }
        }
        cursor.close();
        database.close();
        return contacts;
    }




    /*select other query
    *
    *
    *1. Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
        KEY_NAME, KEY_IMAGE }, KEY_ID + "=?",
        new String[] { String.valueOf(id) }, null, null, null, null);
    *
    *
    *  2.  String selectQuery = "SELECT * FROM "table_name" ORDER BY "name" ";
    * 3. String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
    * 4.
    *
    * */

    public void updateRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, contact.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, contact.getLastName());
        database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{contact.getID()});
        database.close();
    }


    public void updateRecordAlternate(ContactModel contact) {
        database = this.getReadableDatabase();
        database.execSQL("update " + TABLE_NAME + " set " + COLUMN_FIRST_NAME + " = '" +
                contact.getFirstName() + "', " + COLUMN_LAST_NAME + " = '" + contact.getLastName() +
                "' where " + COLUMN_ID + " = '" + contact.getID() + "'");
        database.close();
    }


    public void deleteRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public void deleteRecordAlternate(ContactModel contact) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME + " where " + COLUMN_ID + " = '" + contact.getID() + "'");
        database.close();
    }


    public void deleteAllRecords() {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, null, null);
        database.close();
    }

    public void deleteAllRecordsAlternate() {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME);
        database.close();
    }


    public ArrayList<String> getAllTableName() {
        database = this.getReadableDatabase();
        ArrayList<String> allTableNames = new ArrayList<String>();
        Cursor cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                allTableNames.add(cursor.getString(cursor.getColumnIndex("name")));
            }
        }
        cursor.close();
        database.close();
        return allTableNames;
    }


// update record Data

    public void updateRecord1(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, contact.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, contact.getLastName());
        database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{contact.getID()});
        database.close();
    }

    // over Update record


}
