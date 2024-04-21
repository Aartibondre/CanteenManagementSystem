package com.Hexaware.CMS.Model;


public class Vendor {
    private int venId;  // Vendor.vendor_id  rs.getInt("vendor_id")
  private String venName;   // rs.getString("vendor_name")
  private String venPhone;  // rs.getString("vendor_phone")
  private String venSpecs;  // rs.getString("vendor_specs")
 private int venLoginId;
  private String venPassword;

  public Vendor(){}
  
  public Vendor(int venId, String venName, String venPhone, String venSpecs, int venLoginId, String venPassword) {
        
        this.venId = venId;
        this.venName = venName;
        this.venPhone = venPhone;
        this.venSpecs = venSpecs;
        this.venLoginId = venLoginId;
        this.venPassword = venPassword;
}


public Vendor(int venId, String venName, String venPhone, String venSpecs) {
	super();
	this.venId = venId;
	this.venName = venName;
	this.venPhone = venPhone;
	this.venSpecs = venSpecs;
}


public int getVenId() {
    return venId;
  }


  public void setVenId(int venId) {
    this.venId = venId;
  }


  public String getVenName() {
    return venName;
  }


  public void setVenName(String venName) {
    this.venName = venName;
  }


  public String getVenPhone() {
    return venPhone;
  }


  public void setVenPhone(String venPhone) {
    this.venPhone = venPhone;
  }


  public String getVenSpecs() {
    return venSpecs;
  }


  public void setVenSpecs(String venSpecs) {
    this.venSpecs = venSpecs;
  }
  
  public int getVenLoginId() {
        return venLoginId;
}


public void setVenLoginId(int venLoginId) {
        this.venLoginId = venLoginId;
}


public String getVenPassword() {
        return venPassword;
}


public void setVenPassword(String venPassword) {
        this.venPassword = venPassword;
}


@Override
public String toString() {
        return "*****Vendor*****\n VendorId: " + venId + "\n  Vendor Name: " + venName + "\n  Vendor Phone: " + venPhone + "\n  Vendor Specs: " + venSpecs
                          ;
}






   
}