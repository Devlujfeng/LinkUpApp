/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.lobby;

import java.math.BigDecimal;
import java.util.HashMap;
import llk.restful.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import llk.model.Users;
import org.glassfish.jersey.media.sse.OutboundEvent;

/**
 *
 * @author cmlee
 */
public class getOnlinePlayersTask implements Runnable {
    
    String [] namelist;
    final private ParticipantList participants;
    HashMap<String,HttpSession> nameMap;
    
    public getOnlinePlayersTask(String [] namelist, HashMap<String,HttpSession> nameMap ,ParticipantList p) {
        participants = p;
        this.namelist = namelist;
        this.nameMap = nameMap;
    }
    @Override
    public void run() {
        System.out.println(">>> broadcasting message: online users.");
               JsonArrayBuilder JsonAB = Json.createArrayBuilder();
               for(String s : namelist){
                   Users u = (Users)nameMap.get(s).getAttribute("User");
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
               JsonArray allOnline = JsonAB.build();
               
               OutboundEvent data = new OutboundEvent.Builder()
                       .data(JsonArray.class, allOnline)
                       .name("getOnlinePlayer")
                       .mediaType(MediaType.APPLICATION_JSON_TYPE)
                       .build();
               System.out.println("sending online users list------------------------");
               participants.send(data);
    }

}
