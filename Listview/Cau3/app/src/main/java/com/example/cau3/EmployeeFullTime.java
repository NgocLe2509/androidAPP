package com.example.cau3;

public class EmployeeFullTime extends Employee {

    public EmployeeFullTime() {
        super();
    }

    @Override
    public double tinhLuong() {
        return 500.0;
    }
    @Override
    public String toString() {return id + " - " + name + " --> Fulltime =" + tinhLuong();}
}
