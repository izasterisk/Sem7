/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Suppliers {
    private int SupplierID;
    private String CompanyName;
    private String Address;
    private String Phone;

    public Suppliers() {
    }

    public Suppliers(int SupplierID, String CompanyName, String Address, String Phone) {
        this.SupplierID = SupplierID;
        this.CompanyName = CompanyName;
        this.Address = Address;
        this.Phone = Phone;
    }
    

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public String toString() {
        return "Suppliers{" + "SupplierID=" + SupplierID + ", CompanyName=" + CompanyName + ", Address=" + Address + ", Phone=" + Phone + '}';
    }
}
