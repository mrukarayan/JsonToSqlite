package com.example.rukarayan.jsontosqliteexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.example.rukarayan.jsontosqliteexample.table.ContactsTable;

/**
 * Created by rukarayan on 14-Nov-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getName();
    private Context context;
    public static final String DATABASE_NAME = "Contacts.db";
    private static final int DATABASE_VERSION = 1;


    // Create Student Table

    private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE "
            + ContactsTable.TABLE_NAME + " ( " + ContactsTable.CONTACTS_ID
            + " TEXT NOT NULL, "
            + ContactsTable.NAME + " TEXT NOT NULL, "
            + ContactsTable.EMAIL + " TEXT NOT NULL, "
            + ContactsTable.ADDRESS + " TEXT NOT NULL, "
            + ContactsTable.GENDER + " TEXT NOT NULL, "
            + ContactsTable.PROFILE_PIC + " TEXT NOT NULL );";



	/*public DatabaseHelper(Context context){

		super(context, Environment.getExternalStorageDirectory().toString()
				+ "/" + "ContactsTest" + "/" + DATABASE_NAME,
				null, DATABASE_VERSION);
		this.context = context;
	}*/

    public DatabaseHelper(Context context){

        super(context, "/data/data/"+context.getPackageName()+"/databases/"+DATABASE_NAME,
                null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);
        Log.i(TAG, ContactsTable.TABLE_NAME + " has been created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactsTable.TABLE_NAME);


        onCreate(db);
    }
}
