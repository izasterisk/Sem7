/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change menu license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit menu template
 */
package controllers;

import controllers.managers.*;
import models.Account;
import models.Customer;
import models.Flower;
import models.Order;
import models.Staff;
import tools.InputHandle;
import view.Menu;

/**
 *
 * @author Administrator
 */
public class UserMenu {

    private Menu menu = new Menu("USER MENU:");
    private String userId;
    private Customer customer;
    private Order order;
    private boolean canceled = false;

    public UserMenu(Account account, CustomerManager cm, OrderManager om) {
        userId = account.getpId();
        customer = cm.find(userId);
        order = om.newOrder(userId);
        menu.addOption("Update profile");
        menu.addOption("View flower list");
        menu.addOption("Add flower to cart");
        menu.addOption("View order");
        menu.addOption("Cancle order");
        menu.addOption("Quit");
    }
    
    public int load(){
        menu.printMenu();
        return menu.getChoice() ;
    }

    public void updateProfile() {
        customer.updateProfile();
    }

    public void viewFlowerList(FlowerManager fm) {
        fm.viewList();
    }

    public void addFlowerToCart(OrderManager om, FlowerManager fm) {
        String id;
        while (true) {
            id = InputHandle.getString("Enter flower id FXXX: ");
            if (id.matches("F\\d{3}") && fm.find(id) != null) {
                break;
            }
        }
        int quantity = InputHandle.getPositiveInt("Enter quantity: ", "");
        Flower flower = fm.find(id);
        order.addFlower(flower, quantity);
        System.out.println("Succesfully add to cart.");
    }

    public void viewOrder() {
        order.show();
    }

    public void cancelOrder() {
        canceled = true;
        System.out.println("Order canceled.");
    }

    public boolean quit(OrderManager om) {
        String ask = InputHandle.getString("Do you want to save data[1/0-Y/N-T/F]: ");
        boolean save = ask.matches("[1TtYy]");
        if (!canceled && save) {
            om.add(order);
        }
        return save;
    }

}
