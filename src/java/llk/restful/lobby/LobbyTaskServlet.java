/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.lobby;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import llk.core.RoomContainer;
import llk.restful.ParticipantList;

@WebServlet("/newGame")
public class LobbyTaskServlet extends HttpServlet {
    
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
        
        String roomName = req.getParameter("roomName");
        String limitation = req.getParameter("limitation");
        String userName = req.getParameter("userName");
        
        String roomNo = RoomContainer.createGame(roomName,limitation,userName);
        service.submit(new LobbyTask(roomName,limitation,userName,roomNo,participants));
        PrintWriter out = resp.getWriter();
        out.write(roomNo);
    }        
}
