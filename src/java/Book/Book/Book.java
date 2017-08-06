/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book.Book;

import java.io.Serializable;


/**
 *
 * @author lyc
 */

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String bookname;
   
    private String author;
    
    private Double price;

    private String category;
    
    private String describe;
    public Book() {
    }

    public Book(Integer id) {
        this.id = id;
    }

    public Book(Integer id, String bookname, String author, double price) {
        this.id = id;
        this.bookname = bookname;
        this.author = author;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.setPrice((Double) price);
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe the describe to set
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
