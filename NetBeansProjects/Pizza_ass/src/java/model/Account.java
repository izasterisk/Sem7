/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Account {
    private int AccountID;
    private String userName;
    private String password;
    private String fullName;
    private int role;

    public Account() {
    }

    public Account(int AccountID, String userName, String password, String fullName, int role) {
        this.AccountID = AccountID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "AccountID=" + AccountID + ", userName=" + userName + ", password=" + password + ", fullName=" + fullName + ", role=" + role + '}';
    }
    
}
