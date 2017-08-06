package jaas2;

import Manager.Manager.Manager;
import Manager.ManagerDao;
import java.util.Map;
import java.util.Set;  
import java.util.Arrays;
import javax.security.auth.spi.LoginModule; 
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;

public class MyLoginModule implements LoginModule {
    // initial state
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map<String, ?> options;
    private static ManagerDao dao;
    private UserPrincipal userPrincipal;
    private SimplePrincipal simplePrincipal;
    
    public void setDao(ManagerDao dao){this.dao = dao;}
    
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) throw new LoginException("no handler");
        try {
            NameCallback nameCall = new NameCallback("username: ");
            PasswordCallback passCall = new PasswordCallback("password: ", false);
            callbackHandler.handle(new Callback[] {nameCall, passCall});
            if(dao.ManagerLogin(nameCall.getName(),String.valueOf(passCall.getPassword()))){
                userPrincipal = new UserPrincipal(nameCall.getName());
                simplePrincipal = new SimplePrincipal("manager");
                return true;
            }
        }
        catch (UnsupportedCallbackException e) {
            LoginException e2 = new LoginException("Unsupported callback");
            e2.initCause(e);
            throw e2;
        }
        catch (IOException e) {
            LoginException e2 = new LoginException("I/O exception in callback");
            e2.initCause(e);
            throw e2;
        }

        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        this.subject.getPrincipals().add(userPrincipal);
        this.subject.getPrincipals().add(simplePrincipal);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }
}
