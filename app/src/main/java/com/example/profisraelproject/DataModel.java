package com.example.profisraelproject;

import java.util.List;

public class DataModel {
    private List<String> nestedList;
    private String ItemText;
    private boolean isExpandable;

    public DataModel(List<String> nestedList, String itemText) {
        this.nestedList = nestedList;
        this.ItemText = itemText;
        isExpandable =false;
    }

    public List<String> getNestedList() {
        return nestedList;
    }

    public void setNestedList(List<String> nestedList) {
        this.nestedList = nestedList;
    }

    public String getItemText() {
        return ItemText;
    }

    public void setItemText(String itemText) {
        ItemText = itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
