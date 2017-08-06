package Manager;

import Manager.Manager.Manager;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import jaas2.SimpleCallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class ManagerAction extends ActionSupport {
    private ManagerService service;
    public ManagerAction() {
    }
    public void setService(ManagerService service)
    {
        this.service = service;
    }
    
    public String Login()
    {
        try{
            Manager manager = new Manager();
            manager.setManagername(ServletActionContext.getRequest().getParameter("j_username"));
            manager.setPassword(ServletActionContext.getRequest().getParameter("j_password"));
            LoginContext context = new LoginContext("Login1",new SimpleCallbackHandler(manager.getManagername(),manager.getPassword()));
            context.login();
            HttpSession session = ServletActionContext.getRequest().getSession();  
            session.setAttribute("managerName", manager.getManagername());  
            return SUCCESS;
        }
        catch (LoginException e)
        {
            e.printStackTrace();
            return ERROR;
         }
    }
    
    public String ManagerLogin()
    {
            Manager manager = new Manager();
            manager.setManagername(ServletActionContext.getRequest().getParameter("j_username"));
            manager.setPassword(ServletActionContext.getRequest().getParameter("j_password"));
            //LoginContext context = new LoginContext("Login1",new SimpleCallbackHandler(manager.getManagername(),manager.getPassword()));
            //context.login();
            HttpSession session = ServletActionContext.getRequest().getSession();  
            session.setAttribute("managerName", manager.getManagername());  
            return SUCCESS;
    }
    
    public String JumpBookAffair()
    {
        return SUCCESS;
    }
    
    public String JumpManagerLogin()
    {
        return SUCCESS;
    }
}
