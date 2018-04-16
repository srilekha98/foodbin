package com.example.srilekha.foodbin;

/**
 * Created by Srilekha on 14-04-2018.
 */

public class Order {

    private String dname;
    private String contact;
    private String donoraddr;
    private String deliveryaddress;
    public Order()
    {

    }
    public Order(String dname1,String contact1,String donoraddr1,String deliveryaddress1)
    {
        dname=dname1;contact=contact1;donoraddr=donoraddr1;deliveryaddress=deliveryaddress1;
    }


    public String getdname() {
        return dname;
    }

    public void setdname(String headline) {
        this.dname = headline;
    }

    public String getcontact() {
        return contact;
    }

    public void setcontact(String reporterName) {
        this.contact = reporterName;
    }

    public String getdonoraddr() {
        return donoraddr;
    }

    public void setdonoraddr(String date) {
        this.donoraddr = date;
    }

    public String getdeliveryaddress() {
        return deliveryaddress;
    }

    public void setdeliveryaddress(String date) {
        this.deliveryaddress = date;
    }

}
