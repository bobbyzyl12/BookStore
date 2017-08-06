package Order.Order;

import java.io.Serializable;

public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer userid;
    private Integer state;
    private Double price;
    private String books;
    private String nums;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return the state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the books
     */
    public String getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(String books) {
        this.books = books;
    }

    /**
     * @return the nums
     */
    public String getNums() {
        return nums;
    }

    /**
     * @param nums the nums to set
     */
    public void setNums(String nums) {
        this.nums = nums;
    }


  
    
}
