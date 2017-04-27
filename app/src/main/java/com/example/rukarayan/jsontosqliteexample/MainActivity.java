package com.example.rukarayan.jsontosqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rukarayan.jsontosqliteexample.api.ContactsApi;
import com.example.rukarayan.jsontosqliteexample.database.ContactsDatabase;
import com.example.rukarayan.jsontosqliteexample.model.ContactsMainModel;
import com.example.rukarayan.jsontosqliteexample.model.ContactsModel;
import com.example.rukarayan.jsontosqliteexample.utility.Constants;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class MainActivity extends AppCompatActivity {
    private RestAdapter restAdapter;
    private OkHttpClient client;
    private ContactsApi api;
    private ContactsDatabase db;
    private ArrayList<ContactsModel> contactList = new ArrayList<ContactsModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ContactsDatabase(this.getApplicationContext());
        db.open();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();

        api = restAdapter.create(ContactsApi.class);

        api.getContactsList(new Callback<ContactsMainModel>() {
            @Override
            public void success(ContactsMainModel mainModel, Response response) {
                String name = mainModel.getContacts().get(0).getPhone().getMobile().toString();
                contactList = mainModel.getContacts();
                StartPopulatingContactsTable(contactList);
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "Failed:" + error.getMessage().toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void StartPopulatingContactsTable( ArrayList<ContactsModel> contactList ) {

        if (contactList != null && contactList.size() > 0) {
            int i = 0;
            ContactsModel cl = null;
            db.startexecution();
            while (contactList.size() > i ) {
                cl = contactList.get(i);
                if (db.CheckContactsTable(cl.getId())) {
                    db.updateContactsTable(cl);
                } else {
                    db.insertIntoContactsTable(cl);
                }
                i++;
            }
            db.startcommit();
        }
    }
}
