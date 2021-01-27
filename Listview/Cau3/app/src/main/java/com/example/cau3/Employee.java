package com.example.cau3;

public abstract class Employee {
    public String id;
    public String name;

    public void setId(String emID){
        id = emID;
    }
    public void setName(String emName){
        name = emName;
    }
    public String ToString(){
        return id + " - " + name + " --> Fulltime =" + tinhLuong();
    }

    public abstract double tinhLuong();
}
