/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.managers.AccountManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import models.Account;
import tools.FileHandle;
import tools.InputHandle;

/**
 *
 * @author Administrator
 */
public class AccountAuthenticator {

    private AccountManager accounts;

    public AccountAuthenticator(AccountManager am) {
        this.accounts = am ;
    }

    public Account login() {
        while (true) {
            String id, password;
            //Enter id
            while (true) {
                id = InputHandle.getString("Enter id AXXX: ");
                if (id.matches("A\\d{3}")) {
                    break;
                }
            }
            //Enter password
            while (true) {
                password = InputHandle.getString("Enter password: ");
                if (checkValidPass(password)) {
                    break;
                } else {
                    System.out.println("Password must be at least 8 characters comprised by at least one character, one digit, and one special symbol");
                }
            }
            //Check login information then return status code -1:failed, 0:user, 1:dev 
            Account status = accounts.authenticate(id, password) ;
            if (status == null) System.out.println("Failed login. Password or id not match.");
            else{
                System.out.println("Successfully login.");
                return status;
            }
        }
    }

    private boolean checkValidPass(String pass) {
        if (pass.length() < 8) {
            return false;
        }
        boolean hasAlphabeticLetter = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        String specialSymbols = "!@#$%^&*()_+{}[]~-";
        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);
            if (Character.isAlphabetic(c)) {
                hasAlphabeticLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (specialSymbols.contains(Character.toString(c))) {
                hasSpecial = true;
            }
            if (hasAlphabeticLetter && hasDigit && hasSpecial) {
                break;
            }
        }
        if (!hasAlphabeticLetter || !hasDigit || !hasSpecial) {
            return false;
        }
        return true;
    }
}