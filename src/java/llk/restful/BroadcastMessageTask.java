/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.sse.OutboundEvent;

/**
 *
 * @author cmlee
 */
public class BroadcastMessageTask implements Runnable {

    private String name;
    private String message;
    private ParticipantList participants;
    

    public BroadcastMessageTask(String n, String m, ParticipantList p) {
        name = n;
        message = m;
        participants = p;
     
    }

    @Override
    public void run() {
        
        System.out.println(">>> broadcasting message: " + message);

        JsonObject json = Json.createObjectBuilder()
                .add("name", name)
                .add("message", message)
                .build();
        OutboundEvent data = new OutboundEvent.Builder()
                .data(JsonObject.class, json)
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .build();
        participants.send(data);
    }

}
