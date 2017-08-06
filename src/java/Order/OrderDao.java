package Order;

import Order.Order.Order;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OrderDao {
     private SessionFactory factory;
   public void setFactory(SessionFactory factory){this.factory = factory;}
   
    public List<Order> SearchOrderByUid(Integer uid)
    {
        Session session = factory.openSession();
        String hql = "from Order where userid=:uid";
        Query query = session.createQuery(hql);
        query.setInteger("uid", uid);
        return query.list();
    }
    
    public Order FindOrderByID(Integer oid){
        Session session = factory.openSession();
        String hql = "from order where id=:oid";
        Query query = session.createQuery(hql);
        query.setInteger("oid", oid);
        return (Order)query.uniqueResult();
    }
    
    
    public boolean PayOrder(Integer oid,Integer uid){
       Order order = new Order();  
       order.setId(oid); 
       order.setState(2);
       order.setUserid(uid);
       
        Session session2 = factory.openSession();  
        session2.beginTransaction();  
        session2.update(order);  
        session2.getTransaction().commit();  
        session2.close();  
         return true;
     }
    
    
    public boolean AddOrder(Order order)
    {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
