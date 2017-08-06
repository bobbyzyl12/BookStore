package Order;

import Book.Book.Book;
import Book.BookService;
import Cart.Cart.Cart;
import Cart.CartService;
import JMS.jmsSender;
import Order.Order.Order;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.jms.Destination;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import javax.servlet.http.HttpSession;

public class OrderAction extends ActionSupport{
    private OrderService service;
    private Order order;
    private Destination destination;
    private jmsSender jmssender;
    private CartService cartService;
    private BookService bookService;
    
    public void setService(OrderService service)
    {
        this.service = service;
    }
    
    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }
    
    public void setBookService(BookService bookService)
    {
        this.bookService = bookService;
    }
    
    public void setUser(Order order)
    {
        this.order = order;
    }
    
    public void setDestination(Destination destination){
        this.destination = destination;
    }
    
    public void setJmssender(jmsSender jmssender){
        this.jmssender = jmssender;
    }
    
    public OrderAction() {
        
    }
    
    
     public String JumpMyOrder()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();  
        String login_flag = session.getAttribute("login_flag").toString();
        System.out.println(login_flag);
        if(login_flag.equals("false"))
            return ERROR;

        Integer uid = (Integer)session.getAttribute("userid");
        List<Order> orderList = service.SearchAllByUID(uid);
        ServletActionContext.getRequest().setAttribute("orderlist", orderList);
        return SUCCESS;
    }
     
     public String AddOrder()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();  
        Integer uid = (Integer)session.getAttribute("userid");
        Order order =new Order();
        
        Double price = 0.0;
        String bookstr = "";
        String numstr ="";
        List<Cart> cartList = cartService.SearchAllByUID(uid);
        for(int i=0;i<cartList.size();++i){
            Cart cart = cartList.get(i);
            Book book = bookService.SearchBookByID(cart.getBookid());
            price = book.getPrice()*cart.getNumber()+ price ;
            bookstr = bookstr + (cart.getBookid().toString());
            numstr = numstr + (cart.getNumber().toString());
            if(i!=cartList.size()-1)
            {
                bookstr = bookstr + "/";
                numstr = numstr + "/";
            }    
            
        }
        
        order.setUserid(uid);
        order.setState(1);
        order.setPrice(price);
        order.setBooks(bookstr);
        order.setNums(numstr);
        //service.AddOrder(order);
        jmssender.send(destination, order);
        cartService.DeleteAllCart(uid);
        return SUCCESS;
    }
     
     public String PayOrder()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();  
        Integer uid = (Integer)session.getAttribute("userid");
        Integer oid = Integer.parseInt(ServletActionContext.getRequest().getParameter("oid"));
        service.PayOrder(oid,uid);
        return SUCCESS;
    }
}
