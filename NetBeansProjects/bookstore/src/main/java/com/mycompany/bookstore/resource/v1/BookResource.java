/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource.v1;

import com.mycompany.bookstore.dto.Book;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


/**
 *
 * @author Admin
 */
@Path("v1/books")
public class BookResource {
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String sayHello(){
//        return "Lo con c";
//    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(){
        return new Book("8935244878349", "Lop Hoc Rung Ron", "Emi Ishikawa", 1, 2022);
    }
}
