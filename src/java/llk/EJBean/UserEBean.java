/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.EJBean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import llk.model.Friends;
import llk.model.Users;
/**
 *
 * @author tictaclu
 */
@Stateless
public class UserEBean {
      @PersistenceContext private EntityManager em;
      
      public boolean login(String username, String password){
          boolean flag = true;
          try{
          TypedQuery<Users> namedQuery = em.createNamedQuery("Users.findByEmail",Users.class);
          namedQuery.setParameter("email", username);
          String val = namedQuery.getSingleResult().getPassword();
            if(val.equalsIgnoreCase(password)){
                return flag;
            }
            else{
                flag = false;
                return flag;
            }
            }
            catch (Exception e) {
                flag = false;
                return flag;
            }
      }
      
     public Users getAllUsersDetails(String accountName){
         return em.find(Users.class, accountName);
     }
     public List<Users> getAllFriends(String email){
            List<Users> lu = new ArrayList<Users>();
            TypedQuery<Friends> query = em.createQuery("select uf from Users u join u.friendsCollection uf where u.email = :email",Friends.class);
            query.setParameter("email", email);
            for( Friends f : query.getResultList()){  
            TypedQuery<Users> namedQuery = em.createNamedQuery("Users.findByEmail",Users.class);
            namedQuery.setParameter("email", f.getFriendsEmail());
            lu.add(namedQuery.getSingleResult());
            }
            return lu;
    }  
     public boolean createNewUser(Users u){
            try {
             TypedQuery<Users> namedQuery = em.createNamedQuery("Users.findByEmail",Users.class);
             namedQuery.setParameter("email", u.getEmail());
                System.out.println(namedQuery.getResultList().size());
             if(namedQuery.getResultList().size() != 0){
                 return false;
             }
             else{
                 em.persist(u);
              return true;   
             }
            } catch (Exception e) {

                return false;
            }
     }
     
     public String getmd5(String email) throws  Exception{
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(email.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                 sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
               }
                return sb.toString();
     }
}