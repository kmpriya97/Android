package com.ixm.demoexampleland;


import java.util.ArrayList;

public class ModelNew {
    public ModelNew(int id, String itemName, String itemCount) {
        this.id = id;
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

    int id;

    public ModelNew() {

    }

    public int getId() {
        return id;
    }
    private static final ModelNew holder = new ModelNew();
    public static ModelNew getInstance() {
        return holder;
    }
    private ArrayList<ModelNew> data;

    public ArrayList<ModelNew> getData() {
        return data;
    }

    public void setData(ArrayList<ModelNew> data) {
        this.data = data;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String  itemCount) {
        this.itemCount = itemCount;
    }

    String itemName;
     String itemCount;
}
