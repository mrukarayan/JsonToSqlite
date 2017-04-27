package com.example.rukarayan.jsontosqliteexample.model;

import java.util.ArrayList;

/**
 * Created by rukarayan on 15-Nov-16.
 */

public class ContactsMainModel {
    private ArrayList<ContactsModel> contacts = new ArrayList<ContactsModel>();

    public ArrayList<ContactsModel> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactsModel> contacts) {
        this.contacts = contacts;
    }
}
