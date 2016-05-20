/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.room;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import llk.core.RoomContainer;
import llk.core.matchCore;
import llk.model.Users;
import llk.restful.ParticipantList;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

@RequestScoped
@Path("/room/{roomid}")
public class FindRoomPlayersResource {
        @Context private HttpServletRequest request;
    @Inject private ParticipantList participants;
//FindRoomPlayers
    @Resource(lookup = "concurrent/myFirstPool")
    private ManagedScheduledExecutorService service;
    
    @GET     
    @Produces(SseFeature.SERVER_SENT_EVENTS)
     
    public Response connect() {       
         System.out.println(">>> new connection 3 for finding rooms players");
         EventOutput eo = new EventOutput();
         participants.add(eo);
          System.out.println(">>> new connection 3 for finding rooms players");
         return (Response.ok(eo).build());
    }
    
    @Inject private RoomContainer RoomContainer;
    @GET
    @Path("/getRoomPlayersByFresh")
    public JsonArray getRoomPlayersByFresh(@PathParam("roomid") String roomNo) {
            System.out.println(roomNo + "*************where is room no?");
            matchCore mc = RoomContainer.getAllRoom().get(roomNo);
            HashMap<String, HttpSession> sessionList = mc.getRoomPlayers();
            Set<String> namelist = sessionList.keySet();
            String [] nameStringList = namelist.toArray(new String[namelist.size()]);
            Arrays.sort(nameStringList);
              
              JsonArrayBuilder JsonAB = Json.createArrayBuilder();
               for(String s : namelist){
                   try {  
                   Users u = (Users)sessionList.get(s).getAttribute("User");
                    if(u != null){
                        JsonAB.add(
                                   Json.createObjectBuilder()
                                    .add("accountName", u.getName())
                                    .add("email", u.getEmail())
                                    .add("scores", u.getScores())
                                    .add("photoIdentifier", u.getPhotoIdentifier())
                        );       
                    }  
                    } catch (Exception e) {
                    } 
               }
               JsonArray allOnline = JsonAB.build();
               return(allOnline);
    }
}
