package com.example.rukarayan.jsontosqliteexample.api;

import com.example.rukarayan.jsontosqliteexample.model.ContactsMainModel;
import com.example.rukarayan.jsontosqliteexample.model.ContactsModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by rukarayan on 14-Nov-16.
 */

public interface ContactsApi {
    @GET("/json_data.json")
    void getContactsList(Callback <ContactsMainModel> cd);
}
