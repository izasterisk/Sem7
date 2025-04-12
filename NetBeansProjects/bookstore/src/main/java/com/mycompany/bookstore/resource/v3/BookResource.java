/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource.v3;

import com.mycompany.bookstore.dao.BookDAO;
import com.mycompany.bookstore.dto.Book;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author Admin
 */
@Path("v3/books")
public class BookResource {
    private BookDAO dao = BookDAO.getInstance();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAll(){
        return dao.getAll();
    }
}
