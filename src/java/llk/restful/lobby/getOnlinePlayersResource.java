package llk.restful.lobby;

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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import llk.core.RoomContainer;
import llk.model.Users;
import llk.restful.ParticipantList;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;
import javax.servlet.http.HttpSession;

@RequestScoped
@Path("/allPlayers")
public class getOnlinePlayersResource {
    @Inject private ParticipantList participants;
    @Context private HttpServletRequest request;
    
    @Resource(lookup = "concurrent/myFirstPool")
    private ManagedScheduledExecutorService service;
    
    @GET     
    @Produces(SseFeature.SERVER_SENT_EVENTS)
     
    public Response connect() {
         System.out.println(">>> new connection allPlayers ");
         EventOutput eo = new EventOutput();
         participants.add(eo);
         System.out.println(">>> new connection 2 allPlayers");
         return (Response.ok(eo).build());
    }
    
    
    @Inject private RoomContainer RoomContainer;
    @GET
    @Path("/getOnlinePlayerRestful")
    public JsonArray getOnlinePlayerRestful() {
            HashMap<String, HttpSession> sessionList = RoomContainer.getOnlinePlayers();
            System.out.println("test sessionList size: " + sessionList.size());
            Set<String> namelist = sessionList.keySet();
            String [] nameStringList = namelist.toArray(new String[namelist.size()]);
            Arrays.sort(nameStringList);
              System.out.println("test namestringlist size: " + nameStringList.length);
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
                   } 
                   catch (Exception e) {
                       
                   }
               }
               JsonArray allOnline = JsonAB.build();
               return(allOnline);
    }
}
