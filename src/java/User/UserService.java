package User;

import User.User.User;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class UserService {
    private UserDao dao;
    public void setDao(UserDao dao)
    {
        this.dao = dao;
    }
    
    public User getUserById(int id)
    {
        return dao.getUserById(id);
    }
    
    public User getUserByName(String username)
    {
        return dao.getUserByName(username);
    }
    
    public String UserRegister(User user)
    {
        if(dao.getUserByName(user.getUsername()) != null)
            return "exist";
        if(!dao.UserRegister(user))
            return "error";
        return "success";
    }
    
    public String UserLogin(User user)
    {
        if(dao.getUserByName(user.getUsername()) == null)
            return "nouser";
        if(!dao.UserLogin(user))
            return "error";
        return "success";
    }
    
    public List<User> SearchUser()
    {
        return dao.SearchUser();
    }
}
