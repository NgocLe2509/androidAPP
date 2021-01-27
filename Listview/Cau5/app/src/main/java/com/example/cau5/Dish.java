package com.example.cau5;

public class Dish {
    private String name;
    private int itemSpinner;
    private boolean promotion;

    public Dish() {
        super();
        this.name = name;
        this.itemSpinner = itemSpinner;
        this.promotion = promotion;
    }


    public void setName(String name){this.name = name;}
    public void setSpinner(int itemSpinner){this.itemSpinner = itemSpinner;}
    public void setPromotion(boolean promotion){this.promotion = promotion;}

    public String getName(){return this.name;}
    public int getSpinner(){return this.itemSpinner;}
    public boolean isPromotion(){return this.promotion;}
}
