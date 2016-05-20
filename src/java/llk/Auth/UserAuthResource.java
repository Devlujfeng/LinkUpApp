/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.Auth;

import java.math.BigDecimal;
import java.security.*;
import java.util.Arrays;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import llk.EJBean.UserEBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import llk.core.RoomContainer;
import llk.model.Friends;
import llk.model.Users;
/**
 *
 * @author tictaclu
 */

@RequestScoped
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserAuthResource {
    @Inject private RoomContainer RoomContainer;
    @EJB private UserEBean userBean;
    @Context private HttpServletRequest request;
    
    @GET
    @Path("/login")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response get(@QueryParam("email") String email,
                        @QueryParam("passwd") String password){
        System.out.println(email + "----" + password);
       if(userBean.login(email, password)){
           Users u = userBean.getAllUsersDetails(email);
           HttpSession session = request.getSession();
           session.setAttribute("User", u);
           RoomContainer.updateOnlinePlayers(email, session);
           List<Users> lu = userBean.getAllFriends(email);
           lu.add(u);
           JsonArrayBuilder JsonAB = Json.createArrayBuilder();
                for(Users uobj : lu){
                    JsonAB.add(
                             Json.createObjectBuilder()
                            .add("accountName", uobj.getEmail())
                            .add("emailAddr", uobj.getName())
                            .add("scores", uobj.getScores())
                            .add("photoIdentifier", uobj.getPhotoIdentifier())
                     );
                }
            JsonArray allFriends = JsonAB.build(); 
           return (Response.ok(allFriends).build());
        }
        else{
           JsonObject status = Json.createObjectBuilder()
                   .add("status","Email Address or Password is wrong.").build();
            return (Response.ok(status).build());
        }
    }
    
    
    @GET
    @Path("/getfriends")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getfriends(){
        
        HttpSession session = request.getSession();
        Users useraccount = (Users)session.getAttribute("User");
        if(useraccount == null){
            JsonObject status = Json.createObjectBuilder()
                   .add("status","Please login first").build();
            return (Response.ok(status).build());
        }else{
            Users u = userBean.getAllUsersDetails(useraccount.getEmail());
            List<Users> lu = userBean.getAllFriends(useraccount.getEmail());
            lu.add(u);
           JsonArrayBuilder JsonAB = Json.createArrayBuilder();
                for(Users uobj : lu){
                    JsonAB.add(
                             Json.createObjectBuilder()
                            .add("accountName", uobj.getName())
                            .add("emailAddr", uobj.getEmail())
                            .add("scores", uobj.getScores())
                            .add("photoIdentifier", uobj.getPhotoIdentifier())
                     );
                }
            JsonArray allFriends = JsonAB.build(); 
           return (Response.ok(allFriends).build());
        }
    }

    @GET
    @Path("/register")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createNewUser(@QueryParam("accountName") String accountName,
            @QueryParam("email") String email,@QueryParam("passwd") String password){
            Users u = new Users();
            u.setName(accountName);
            u.setEmail(email);
            u.setPassword(password);
            u.setScores(0);
            try {
            u.setPhotoIdentifier(userBean.getmd5(email));
            } catch (Exception e) {
            }
            if(userBean.createNewUser(u)){
            HttpSession session = request.getSession();
            session.setAttribute("User", u);
            RoomContainer.updateOnlinePlayers(email, session);
            JsonObject status = Json.createObjectBuilder()
                   .add("status","success").build();
            return (Response.ok(status).build());
            }else{
            JsonObject status = Json.createObjectBuilder()
                   .add("status","The email has been registered by others.").build();
            return (Response.ok(status).build());
            }
    }
    
    @POST
    @Path("/logout")
     @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void logout(@QueryParam("email") String email){
                System.out.println("get" + request.getSession().getAttribute("User"));
                request.getSession().invalidate();
                System.out.println("get2" + request.getSession().getAttribute("User"));
    }
    
    
    
    
     @GET
     @Path("/getmd5")
     @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getmd5(@QueryParam("accountName") String accountName) throws  Exception{
            System.out.println("test" + accountName);        
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(accountName.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                  sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
               }
              JsonObject Jsonojb = Json.createObjectBuilder()
                      .add("md5", sb.toString()).build();
              return(Response.ok(Jsonojb).build());
    }
    
    
     @GET
     @Path("/getMyInfo")
     @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getMyInfo(){
        HttpSession session = request.getSession();
        Users useraccount = (Users)session.getAttribute("User");
              JsonObject Jsonojb = Json.createObjectBuilder()
                      .add("userName", useraccount.getName())
                      .add("userEmail", useraccount.getEmail())
                      .add("userScores", useraccount.getScores())
                      .add("userPhoto", useraccount.getPhotoIdentifier())
                      .build();
              return(Response.ok(Jsonojb).build());
    }
}
