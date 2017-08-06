package Book;

import Book.Book.Book;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author lyc
 */
public class BookDao {
   private SessionFactory factory;
    public void setFactory(SessionFactory factory){this.factory = factory;}
    
    public Book getBookById(int id)
    {
        Session session = factory.openSession();
        String hql = "from Book where id=:id";
        Query query = session.createQuery(hql);
        query.setCacheable(true);
        query.setInteger("id", id);
        return (Book)query.uniqueResult();
    }
    
    public boolean AddBook(Book book)
    {
        Session session = factory.openSession();
            try{
                session.beginTransaction();
                session.save(book);
                session.getTransaction().commit();
            }catch(Exception ex)
            {
                ex.printStackTrace();
                return false;
            }
        return true;
    }
    
    public List<Book> SearchBook(String type, String search)
    {
        Session session = factory.openSession();
        String hql = "from Book where "+type+"=:search";
        Query query = session.createQuery(hql);
        query.setCacheable(true);
        query.setString("search", search);
        return query.list();
    }
    
    public Book SearchBookByID(Integer id){
        Session session = factory.openSession();
        String hql = "from Book where id =:id";
        Query query = session.createQuery(hql);
        query.setCacheable(true);
        query.setInteger("id", id);
        return (Book)query.uniqueResult();
    }
}
