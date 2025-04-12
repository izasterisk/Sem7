/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource.v2;

import com.mycompany.bookstore.dto.Book;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
@Path("v2/books")
public class BookResource {
    private List<Book> list= new ArrayList();
    
    public BookResource(){
        list.add(new Book("abc", "xyx", "zya", 23, 42412));
        list.add(new Book("asd", "asgasd", "gascxz", 123, 123));
        list.add(new Book("asdf", "gasf", "zyaasd", 321, 412344));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    public List<Book> getAll(){
        return list;
    }
    
    @GET
    @Path("{masach}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getOne(@PathParam("masach") String isbn){
        for (Book x : list) {
            if(x.getIsbn().equalsIgnoreCase(isbn)){
                return x;
            }
        }
        return null;
    }
}
