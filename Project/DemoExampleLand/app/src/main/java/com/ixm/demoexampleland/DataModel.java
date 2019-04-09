package com.ixm.demoexampleland;

/**
 * Created by anupamchugh on 11/02/17.
 */

public class DataModel {


    public String text;
    public int drawable;

    public DataModel(String text, int drawable, String color) {
        this.text = text;
        this.drawable = drawable;
        this.color = color;
    }

    public String color;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String count;
    int pos;

    public DataModel(String t, int d, String c, int p) {
        text = t;
        drawable = d;
        color = c;
        pos = p;
    }
}
