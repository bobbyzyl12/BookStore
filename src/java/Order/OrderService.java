package Order;

import Order.Order.Order;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@TransactionAttribute(NOT_SUPPORTED)
@Stateless
public class OrderService {
    private OrderDao dao;
    
    public void setDao(OrderDao dao)
    {
        this.dao = dao;
    }
    
    @TransactionAttribute(REQUIRED)
     public List<Order> SearchAllByUID(Integer uid)
    {
        return dao.SearchOrderByUid(uid);
    }
     
    @TransactionAttribute(REQUIRES_NEW)
    public boolean AddOrder(Order order){
         return dao.AddOrder(order);
    }
    
    public Boolean PayOrder(Integer oid,Integer uid){
        return dao.PayOrder(oid,uid);
    }
    
}
