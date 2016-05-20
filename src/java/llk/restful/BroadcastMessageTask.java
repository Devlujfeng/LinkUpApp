/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful;

import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import llk.model.Users;
import org.glassfish.jersey.media.sse.OutboundEvent;

/**
 *
 * @author cmlee
 */
public class BroadcastMessageTask implements Runnable {

    private String name;
    private String message;
    private ParticipantList participants;
    private String chatname;
    private Users u;

    public BroadcastMessageTask(String n, String m, ParticipantList p, String ch,Users u) {
        name = n;
        message = m;
        participants = p;
        chatname = ch;
        this.u = u;
    }

    @Override
    public void run() {
        
        System.out.println(">>> broadcasting message: " + message);

        JsonObject json = Json.createObjectBuilder()
                .add("name", name)
                .add("message", message)
                .add("imgId", u.getPhotoIdentifier())
                .build();
        OutboundEvent data = new OutboundEvent.Builder()
                .data(JsonObject.class, json)
                .name(chatname)
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .build();
        participants.send(data);
    }

}
