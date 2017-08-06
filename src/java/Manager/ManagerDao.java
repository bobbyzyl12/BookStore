package Manager;

import Manager.Manager.Manager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ManagerDao {
   private SessionFactory factory;
    public void setFactory(SessionFactory factory){this.factory = factory;}
    
    public boolean ManagerLogin(String username,String password)
    {
        Session session = factory.openSession();
        String hql = "from Manager where managername=:managername and password=:password";
        Query query = session.createQuery(hql);
        query.setString("managername", username);
        query.setString("password", password);
        Manager manager = (Manager)query.uniqueResult();
        if(manager == null){
            return false;
        }
        else {
            return true;
        }
    }
}
