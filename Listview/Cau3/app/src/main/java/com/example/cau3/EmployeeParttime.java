package com.example.cau3;

public class EmployeeParttime extends Employee {

    @Override
        public double tinhLuong() {
        return 150.0;
    }
        @Override
        public String toString() {return id + " - " + name + " --> Parttime =" + tinhLuong();}
}
