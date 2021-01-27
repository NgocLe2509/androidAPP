package com.example.cau5;

public class ListSpinner {
    private String name;
    private int img;

    public  ListSpinner(String dish_name, int dish_img){
        name = dish_name;
        img = dish_img;
    }

    public  String get_Dish_Name() {
        return name;
    }

    public  int get_Dish_Image(){
        return img;
    }
}
