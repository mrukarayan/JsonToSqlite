package com.example.rukarayan.jsontosqliteexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.rukarayan.jsontosqliteexample.model.ContactsModel;
import com.example.rukarayan.jsontosqliteexample.table.ContactsTable;

/**
 * Created by rukarayan on 14-Nov-16.
 */

public class ContactsDatabase {

    private static final String TAG = ContactsDatabase.class.getName();
    private DatabaseHelper dbHelpher;
    private SQLiteDatabase db;
    private String version;

    public ContactsDatabase(Context context) {
        dbHelpher = new DatabaseHelper(context);
        Log.i(TAG, "DB Helpher has been created");
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        Log.i(TAG, "Database Openend");
        db = dbHelpher.getWritableDatabase();
    }

    public synchronized void close() {
        Log.i(TAG, "Database Closed");
        dbHelpher.close();
    }

    public void startexecution() {
        db.execSQL("BEGIN;");
    }

    public void startcommit() {
        db.execSQL("COMMIT;");
    }

    //>>>>>>>>>>> Everything of Department Table >>>>>>>>>>>


    public Boolean CheckContactsTable (String id){
        String sql = "SELECT COUNT(contacts_id) FROM [tblContacts] WHERE [contacts_id]='" + id
                + "' LIMIT 1;";
        Cursor cr = db.rawQuery(sql, null);
        if (cr.getCount() > 0) {
            cr.moveToFirst();
            if (cr.getInt(0) > 0) {
                return true;
            }
        }
        cr.close();
        return false;

    }
    public void deleteDept(){
        db.delete(ContactsTable.TABLE_NAME, null, null);
    }


    public boolean updateContactsTable(ContactsModel d){
        String where = "contacts_id='" + d.getId() + "'";

        ContentValues cv = new ContentValues();
        cv.put(ContactsTable.NAME, d.getName());
        cv.put(ContactsTable.EMAIL, d.getEmail());
        cv.put(ContactsTable.ADDRESS, d.getAddress());
        cv.put(ContactsTable.GENDER, d.getGender());
        cv.put(ContactsTable.PROFILE_PIC, d.getProfile_pic());


        long result = db.update(ContactsTable.TABLE_NAME, cv, where, null);
        return result > 0;
    }


    public boolean insertIntoContactsTable(ContactsModel d) {
        ContentValues cv = new ContentValues();
        cv.put(ContactsTable.CONTACTS_ID, d.getId());
        cv.put(ContactsTable.NAME, d.getName());
        cv.put(ContactsTable.EMAIL, d.getEmail());
        cv.put(ContactsTable.ADDRESS, d.getAddress());
        cv.put(ContactsTable.GENDER, d.getGender());
        cv.put(ContactsTable.PROFILE_PIC, d.getProfile_pic());

        long result = db.insert(ContactsTable.TABLE_NAME, null, cv);
        return result > 0;
    }




    public Cursor getallDataFromDept(){

        Cursor c=db.rawQuery("select * from tblContacts", null);
        Log.i(TAG, "Selected");
        return c;
    }

    public Cursor getDepartmentAll() {
        return db.rawQuery("SELECT * FROM [tblContacts];", null);
    }



}
