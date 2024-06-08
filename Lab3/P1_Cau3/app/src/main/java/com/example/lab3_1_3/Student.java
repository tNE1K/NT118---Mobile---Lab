package com.example.lab3_1_3;

public class Student {
    private String mssv;
    private String fullName;
    private String maLop;

    public Student()
    {

    }

    public Student(String mssv, String fullName, String maLop){
        this.mssv = mssv;
        this.fullName = fullName;
        this.maLop = maLop;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
}
