/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.EJBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import llk.model.Users;
/**
 *
 * @author tictaclu
 */
@Stateless
public class UserEBean {
      @PersistenceContext private EntityManager em;
      
      public boolean login(String username, String password){
          boolean flag = false;
          try{
          TypedQuery<Users> namedQuery = em.createNamedQuery("Users.findByAccountName",Users.class);
          namedQuery.setParameter("AccountName", username);
          String val = namedQuery.getSingleResult().getPassword();
                  if(val.equalsIgnoreCase(password)){
           // System.out.println("1");
            return flag;
        }
        else{
           // System.out.println("2");
            flag = false;
            return flag;
        }
        }
        catch (Exception e) {
            flag = false;
            return flag;
        }
      }
}
