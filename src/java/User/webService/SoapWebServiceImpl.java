/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.webService;

import User.User.User;
import javax.jws.WebService;
import User.UserService;
import java.util.List;
@WebService(endpointInterface="User.webService.SoapWebService",serviceName="SoapWebService")
public class SoapWebServiceImpl implements SoapWebService{
   private static UserService service;
    
   public void setService(UserService service){
       SoapWebServiceImpl.service = service;
   }
   
    @Override
    public User testSoap(Integer uid){
        return service.getUserById(uid);
    }
}
