/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Products {
    private int ProductID;
    private String ProductName;
    private Suppliers Supplier;
    private Categories Category;
    private int QuantityPerUnit;
    private float UnitPrice;
    private String ProductImage;
    private String Description;

    public Products() {
    }

    public Products(int ProductID, String ProductName, Suppliers Supplier, Categories Category, int QuantityPerUnit, float UnitPrice, String ProductImage, String Description) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Supplier = Supplier;
        this.Category = Category;
        this.QuantityPerUnit = QuantityPerUnit;
        this.UnitPrice = UnitPrice;
        this.ProductImage = ProductImage;
        this.Description = Description;
    }

    

   

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public Suppliers getSupplier() {
        return Supplier;
    }

    public void setSupplierID(Suppliers Supplier) {
        this.Supplier = Supplier;
    }

    public Categories getCategory() {
        return Category;
    }

    public void setCategoryID(Categories Category) {
        this.Category = Category;
    }

    public int getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(int QuantityPerUnit) {
        this.QuantityPerUnit = QuantityPerUnit;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    } 
}
