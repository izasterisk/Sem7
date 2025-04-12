/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import models.Flower;
import models.Order;
import tools.FileHandle;
import tools.InputHandle;
import tools.PrintingFormat;

/**
 *
 * @author Administrator
 */
public class FlowerManager extends HashSet<Flower> {

    public FlowerManager(String path) {
        ArrayList<String> dta = FileHandle.readFromFile(path);
        for (String line : dta) {
            if (line == null ||line.isEmpty()) continue ;
            String strSplit[] = line.trim().split(",");
            this.add(new Flower(strSplit[0],
                    strSplit[1],
                    Double.parseDouble(strSplit[2]),
                    InputHandle.toDate(strSplit[3], "yyyy/MM/dd")));
        }
    }

    private String autoTakingID() {
        String id = "F";
        String num = Integer.toString(this.size());
        for (int i = 0; i < 3 - num.length(); i++) {
            id += "0";
        }
        id += num;
        return id;
    }

    public void add() {
        String id = autoTakingID();
        String name = InputHandle.getString("Enter name: ");
        Double price = InputHandle.getPositiveReal("Enter price: ");
        Date date = InputHandle.getDate("Enter date: ", "yyyy/MM/dd");
        this.add(new Flower(id, name, price, date));
        System.out.println("Successfully add flower");
    }

    public void viewList() {
        PrintingFormat.printLine80();
        String title = PrintingFormat.print4Args("id", "name", "price", "importDate");
        System.out.println(title);
        PrintingFormat.printLine80();
        for (Flower f : this) {
            String line = PrintingFormat.print4Args(f.getId(),
                    f.getName(), Double.toString(f.getPrice()),
                    InputHandle.toDateString(f.getImportDate(), "yyyy/MM/dd"));
            System.out.println(line);
        }
        PrintingFormat.printLine80();
        String total = PrintingFormat.printTotal80(String.format("TOTAL: %d flower type[s]", this.size()));
        System.out.println(total);
        PrintingFormat.printLine80();
    }
    
    public Flower find(String id){
        for(Flower f : this){
            if(f.getId().equals(id)) return f ;
        }
        return null ;
    }

    public void modify() {
        String id;
        while (true) {
            id = InputHandle.getString("Enter flower id FXXX: ");
            if (id.matches("F\\d{3}") && find(id) != null) {
                break;
            }
        }
        Flower f = find(id) ;
        String name = InputHandle.getString("Enter name: ");
        Double price = InputHandle.getPositiveReal("Enter price: ");
        Date date = InputHandle.getDate("Enter date: ", "yyyy/MM/dd");
        f.setName(name);
        f.setImportDate(date);
        f.setPrice(price);
        System.out.println("Successfully modify the flower.");
    }

    public void remove(OrderManager om) {
        String id;
        while (true) {
            id = InputHandle.getString("Enter flower id FXXX: ");
            if (id.matches("F\\d{3}") && find(id) != null) {
                break;
            }
        }
        Flower f = find(id) ;
        boolean able = true ;
        for(Order o : om){
            if(o.getoList().containsKey(f)){
                able = false ;
                break ;
            }
        }
        if (able){
            System.out.println("Successfully remove");
            this.remove(f) ;
        } else{
            System.out.println("Failed to remove.");
        }
    }
    
    public ArrayList<String> toArrayList(){
        ArrayList<String> ret = new ArrayList<>();
        for(Flower s : this){
            ret.add(s.toString()) ;
        }
        return ret;
    }
}
