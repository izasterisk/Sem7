/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import tools.InputHandle;
import tools.PrintingFormat;

/**
 *
 * @author Administrator
 */
public class Order {

    private String id;
    private Date date;
    private double total;
    private String buyerId;
    private HashMap<Flower, Integer> oList = new HashMap<>();

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }

    public String getBuyerId() {
        return buyerId;
    }
    
    public int getFlowerCount(){
        int ret = 0 ;
        for (Entry<Flower, Integer> item : oList.entrySet()) {
            ret += item.getValue() ;
        }
        return ret ;
    }

    public HashMap<Flower, Integer> getoList() {
        return oList;
    }

    public Order(String id, Date date, String buyerId) {
        this.id = id;
        this.date = date;
        this.buyerId = buyerId;
        this.total = 0;
    }

    public void addFlower(Flower flower, int quantity) {
        total += flower.getPrice() * quantity ;
        if (oList.containsKey(flower)) {
            int quan = oList.get(flower);
            quantity += quan;
            oList.remove(flower);
        }
        oList.put(flower, quantity);
    }

    private String toStringFList() {
        String ret = "";
        for (Entry<Flower, Integer> item : oList.entrySet()) {
            ret += String.format("%s:%d:%f,", item.getKey().getId(),
                    item.getValue(),
                    item.getKey().getPrice() * item.getValue());
        }
        return ret.substring(0, ret.length() - 1);
    }
    
    public void show(){
        PrintingFormat.printLine60();
        String title = PrintingFormat.print3Args("Order ID", "Date", "Buyer ID") ;
        PrintingFormat.printLine60();
        String _title = PrintingFormat.print3Args(this.id, InputHandle.toDateString(date, "yyyy/MM/dd"), buyerId);
        System.out.println(title + "\n" + _title);
        PrintingFormat.printLine60();
        title = PrintingFormat.print3Args("Flower Name", "Quantity", "Price");
        System.out.println(title);
        PrintingFormat.printLine60();
        for (Entry<Flower, Integer> item : oList.entrySet()) {
            String line = PrintingFormat.print3Args(item.getKey().getName(),
                    item.getValue().toString(),
                    Double.toString(item.getKey().getPrice() * item.getValue()));
            System.out.println(line);
        }
        PrintingFormat.printLine60();
        System.out.println(PrintingFormat.printTotal60(String.format("Total: %f",this.total)));
        PrintingFormat.printLine60();
    }

    @Override
    public String toString() {
        String str = String.format("%s,%s,%f,%s,%s",
                id, InputHandle.toDateString(date, "yyyy/MM/dd"), total,buyerId, toStringFList());
        return str ;
    }
}
