package Cart.Cart;

import java.io.Serializable;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer cartid;
    private Integer userid;
    private Integer bookid;
    private Integer number;
    private String status;

    /**
     * @return the cartid
     */
    public Integer getCartid() {
        return cartid;
    }

    /**
     * @param cartid the cartid to set
     */
    public void setCartid(Integer cartid) {
        this.cartid = cartid;
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
     * @return the bookid
     */
    public Integer getBookid() {
        return bookid;
    }

    /**
     * @param bookid the bookid to set
     */
    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
