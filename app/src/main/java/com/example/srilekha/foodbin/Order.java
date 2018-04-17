package com.example.srilekha.foodbin;

/**
 * Created by Srilekha on 14-04-2018.
 */

public class Order {

    private String dname;
    private String contact;
    private String donoraddr;
    private String deliveryaddress;
    private String id;
    private String type;
    private String pack;
    private String serves;
    public Order()
    {

    }
    public Order(String dname1,String contact1,String donoraddr1,String deliveryaddress1,String idd)
    {
        dname=dname1;contact=contact1;donoraddr=donoraddr1;deliveryaddress=deliveryaddress1;
        id=idd;
      /*  type=type1;
        pack=pack1;
        serves=serves1; */
    }
    public Order(String dname1,String contact1,String donoraddr1,String deliveryaddress1,String idd,
                 String type1 , String pack1, String serves1)
    {
        dname=dname1;contact=contact1;donoraddr=donoraddr1;deliveryaddress=deliveryaddress1;
        id=idd;
        type=type1;
        pack=pack1;
        serves=serves1;
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

    public String getid() {
        return id;
    }

    public void setid(String headline) {
        this.id = headline;
    }

    public String gettype() {
        return type;
    }

    public void settype(String headline) {
        this.type = headline;
    }

    public String getpack() {
        return pack;
    }

    public void setpack(String headline) {
        this.pack= headline;
    }

    public String getserves() {
        return serves;
    }

    public void setserves(String headline) {
        this.id = headline;
    }


    public void setdeliveryaddress(String date) {
        this.deliveryaddress = date;
    }

}
