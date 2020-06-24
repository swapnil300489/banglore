package com.example.myitem.pojo;

public class Item {

    private String id;
    private String item_title;
    private String item_desc;
    private int item_check;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public int getItem_check() {
        return item_check;
    }

    public void setItem_check(int item_check) {
        this.item_check = item_check;
    }
}
