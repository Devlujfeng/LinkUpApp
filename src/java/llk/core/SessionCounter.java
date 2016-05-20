/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import llk.restful.ParticipantList;
import llk.restful.lobby.getOnlinePlayersTask;

/**
 *
 * @author tictaclu
 */
public class SessionCounter {

    private ParticipantList participants;
    private ManagedScheduledExecutorService service;
    @Inject private RoomContainer RoomContainer;
    
    @Inject
    public void setParticipantList(ParticipantList p) {
        participants = p;
    }
    
    @Resource(lookup = "concurrent/myFirstPool")
    public void setMyFristPool(ManagedScheduledExecutorService svc) {
        service = svc;
    }
        
    public void refreshSession(){
        HashMap<String, HttpSession> sessionList = RoomContainer.getOnlinePlayers();
        Set<String> namelist = sessionList.keySet();
        String [] nameStringList = namelist.toArray(new String[namelist.size()]);
        Arrays.sort(nameStringList);
        service.submit(new getOnlinePlayersTask(nameStringList,sessionList,participants));
    }
}
