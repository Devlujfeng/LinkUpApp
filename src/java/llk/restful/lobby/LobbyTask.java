/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.lobby;

import llk.restful.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.sse.OutboundEvent;

/**
 *
 * @author cmlee
 */
public class LobbyTask implements Runnable {

    private ParticipantList participants;
    String gameId;
    String roomName;
    String limitation;
    String userName;
public LobbyTask(String roomName,String limit, String userName,String GameId, ParticipantList p) {
        participants = p;
        gameId = GameId;
             this.roomName = roomName;
             this.limitation = limit;
             this.userName = userName;
    }
    @Override
    public void run() {
        System.out.println(">>> broadcasting message: " + gameId);
        JsonObject json = Json.createObjectBuilder()
                .add("GameId", gameId)
                .add("roomName", roomName)
                .add("limitation", limitation)
                .add("createdBy", userName)
                .build();
        OutboundEvent data = new OutboundEvent.Builder()
                .data(JsonObject.class, json)
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .build();
        participants.send(data);
    }

}
