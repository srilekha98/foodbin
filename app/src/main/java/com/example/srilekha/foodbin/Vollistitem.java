package com.example.srilekha.foodbin;

/**
 * Created by Srilekha on 14-04-2018.
 */

public class Vollistitem {

    private String dname;
    private String contact;
    private String donoraddr;
    private String deliveryaddress;
    private String id;
    public Vollistitem()
    {

    }
    public Vollistitem(String dname1,String contact1,String donoraddr1,String deliveryaddress1,String idd)
    {
        dname=dname1;contact=contact1;donoraddr=donoraddr1;deliveryaddress=deliveryaddress1;
        id=idd;
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

    public void setdeliveryaddress(String date) {
        this.deliveryaddress = date;
    }


}
