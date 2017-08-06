
package Manager.Manager;

import java.io.Serializable;

public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
   
    private String managername;
    
    private String password;

    public Manager() {
    }

    public Manager(Integer id) {
        this.id = id;
    }

    public Manager(Integer id, String managername, String password) {
        this.id = id;
        this.managername = managername;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
