/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;
import User.User.User;
import User.webService.SoapWebService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
@Aspect
public class UserAction extends ActionSupport {
    private UserService service;
    private User user;
    
    public void setService(UserService service)
    {
        this.service = service;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    
    public UserAction() {
    }
    
    public String UserRegister()
    {
        user.setUsername(ServletActionContext.getRequest().getParameter("username"));
        user.setPassword(ServletActionContext.getRequest().getParameter("password"));
        String c_password = ServletActionContext.getRequest().getParameter("c_password");
        user.setEmail(ServletActionContext.getRequest().getParameter("email"));
        user.setMobile(ServletActionContext.getRequest().getParameter("mobile"));
        if(!c_password.equals(user.getPassword()))
            return ERROR;
        if(this.service.UserRegister(user).equals("success"))
        {
            user = service.getUserByName(user.getUsername());
            HttpSession session = ServletActionContext.getRequest().getSession();  
            session.setAttribute("userid", user.getId()); 
            return SUCCESS;
        }
        return ERROR;
    }
    
    @Pointcut("execution(* Order.OrderAction.JumpMyOrder())")
    public void Point_Cut(){}
    
    @Before("Point_Cut()")
    public void Before()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();  
        if(session.getAttribute("userid") == null || session.getAttribute("userid").equals("null"))
        {
            ServletActionContext.getRequest().setAttribute("login_flag", "false");
            session.setAttribute("login_flag", "false");
            System.out.println("The user is not login!");
        }
        else{
            session.setAttribute("login_flag", "true");
            System.out.println("The user is login!");
        }
    }

    public String UserLogin()
    {
        user.setUsername(ServletActionContext.getRequest().getParameter("username"));
        user.setPassword(ServletActionContext.getRequest().getParameter("password"));
        System.out.println("user logging in ...\n");
        if(service.UserLogin(user).equals("success"))
        {
            user = service.getUserByName(user.getUsername());
            ServletActionContext.getRequest().setAttribute("User", user);
            System.out.println(user.getUsername());
            HttpSession session = ServletActionContext.getRequest().getSession();  
            session.setAttribute("userid", user.getId());
            session.setAttribute("username",user.getUsername());
            
            ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:User/webService/cxf.xml");
            SoapWebService soap_service = (SoapWebService)ctx.getBean("SoapWebService_Publish");
            User cuser = soap_service.testSoap(user.getId());
            System.out.println(cuser.getId()+":"+cuser.getUsername()+" Mobile number:"+cuser.getMobile()+" Email:"+cuser.getEmail());
            ServletActionContext.getRequest().setAttribute("username", cuser.getUsername());
            String currentLanguage = (String)session.getAttribute("language");
            if(currentLanguage==null){
                currentLanguage ="0";
            }
            session.setAttribute("language", currentLanguage);
        
        //0:新用户注册/1:用户登录/2:管理员登录/3:书店/4:聊天室
        if(currentLanguage.equals("1")||currentLanguage.equals("0")){
            Locale locale = Locale.US;
            ResourceBundle bundle = ResourceBundle.getBundle("Sources/Index", locale);
            ServletActionContext.getRequest().setAttribute("register", bundle.getString("register"));
            ServletActionContext.getRequest().setAttribute("userLogin", bundle.getString("userLogin"));
            ServletActionContext.getRequest().setAttribute("managerLogin", bundle.getString("managerLogin"));
            ServletActionContext.getRequest().setAttribute("bookstore", bundle.getString("bookstore"));
            ServletActionContext.getRequest().setAttribute("chatroom", bundle.getString("chatroom"));
            ServletActionContext.getRequest().setAttribute("luceneSearch", bundle.getString("luceneSearch"));
        }
        else if(currentLanguage.equals("2")){
                Locale locale = Locale.CHINESE;
                ResourceBundle bundle = ResourceBundle.getBundle("Sources/Index", locale);
                ServletActionContext.getRequest().setAttribute("register", bundle.getString("register"));
                ServletActionContext.getRequest().setAttribute("userLogin", bundle.getString("userLogin"));
                ServletActionContext.getRequest().setAttribute("managerLogin", bundle.getString("managerLogin"));
                ServletActionContext.getRequest().setAttribute("bookstore", bundle.getString("bookstore"));
                ServletActionContext.getRequest().setAttribute("chatroom", bundle.getString("chatroom"));
                ServletActionContext.getRequest().setAttribute("luceneSearch", bundle.getString("luceneSearch"));
            }
            
            return SUCCESS;
        }
        return ERROR;
    }
    
     public String JumpToIndex()
    {
        //0: default(English),1:English,2:Chinese
        HttpSession session = ServletActionContext.getRequest().getSession();  
        String currentLanguage = (String)session.getAttribute("language");
        String flag = ServletActionContext.getRequest().getParameter("languageFlag");
        if(currentLanguage==null || flag ==null){
            currentLanguage ="0";
        }
        else{
            if(flag.equals("1")){
                currentLanguage = "1";
            }
            else if(flag.equals("2")){
                currentLanguage = "2";
            }
        }
        session.setAttribute("language", currentLanguage);
        
        //0:新用户注册/1:用户登录/2:管理员登录/3:书店/4:聊天室
        if(currentLanguage.equals("1")||currentLanguage.equals("0")){
            Locale locale = Locale.US;
            ResourceBundle bundle = ResourceBundle.getBundle("Sources/Index", locale);
            ServletActionContext.getRequest().setAttribute("register", bundle.getString("register"));
            ServletActionContext.getRequest().setAttribute("userLogin", bundle.getString("userLogin"));
            ServletActionContext.getRequest().setAttribute("managerLogin", bundle.getString("managerLogin"));
            ServletActionContext.getRequest().setAttribute("bookstore", bundle.getString("bookstore"));
            ServletActionContext.getRequest().setAttribute("chatroom", bundle.getString("chatroom"));
             ServletActionContext.getRequest().setAttribute("luceneSearch", bundle.getString("luceneSearch"));
        }
        else if(currentLanguage.equals("2")){
            Locale locale = Locale.CHINESE;
            ResourceBundle bundle = ResourceBundle.getBundle("Sources/Index", locale);
            ServletActionContext.getRequest().setAttribute("register", bundle.getString("register"));
            ServletActionContext.getRequest().setAttribute("userLogin", bundle.getString("userLogin"));
            ServletActionContext.getRequest().setAttribute("managerLogin", bundle.getString("managerLogin"));
            ServletActionContext.getRequest().setAttribute("bookstore", bundle.getString("bookstore"));
            ServletActionContext.getRequest().setAttribute("chatroom", bundle.getString("chatroom"));
             ServletActionContext.getRequest().setAttribute("luceneSearch", bundle.getString("luceneSearch"));
        }
        
        ServletActionContext.getRequest().setAttribute("username",session.getAttribute("username"));
        return SUCCESS;
    }
    
    public String JumpUserLogin()
    {
        return SUCCESS;
    }
    
    public String JumpUserRegister()
    {
        return SUCCESS;
    }
    
    public String JumpChatRoom()
    {
        System.out.println("Jumping to chatroom...");
        //HttpSession session = ServletActionContext.getRequest().getSession();
        //String username = (String) session.getAttribute("username");
        //ServletActionContext.getRequest().setAttribute("username", username);
        return SUCCESS;
    }
    
    
     public String JumpUserAffair()
    {
        List<User> users = service.SearchUser();
        ServletActionContext.getRequest().setAttribute("userlist", users);
        return SUCCESS;
    }
}
