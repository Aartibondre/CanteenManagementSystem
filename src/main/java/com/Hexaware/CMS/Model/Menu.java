package com.Hexaware.CMS.Model;

/**
 * food class used to display food information.
 * @author hexware
 */
public class Menu {
	 private int foodId;
     private String foodName;
     private int foodPrice;
     private int vendorId; 
 
 
     public Menu(){}
 
    
     public Menu(int foodId,String foodName, int foodPrice, int vendorId){
         this.foodId=foodId;
         this.foodName=foodName;
         this.foodPrice=foodPrice;
         this.vendorId = vendorId;
 
     }
     public void setFoodId(int foodId){
         this.foodId=foodId;
     }
 
     public int getFoodId(){
         return foodId;
     }
 
     public void setFoodName(String foodName){
         this.foodName=foodName;
     }
 
     public String getFoodName(){
         return foodName;
     }
 
     public void setFoodPrice(int foodPrice){
         this.foodPrice=foodPrice;
     }
 
     public int getFoodPrice(){
         return foodPrice;
     }

         public int getVendorId() {
         return vendorId;
     }
 
     public void setVendorId(int vendorId) {
         this.vendorId = vendorId;
     }
     // modify to show vendor id.
     public String toString(){
         return "Menu---> [Food id: "+foodId+", Food Name: "+foodName+
                 ", Food Price: "+foodPrice + ", Vendor id: "+vendorId+"]";
     }
 
     
 
     

    }
