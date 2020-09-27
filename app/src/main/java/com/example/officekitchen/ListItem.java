package com.example.officekitchen;

public class ListItem {
    private String company;
    private  String location;
    private String price;
    private String alert;



    public ListItem(String company, String location, String price, String alert) {
        this.company = company;
        this.location = location;
        this.price = price;
        this.alert = alert;

    }

    public String getCompany() {

        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }
    public String getAlert() {
        return alert;
    }
}


