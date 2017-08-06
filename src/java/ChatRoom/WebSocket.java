
package ChatRoom;

import java.io.IOException;  
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;  
import java.util.Set;  
  
import javax.servlet.http.HttpSession;  
import javax.websocket.EndpointConfig;  
import javax.websocket.OnClose;  
import javax.websocket.OnError;  
import javax.websocket.OnMessage;  
import javax.websocket.OnOpen;  
import javax.websocket.Session;  
import javax.websocket.server.ServerEndpoint; 
import org.apache.struts2.ServletActionContext;

//import org.jp.util.JsonUtil;  
//import org.jp.websocket.service.WebSocketService;  
import org.springframework.context.ApplicationContext;  
//import org.springframework.web.socket.server.endpoint.SpringConfigurator;  
import org.springframework.web.context.support.WebApplicationContextUtils;  

import javax.json.*;

@ServerEndpoint(value = "/websocket/chat") 
public class WebSocket {
    static Set<Session> USERS =Collections.synchronizedSet( new HashSet<Session>());
            
    @OnOpen  
    public void onOpen(Session session, EndpointConfig config) throws IOException, InterruptedException  {  
       System.out.println("Session "+ session.getId() +" connected.");
       USERS.add(session);
    }  
    
    @OnClose
    public void onClose(Session session){
        USERS.remove(session);
        System.out.println("Session "+ session.getId() +" removeed.");
    }
    
    @OnMessage
    public void onMessage(Session session,String message) throws IOException{
        System.out.println("Receiving Message from Session "+ session.getId() +":" + message);
        Iterator<Session> iter = USERS.iterator();
        //HttpSession httpSession = ServletActionContext.getRequest().getSession();  
        //String username = (String) httpSession.getAttribute("username");
        while(iter.hasNext()){
            try{
                iter.next().getBasicRemote().sendText(message);
            }
            catch(IOException e){
            }
        }
    }
    
    private String bulidJsonData(String username,String message){
        JsonObject jsonObject =Json.createObjectBuilder().add("message",username+":"+message).build();
        StringWriter stringWriter =new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stringWriter)){
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }
}
