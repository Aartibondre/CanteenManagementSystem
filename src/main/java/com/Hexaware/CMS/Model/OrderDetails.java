package com.Hexaware.CMS.Model;
//import java.time.LocalDateTime;

public class OrderDetails{
    private int orderNo;
    private int venId;
    private String customerId;
    private int foodID;
    private int quantity;
    private String datetime;
    private int orderValue;
    private String orderStatus;

    public OrderDetails(){}

    public OrderDetails(int orderNo, int venId, String customerId, int foodID, int quantity, String datetime, int orderValue, String orderStatus){
        this.orderNo = orderNo;
        this.venId = venId;
        this.customerId = customerId;
        this.foodID = foodID;
        this.quantity = quantity;
        this.datetime = datetime;
        this.orderValue = orderValue;
        this.orderStatus = orderStatus;
    } 

    public int getOrderNo(){
        return orderNo;
    }
    public void setOrderNo(int orderNo){
        this.orderNo=orderNo;
    }


    public int getVenId(){
        return venId;
    }
    public void setVenId(int venId){
        this.venId=venId;
    }


    public String getCustomerId(){
        return customerId;
    }
    public void setCustomerId(String customerId){
        this.customerId=customerId;
    }


    public int getFoodId(){
        return foodID;
    }
    public void setFoodId(int foodID){
        this.foodID=foodID;
    }


    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }


    public String getDateTime(){
        return datetime;
    }
    public void setDateTime(String datetime){
        this.datetime=datetime;
    }
    

    public int getOrderValue(){
        return orderValue;
    }
    public void orderValue(int orderValue){
        this.orderValue=orderValue;
    }


    public String getOrderStatus(){
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus){
        this.orderStatus=orderStatus;
    }


    @Override
    public String toString(){
        return "Order Details-->[Order No.: "+orderNo+" , "+"Customer Id: "+customerId+" , "+"Vendor Id: "+venId+" , "+"Food Id: "+foodID+" , "+"Quantity: "+" , "+"Date and Time: "+datetime+" , "+"Order Value: "+orderValue+" , "+"Order Status: "+orderStatus+"]";
    }
}

