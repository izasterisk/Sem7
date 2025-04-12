/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.managers;

import java.util.ArrayList;
import java.util.HashSet;
import models.Account;
import tools.FileHandle;

/**
 *
 * @author Administrator
 */
public class AccountManager extends HashSet<Account>{

    public AccountManager(String path) {
        ArrayList<String> dta = FileHandle.readFromFile(path);
        for (String line : dta) {
            if (line == null ||line.isEmpty()) continue ;
            String lineSplit[] = line.trim().split(",");
            this.add(new Account(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3]));
        }
    }
    
    public Account authenticate(String id , String pass){
        for(Account account : this){
            String _id = account.getId() ;
                String  _pass = account.getPassword() ;
                if (id.equals(_id) && pass.equals(_pass)){
                    return account;
                }
        }
        return null;
    }
    
    public Account find(String id){
        for(Account a : this){
            if (id.equals(a.getId())) return a ;
        }
        return null ;
    }
    
    public ArrayList<String> toArrayList(){
        ArrayList<String> ret = new ArrayList<>();
        for(Account s : this){
            ret.add(s.toString()) ;
        }
        return ret;
    }
}
