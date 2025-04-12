/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import tools.InputHandle;

/**
 *
 * @author Administrator
 */
public class Flower {

    private String id;
    private String name;
    private double price;
    private Date importDate;

    public Flower() {
    }

    public Flower(String id, String name, double price, Date importDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.importDate = importDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        String str = String.format("%s,%s,%f,%s",
                id, name, price, InputHandle.toDateString(importDate,"yyyy/MM/dd"));
        return str;
    }
}
