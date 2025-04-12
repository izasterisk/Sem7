/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.managers;

import java.util.ArrayList;
import java.util.HashSet;
import models.Customer;
import tools.FileHandle;

/**
 *
 * @author Administrator
 */
public class CustomerManager extends HashSet<Customer>{
    public CustomerManager(String path) {
        ArrayList<String> dta = FileHandle.readFromFile(path);
        for (String line : dta) {
            if (line == null ||line.isEmpty()) continue ;
            String strSplit[] = line.trim().split(",");
            this.add(new Customer(strSplit[0],
                    strSplit[1],
                    strSplit[2],
                    strSplit[3]));
        }
    }
    
    public Customer find(String id){
        for(Customer c : this){
            if (id.equals(c.getId())) return c ;
        }
        return null ;
    }
    
    public ArrayList<String> toArrayList(){
        ArrayList<String> ret = new ArrayList<>();
        for(Customer s : this){
            ret.add(s.toString()) ;
        }
        return ret;
    }
}
