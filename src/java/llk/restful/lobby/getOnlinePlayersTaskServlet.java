/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.lobby;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import llk.core.RoomContainer;
import llk.model.Users;
import llk.restful.ParticipantList;

/**
 *
 * @author tictaclu
 */
@WebServlet("/getonlineplayers")
public class getOnlinePlayersTaskServlet extends HttpServlet {
 
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
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException { 
        HashMap<String, HttpSession> sessionList = RoomContainer.getOnlinePlayers();
        Set<String> namelist = sessionList.keySet();
        String [] nameStringList = namelist.toArray(new String[namelist.size()]);
        Arrays.sort(nameStringList);
        service.submit(new getOnlinePlayersTask(nameStringList,sessionList,participants));
        PrintWriter out = resp.getWriter();
        out.write("Happy holiday");
    }
}
