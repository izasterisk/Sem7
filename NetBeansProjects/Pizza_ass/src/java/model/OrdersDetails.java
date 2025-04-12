/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class OrdersDetails {
    private Orders Order;
    private Products product;
    private float unitPrice;
    private int quantity;

    public OrdersDetails() {
    }

    public OrdersDetails(Orders Order, Products product, float unitPrice, int quantity) {
        this.Order = Order;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public void setOrder(Orders Order) {
        this.Order = Order;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 

    public Orders getOrder() {
        return Order;
    }

    public Products getProduct() {
        return product;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
