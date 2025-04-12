/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }
    public Item getItemByID(int id){
        for(Item i:items){
            if(i.getProduct().getId()==id){
                return i;
            }
        }
        return null;
    }
    public int getQuantityByID(int id){
        return getItemByID(id).getQuantity();
    }
    
    public void addItem(Item t){
        if(getItemByID(t.getProduct().getId())!=null){
            Item m= getItemByID(t.getProduct().getId());
            m.setQuantity(m.getQuantity()+t.getQuantity());
        }else{
            items.add(t);
        }
    }
    
    public void removeItem(int id){
        if(getItemByID(id)!=null){
            items.remove(getItemByID(id));
        }
    }
    public double getTotalMoney(){
        double t = 0;
        for(Item i:items){
            t+=i.getQuantity()*i.getPrice();
        }
        return t;
    }
}
