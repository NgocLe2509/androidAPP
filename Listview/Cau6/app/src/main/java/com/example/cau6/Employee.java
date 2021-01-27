package com.example.cau6;

public class Employee {
    private String id;
    private String name;
    private boolean manager;

    public Employee(String id, String name, boolean manager){
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    public Employee() {

    }

    public void setID(String id){this.id = id;}
    public void setFullName(String name){ this.name = name;}
    public void setManager(boolean manager) {this.manager = manager;}

    public String getID(){return this.id;}
    public String getFullName(){
        return this.name;
    }
    public boolean isManager() {
        return this.manager;
    }
}