/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.room;

import llk.restful.room.*;
import java.io.IOException;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import llk.core.RoomContainer;
import llk.restful.ParticipantList;

@WebServlet("/playGame")
public class RoomTaskServlet extends HttpServlet {
    
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
        
        String AcardID = req.getParameter("AcardID");
        String BcardID = req.getParameter("BcardID");
        System.out.println(AcardID + "-----"+  BcardID);
        int [] A = idConverter(AcardID);
        int [] B = idConverter(BcardID);
        String roomID = req.getParameter("RoomID");
        
        if(RoomContainer.ReturnResult(roomID, A, B)){
            int[][] plate = RoomContainer.getNowGame(roomID);
            service.submit(new RoomTask(participants,roomID,plate));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }
    public int [] idConverter(String AcardID){
        int number = Integer.parseInt(AcardID);
        int row =  (int)Math.floor(number/21);
        int column = number%21;
        int [] identity = {row,column};
        System.out.println(row + "======" + column);
        return identity;
    }
    
}
