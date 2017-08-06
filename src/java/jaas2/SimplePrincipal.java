package jaas2;
import java.security.Principal;
import java.util.Objects;

//confirm roles principal
public class SimplePrincipal implements Principal {
    private String role;

    public SimplePrincipal() {

    }

    public SimplePrincipal(String role) {
        this.role = role;
    }

    //@Override
    public String getName() {
        return role;
    }

    public boolean equals(Object otherObject) {
        boolean flag =false;
        if(this== otherObject) {
            flag = true;
        }
        if (otherObject == null) {
            flag = false;
        }

        if (!(otherObject instanceof SimplePrincipal)) {
            flag =false;
        }
        if (otherObject instanceof SimplePrincipal) {
            SimplePrincipal that = (SimplePrincipal)otherObject;
            if (getName().equals(that.getName()))
            flag = true;
        }

        System.out.println("flag=" + flag);
        return flag;
    }

    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
