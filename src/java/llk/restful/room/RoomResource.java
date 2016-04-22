package llk.restful.room;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import llk.restful.ParticipantList;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

@RequestScoped
@Path("/game/{gameroom}")
public class RoomResource {
    @Inject private ParticipantList participants;
    
    
    @Resource(lookup = "concurrent/myFirstPool")
    private ManagedScheduledExecutorService service;
    
    @GET     
    @Produces(SseFeature.SERVER_SENT_EVENTS)
     
    public Response connect() {       
         System.out.println(">>> new connection 3 for Playing Game");
         EventOutput eo = new EventOutput();
         participants.add(eo);
          System.out.println(">>> new connection 3 for Playing Game");
         return (Response.ok(eo).build());
    }
}
