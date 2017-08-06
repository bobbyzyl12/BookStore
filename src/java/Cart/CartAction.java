package Cart;

import Book.Book.Book;
import Book.BookService;
import Cart.Cart.Cart;
import User.User.User;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class CartAction extends ActionSupport{
    private CartService cartService;

    public CartAction(){
    }
    public void setService(CartService cartservice)
    {
        this.cartService = cartservice;
    }
    
    public String AddBookToCart(){
        Cart cart = new Cart();
        HttpSession session = ServletActionContext.getRequest().getSession();  
       Integer uid = (Integer)session.getAttribute("userid");
        cart.setBookid (Integer.parseInt(ServletActionContext.getRequest().getParameter("bid")));
        cart.setNumber (Integer.parseInt(ServletActionContext.getRequest().getParameter("number")));
        cart.setUserid(uid);
        cart.setStatus("1");
        if(cartService.AddCart(cart).equals("success")){
          return SUCCESS;
        }
        else return ERROR;
    }
    
    public String EditBookToCart(){
        Integer cartid = Integer.parseInt(ServletActionContext.getRequest().getParameter("cid"));
        Integer number = Integer.parseInt(ServletActionContext.getRequest().getParameter("number"));
         if(cartService.EditCart(cartid,number).equals("success")){
          return SUCCESS;
        }
        else return ERROR;
    }
    
    public String PayCart(){
        Integer cartid = Integer.parseInt(ServletActionContext.getRequest().getParameter("cid"));
         if(cartService.PayCart(cartid).equals("success")){
          return SUCCESS;
        }
        else return ERROR;
    }
    
    public String DeleteCart(){
        Integer cartid = Integer.parseInt(ServletActionContext.getRequest().getParameter("cid"));
         if(cartService.DeleteCart(cartid).equals("success")){
          return SUCCESS;
        }
        else return ERROR;
    }
    
    public String JumpMyCart()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();  
        Integer uid = (Integer)session.getAttribute("userid");
        List<Cart> carts = cartService.SearchAllByUID(uid);
        ServletActionContext.getRequest().setAttribute("cartlist", carts);
        return SUCCESS;
    }
    
    public String JumpCartDetail()
    {
        Integer cid = Integer.parseInt(ServletActionContext.getRequest().getParameter("cid"));
        Cart cartD = cartService.SearchAllByID(cid);
        //Book book = cartService.SearchBookByID(cartD.getBookid());
        ServletActionContext.getRequest().setAttribute("cartD", cartD);
        //ServletActionContext.getRequest().setAttribute("book", book);
        return SUCCESS;
    }
    
    
}
