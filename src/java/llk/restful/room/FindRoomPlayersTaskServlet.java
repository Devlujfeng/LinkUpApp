/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.room;

import llk.restful.room.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import llk.core.RoomContainer;
import llk.core.matchCore;
import llk.model.Users;
import llk.restful.ParticipantList;
import llk.restful.lobby.getOnlinePlayersTask;

@WebServlet("/joinRoom")
public class FindRoomPlayersTaskServlet extends HttpServlet {
    
    //@Inject 
    private ParticipantList participants;
    
    //@Resource(lookup = "concurrent/myFirstPool")
    private ManagedScheduledExecutorService service;
    
    @Inject
    public void setParticipantList(ParticipantList p) {
        participants = p;
    }
    
    @Resource(lookup = "concurrent/myFirstPool")
    public void setMyFristPool(ManagedScheduledExecutorService svc) {
        service = svc;
    }
    @Inject private RoomContainer RoomContainer;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException { 
            HttpSession session = req.getSession();
            Users useraccount = (Users)session.getAttribute("User");
            String roomNo = req.getParameter("roomNo");
            System.out.println(roomNo + "*************where is room no?");
            matchCore mc = RoomContainer.getAllRoom().get(roomNo);
            
            mc.updateRoomPlayers(useraccount.getEmail(), session);
            
            HashMap<String, HttpSession> sessionList = mc.getRoomPlayers();
            Set<String> namelist = sessionList.keySet();
            String [] nameStringList = namelist.toArray(new String[namelist.size()]);
            Arrays.sort(nameStringList);
            
            service.submit(new FindRoomPlayersTask(nameStringList,sessionList,participants,roomNo));
            resp.setStatus(HttpServletResponse.SC_OK);
    }    
}
