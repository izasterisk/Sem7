/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Orders {
    private int OrderID;
    private Customers Customer;
    private Date OrderDate;
    private Date ShippedDate;
    private float Freight;
    private String ShipAddress;
    private boolean status;
    
    

    public Orders() {
    }

    public Orders(int OrderID, Customers Customer, Date OrderDate, Date ShippedDate, float Freight, String ShipAddress, boolean status) {
        this.OrderID = OrderID;
        this.Customer = Customer;
        this.OrderDate = OrderDate;
        this.ShippedDate = ShippedDate;
        this.Freight = Freight;
        this.ShipAddress = ShipAddress;
        this.status = status;
    }

    

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public Customers getCustomer() {
        return Customer;
    }

    public void setCustomer(Customers Customer) {
        this.Customer = Customer;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }


    public Date getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(Date ShippedDate) {
        this.ShippedDate = ShippedDate;
    }

    public float getFreight() {
        return Freight;
    }

    public void setFreight(float Freight) {
        this.Freight = Freight;
    }

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String ShipAddress) {
        this.ShipAddress = ShipAddress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
