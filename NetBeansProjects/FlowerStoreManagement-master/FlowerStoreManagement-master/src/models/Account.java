/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Administrator
 */
public class Account {
    private String id;
    private String role;
    private String password;
    private String pId;

    public Account(String id, String password, String role, String pId) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.pId = pId;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getpId() {
        return pId;
    }

    @Override
    public String toString() {
        String ret = String.format("%s,%s,%s,%s",id,password,role,pId);
        return ret;
    }
}
