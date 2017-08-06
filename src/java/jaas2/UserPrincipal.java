package jaas2;

import java.security.Principal;

public class UserPrincipal
  implements Principal
{
  private String userName;

  public UserPrincipal(String userName)
  {
    this.userName = userName;
  }

  @Override
  public String getName() {
    return this.userName;
  }
  
  @Override
  public boolean equals(Object o){
      return (o instanceof UserPrincipal) && this.userName.equalsIgnoreCase(((UserPrincipal)o).userName);
  }
  
  @Override
  public int hashCode()
  {
      return userName.toUpperCase().hashCode();
  }
}