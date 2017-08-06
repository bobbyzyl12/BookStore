package User.webService;

import User.User.User;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SoapWebService {
    @WebMethod(operationName="testSoap")
    public User testSoap(Integer uid);
}
