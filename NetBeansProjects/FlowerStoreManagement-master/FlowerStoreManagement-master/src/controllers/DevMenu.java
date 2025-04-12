/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change menu license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit menu template
 */
package controllers;

import controllers.managers.*;
import models.Account;
import models.Staff;
import tools.InputHandle;
import view.Menu;

/**
 *
 * @author Administrator
 */
public class DevMenu {

    private Menu menu = new Menu("DEV MENU:");
    private String staffId ;
    private Staff staff ;
    
    public DevMenu(Account account, StaffManager sm) {
        staffId = account.getpId();
        staff = sm.find(staffId);
        menu.addOption("Update profile");
        menu.addOption("View flower list");
        menu.addOption("Add flower ");
        menu.addOption("Modify flower");
        menu.addOption("Remove flower");
        menu.addOption("View sorted order");
        menu.addOption("Delete order");
        menu.addOption("Quit");
    }

    public int load() {
        menu.printMenu();
        return menu.getChoice();
    }

    public void updateProfile() {
        staff.updateProfile();
    }

    public void viewFlowerList(FlowerManager fm) {
        fm.viewList();
    }

    public void addFlowerToList(FlowerManager fm) {
        fm.add();
    }

    public void modifyFlower(FlowerManager fm) {
        fm.modify();
    }

    public void removeFlower(FlowerManager fm, OrderManager om) {
        fm.remove(om);
    }

    public void viewSortedOrder(OrderManager om, CustomerManager cm) {
        om.showList(cm);
    }

    public void removeOrder(OrderManager om) {
        om.removeOrder();
    }

    public boolean quit() {
        String ask = InputHandle.getString("Do you want to save data[1/0-Y/N-T/F]: ");
        return ask.matches("[1TtYy]");
    }
}
