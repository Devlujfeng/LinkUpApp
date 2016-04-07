package llk.restful;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

@RequestScoped
@Path("{chat}")
public class ChatroomResource {
    @Inject private ParticipantList participants;
     @GET     
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public Response connect(@PathParam("chat") String name) {
        
         System.out.println(">>> new connection "+ name);
         EventOutput eo = new EventOutput();
         participants.add(eo);
          System.out.println(">>> new connection 2"+ name);
         return (Response.ok(eo).build());
    }
}
