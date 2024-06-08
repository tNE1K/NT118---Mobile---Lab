package com.example.lab2;

public class EmployeeParttime extends Employee{
    public EmployeeParttime(String id, String name) {
        super(id, name);
    }

    @Override
    public double TinhLuong() {
        return 150.0;
    }

    @Override
    public String ToString() {
        return super.ToString()+" --> PartTime = "+TinhLuong();
    }
}
