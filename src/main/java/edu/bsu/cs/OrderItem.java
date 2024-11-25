package edu.bsu.cs;

public class OrderItem {
    private String itemName;
    private boolean completed;

    private String specialInstructions;

    public void specifyItem(String itemName){
        this.itemName = itemName;
        completed = false;
    }

    public void completeItem(){
        completed = true;
    }

    public String getItemName(){
        return itemName;
    }

    public boolean isCompleted(){
        return completed;
    }

}
