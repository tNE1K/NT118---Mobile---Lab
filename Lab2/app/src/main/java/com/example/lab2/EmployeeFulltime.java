package com.example.lab2;

public class EmployeeFulltime extends Employee {
    public EmployeeFulltime(String id, String name) {
        super(id, name);
    }

    @Override
    public double TinhLuong() {
        return 500.0;
    }

    @Override
    public String ToString() {
        return super.ToString()+" --> FullTime = " + TinhLuong();
    }
}
