package com.sitru.SpringSecurityDemo.Model;

import lombok.*;


public class Student {
    private int id;
    private String Name;
    private String marker;

    public Student(int id, String name, String marker) {
        this.id = id;
        Name = name;
        this.marker = marker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
}
