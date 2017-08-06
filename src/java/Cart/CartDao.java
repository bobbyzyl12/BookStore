package Cart;
import Cart.Cart.Cart;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CartDao {
    private SessionFactory factory;
    public void setFactory(SessionFactory factory){this.factory = factory;}
    
    public List<Cart> SearchCartByUid(Integer uid)
    {
        Session session = factory.openSession();
        String hql = "from Cart where userid=:uid and status = 1";
        Query query = session.createQuery(hql);
        query.setInteger("uid", uid);
        return query.list();
    }
    
    public Cart getCartById1(Integer cid)
    {
        Session session = factory.openSession();
        String hql = "from Cart where cartid=:cid";
        Query query = session.createQuery(hql);
        query.setInteger("cid", cid);
        return (Cart)query.uniqueResult();
    }
    
    
    public Cart getCartById2(Integer uid,Integer bid)
    {
        Session session = factory.openSession();
        String hql = "from cart where userid=:uid and bookid=:bid and status = 1";
        Query query = session.createQuery(hql);
        query.setInteger("uid", uid);
        query.setInteger("bid", bid);
        return (Cart)query.uniqueResult();
    }
    
    public boolean AddCart(Cart cart)
    {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.save(cart);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
     public boolean EditCart(Integer cid,Integer number){
        Session session = factory.openSession();
        session.beginTransaction();  
        Query query = session.createQuery("update Cart c set c.number = :number where cartid = :cid and status =1");  
        query.setInteger("cid", cid);
        query.setInteger("number",number);
        query.executeUpdate();  
        session.getTransaction().commit();
        return true;
     }
     
      public boolean DeleteCart(Integer cid){
        Session session = factory.openSession();
        session.beginTransaction();  
        Query query = session.createQuery("update Cart c set c.status = 3 where cartid = :cid ");  
        query.setInteger("cid", cid);
        query.executeUpdate();  
        session.getTransaction().commit();
        return true;
     }
      
      public boolean DeleteCart2(Integer cid){
        Session session = factory.openSession();
        session.beginTransaction();  
        Query query = session.createQuery("update Cart c set c.status = 2 where cartid = :cid ");  
        query.setInteger("cid", cid);
        query.executeUpdate();  
        session.getTransaction().commit();
        return true;
     }
      
   public boolean PayCart(Integer cid){
        Session session = factory.openSession();
        session.beginTransaction();  
        Query query = session.createQuery("update Cart c set c.status =2 where id = :cid");  
        query.setInteger("cid", cid);
        query.executeUpdate();  
        session.getTransaction().commit();
        return true;
     }
   
}
