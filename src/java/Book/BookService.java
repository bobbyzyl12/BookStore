package Book;

import Book.Book.Book;
import java.util.List;

public class BookService {
    private BookDao dao;
    public void setDao(BookDao dao)
    {
        this.dao = dao;
    }
    
    public List<Book> SearchBook(String type, String search)
    {
        return dao.SearchBook(type, search);
    }
    
    public Book SearchBookByID(Integer bookID){
       return dao.SearchBookByID(bookID);
    }
    
    public boolean AddBook(Book book)
    {
        return dao.AddBook(book);
    }
    
    
}
