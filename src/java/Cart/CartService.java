
package Cart;

import Cart.Cart.Cart;
import java.util.List;
import javax.ejb.Stateful;

@Stateful
public class CartService {
    private CartDao dao;
     
    public void setDao(CartDao dao)
    {
        this.dao = dao;
    }
    
    public String AddCart(Cart cart)
    {
        if(!dao.AddCart(cart))
            return "error";
        return "success";
    }
    
    public Cart SearchAllByID(Integer cid)
    {
        return dao.getCartById1(cid);
    }
    
  public List<Cart> SearchAllByUID(Integer cid)
    {
        return dao.SearchCartByUid(cid);
    }
  
  public String EditCart(Integer cartid,Integer number){
      dao.EditCart(cartid, number);
       return "success";
  }
  
   public String DeleteCart(Integer cartid){
      dao.DeleteCart(cartid);
       return "success";
  }
  
  public String PayCart(Integer cartid){
      dao.PayCart(cartid);
       return "success";
  }
  
  public String DeleteAllCart(Integer userid){
      List<Cart> cartlist = dao.SearchCartByUid(userid);
      for(int i=0;i<cartlist.size();++i){
          dao.DeleteCart2(cartlist.get(i).getCartid());
      }
       return "success";
  }
}
