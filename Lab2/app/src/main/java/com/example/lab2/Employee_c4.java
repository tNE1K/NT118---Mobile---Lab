package com.example.lab2;

public class Employee_c4 {
    public String id;
    public String fullName;
    public boolean isManager;
    //constructor
    public Employee_c4(String id, String fullName, Boolean isManager) {
        this.id = id;
        this.fullName = fullName;
        this.isManager = isManager;
    }
    //getter
    public String getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    //setter
    public void setId(String id) {
        this.id = id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isManager() {
        return isManager;
    }
}
