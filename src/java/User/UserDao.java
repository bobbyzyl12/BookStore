package User;

import User.User.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDao {
   private SessionFactory factory;
   public void setFactory(SessionFactory factory){this.factory = factory;}
    
    public User getUserById(int id)
    {
        Session session = factory.openSession();
        String hql = "from User where id=:id";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        return (User)query.uniqueResult();
    }
    
    public User getUserByName(String username)
    {
        Session session = factory.openSession();
        String hql = "from User where username=:username";
        Query query = session.createQuery(hql);
        query.setString("username", username);
        return (User)query.uniqueResult();
    }
    
    public boolean UserRegister(User user)
    {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean UserLogin(User user)
    {
        Session session = factory.openSession();
        String hql = "from User where username=:username and password=:password";
        Query query = session.createQuery(hql);
        query.setString("username", user.getUsername());
        query.setString("password", user.getPassword());
        User res = (User)query.uniqueResult();
        return res!=null;
    }

     public List<User> SearchUser()
    {
        Session session = factory.openSession();
        String hql = "from User";
        Query query = session.createQuery(hql);
        return query.list();
    }
}
