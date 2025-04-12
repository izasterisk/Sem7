/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.AccountAuthenticator;
import controllers.DevMenu;
import controllers.managers.*;
import java.util.ArrayList;
import models.Account;
import tools.FileHandle;

/**
 *
 * @author Administrator
 */
public class Main {

    private static String accountPath;
    private static String customerPath;
    private static String orderPath;
    private static String staffPath;
    private static String flowerPath;
    private static AccountManager am;
    private static CustomerManager cm;
    private static StaffManager sm;
    private static FlowerManager fm;
    private static OrderManager om;

    public static void main(String[] args) {
        config("\\data\\config.dat");
        am = new AccountManager(accountPath);
        cm = new CustomerManager(customerPath);
        sm = new StaffManager(staffPath);
        fm = new FlowerManager(flowerPath);
        om = new OrderManager(orderPath, fm);
        AccountAuthenticator aa = new AccountAuthenticator(am);
        Account account = aa.login();
        if (account.getRole().equals("DEV")) {
            DevMenu dm = new DevMenu(account, sm);
            while (true) {
                switch (dm.load()) {
                    case 1: {
                        dm.updateProfile();
                        break;
                    }
                    case 2: {
                        dm.viewFlowerList(fm);
                        break;
                    }
                    case 3: {
                        dm.addFlowerToList(fm);
                        break;
                    }
                    case 4: {
                        dm.modifyFlower(fm);
                        break;
                    }
                    case 5: {
                        dm.removeFlower(fm, om);
                        break;
                    }
                    case 6: {
                        dm.viewSortedOrder(om, cm);
                        break;
                    }
                    case 7: {
                        dm.removeOrder(om);
                        break;
                    }
                    case 8: {
                        boolean status = dm.quit();
                        if (status) {
                            save();
                        }
                        System.out.println("Successfully save.\nSuccessfully logout.");
                        return;
                    }
                }
            }
        } else {
            UserMenu um = new UserMenu(account, cm, om);
            while (true) {
                switch (um.load()) {
                    case 1: {
                        um.updateProfile();
                        break;
                    }
                    case 2: {
                        um.viewFlowerList(fm);
                        break;
                    }
                    case 3: {
                        um.addFlowerToCart(om, fm);
                        break;
                    }
                    case 4: {
                        um.viewOrder();
                        break;
                    }
                    case 5: {
                        um.cancelOrder();
                        break;
                    }
                    case 6: {
                        boolean status = um.quit(om);
                        if (status) {
                            save();
                        }
                        System.out.println("Successfully save.\nSuccessfully logout.");
                        return;
                    }
                }
            }
        }
    }

    public static void config(String path) {
        ArrayList<String> dta = FileHandle.readFromFile(path);
        for (String line : dta) {
            String lineSplit[] = line.trim().split(" ");
            switch (lineSplit[0]) {
                case "account": {
                    accountPath = lineSplit[3];
                    break;
                }
                case "flower": {
                    flowerPath = lineSplit[3];
                    break;
                }
                case "orders": {
                    orderPath = lineSplit[3];
                    break;
                }
                case "customer": {
                    customerPath = lineSplit[3];
                    break;
                }
                case "staff": {
                    staffPath = lineSplit[3];
                    break;
                }
            }
        }
    }

    public static void save() {
        FileHandle.writeToFile(accountPath, am.toArrayList());
        FileHandle.writeToFile(customerPath, cm.toArrayList());
        FileHandle.writeToFile(staffPath, sm.toArrayList());
        FileHandle.writeToFile(flowerPath, fm.toArrayList());
        FileHandle.writeToFile(orderPath, om.toArrayList());
    }
}
