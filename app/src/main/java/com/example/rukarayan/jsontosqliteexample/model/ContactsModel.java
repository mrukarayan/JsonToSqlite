package com.example.rukarayan.jsontosqliteexample.model;

import java.util.ArrayList;

/**
 * Created by rukarayan on 14-Nov-16.
 */

public class ContactsModel {
    private String id;
    private String name;
    private String email;
    private String address;
    private String gender;
    private String profile_pic;
    private PhoneModel phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public PhoneModel getPhone() {
        return phone;
    }

    public void setPhone(PhoneModel phone) {
        this.phone = phone;
    }
}
