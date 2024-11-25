package edu.bsu.cs;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private int tableNumber;
    private int timer;

    private List<OrderItem> itemList;

    public void SetInitialInfo(int orderID, int tableNumber){
        this.orderID = orderID;
        this.tableNumber = tableNumber;
        this.itemList = new ArrayList<>();
    }

    public void addItem(String itemName){
        OrderItem currentOrder = new OrderItem();
        itemList.add(currentOrder);
        currentOrder.specifyItem(itemName);
    }

    public void completeOrderItem(String itemName){
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).getItemName().equals(itemName)){
                itemList.get(i).completeItem();
                i = itemList.size();
            }
        }
    }

    public boolean checkForCompletion(){
        boolean isCompleted = true;
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).isCompleted() == false){
                isCompleted = false;
            }
        }
        return  isCompleted;
    }
}