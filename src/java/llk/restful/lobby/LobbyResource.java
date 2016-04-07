package llk.restful.lobby;


import llk.restful.BroadcastMessageTask;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import llk.core.RoomContainer;
import llk.restful.ParticipantList;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

@RequestScoped
@Path("/lobby")
public class LobbyResource {
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
//    @Path("/newGame")
//    public Response getRoomName() {
//        System.out.println("???");
//        EventOutput eo = new EventOutput();
//        service.submit(new LobbyTask(RoomContainer.createGame(),participants));
//        return (Response.ok(eo).build());
//    }
}
