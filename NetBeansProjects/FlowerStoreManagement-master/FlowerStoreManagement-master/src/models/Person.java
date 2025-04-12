/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models;

import tools.InputHandle;

/**
 *
 * @author Administrator
 */
public abstract class Person {

    protected String id;
    protected String name;
    protected String address;
    protected String phone;

    public abstract String getRole();

    public void updateProfile() {
        String newName = InputHandle.getString("Enter name: ");
        String newAddress = InputHandle.getString("Enter address: ");
        while (true) {
            String newPhone = InputHandle.getString("Enter phone: ") ;
            if (newPhone.length() == 9 || newPhone.length() == 12) break ;
        }
        System.out.println("Successfully update account");
    }
}
