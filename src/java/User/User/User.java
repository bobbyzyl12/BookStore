
package User.User;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    
    // Get & Set Methods
    
    // Id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    // Username
    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    // Password
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    // Email
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    // Mobile
    public String getMobile()
    {
        return this.mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
}
