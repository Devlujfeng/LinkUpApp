/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.room;

import llk.restful.room.*;
import llk.restful.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.sse.OutboundEvent;

/**
 *
 * @author cmlee
 */
public class RoomTask implements Runnable {

    private ParticipantList participants;
    String roomNumber;
    int[][] plate;
    
    public RoomTask( ParticipantList p, String rn, int [][] pl) {
        participants = p;
        roomNumber = rn;
        plate = pl;
    }
    @Override
    public void run() {
        
        System.out.println(">>> broadcasting message: " + roomNumber);

            JsonArrayBuilder JsonAB = Json.createArrayBuilder();
                for(int i = 0; i < plate.length; i++){
                    for(int j=0; j < plate[i].length; j++){
                        JsonAB.add(
                                Json.createObjectBuilder()
                                .add("value", plate[i][j])
                        );
                    }
                }
                JsonArray allcards = JsonAB.build(); 
        
        OutboundEvent data = new OutboundEvent.Builder()
                .data(JsonArray.class, allcards)
                .name(roomNumber+"Rd")
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .build();
        participants.send(data);
    }
}
