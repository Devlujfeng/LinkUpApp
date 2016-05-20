/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful;

import llk.restful.ChatroomResource;
import java.io.IOException;
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
import javax.ws.rs.core.MediaType;
import llk.model.Users;
import org.glassfish.jersey.media.sse.OutboundEvent;

@WebServlet("/newMessage")
public class NewMessageServlet extends HttpServlet {
    
    @Inject private ChatroomResource cr;
    private ParticipantList participants;
    
    @Resource(lookup = "concurrent/myFirstPool")
    private ManagedScheduledExecutorService service;
    
    @Inject
    public void setParticipantList(ParticipantList p) {
        participants = p;
    }
    
//    @Resource(lookup = "concurrencySetGame")
//    public void setMyFristPool(ManagedScheduledExecutorService svc) {
//        service = svc;
//    }    

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String name = req.getParameter("username");
        String msg = req.getParameter("message");
        String chatname = req.getParameter("chatRoomId");
        System.out.println(name + ">>> " + msg+ ">>>"+ chatname);
        HttpSession session = req.getSession();
        Users u = (Users)session.getAttribute("User");
        service.submit(new BroadcastMessageTask(name, msg, participants,chatname,u));
        
        resp.setStatus(HttpServletResponse.SC_OK);
    }        
    
}
