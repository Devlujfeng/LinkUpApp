package llk.restful.room;

import llk.restful.room.*;
import java.util.Set;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import llk.core.RoomContainer;
import llk.restful.ParticipantList;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

@RequestScoped
@Path("/{gameroom}")
public class RoomResource {
    @Inject private ParticipantList participants;
    
    
    @Resource(lookup = "concurrent/myFirstPool")
    private ManagedScheduledExecutorService service;
    
    @GET     
    @Produces(SseFeature.SERVER_SENT_EVENTS)
     
    public Response connect() {       
         System.out.println(">>> new connection ");
         EventOutput eo = new EventOutput();
         participants.add(eo);
          System.out.println(">>> new connection 2");
         return (Response.ok(eo).build());
    }
    
//    @GET
//    @Path("/newMessage")
//    @Produces(SseFeature.SERVER_SENT_EVENTS)
//    public Response get(
//             @QueryParam("name")String name,
//             @QueryParam("message")String msg) {
//        EventOutput eo = new EventOutput();
//        System.out.println(name + ">>> " + msg);
//        service.submit(new BroadcastMessageTask(name, msg, participants));
//        return (Response.ok(eo).build());
//    }
//    
//    @Inject private RoomContainer RoomContainer;
//    @GET
//    @Path("/getAllRooms")
//    public JsonArray getAllRooms() {
//        System.out.println("test");
//        JsonArrayBuilder builder = Json.createArrayBuilder();
//        Set<String> keys = RoomContainer.getAllRoom().keySet();
//        
//        String [] rooms = keys.toArray(new String[keys.size()]);
//        for(int i=0; i < keys.size(); i++){
//            System.out.println(rooms[i]);
//        }
//        for(int i=0; i < keys.size(); i++){
//            builder.add(
//            Json.createObjectBuilder()
//                    .add("rooms",rooms[i])
//            );
//        }
//        return (builder.build());
//    }
}
