/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.restful.room;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import llk.core.RoomContainer;
import javax.json.Json;
import javax.json.JsonArray;
import javax.servlet.http.HttpServlet;
/**
 *
 * @author tictaclu
 */
@WebServlet("/initGame")
public class InitGameServlet extends HttpServlet {
        @Inject private RoomContainer roomContainer;
        
        public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
            System.out.println("am in initGame");
            String roomid = req.getParameter("id");
            System.out.println(roomid);
            int [][] plate = roomContainer.initGame(roomid);

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
                try (PrintWriter pw = resp.getWriter()) {
                    pw.println(allcards.toString());
                     System.out.println(allcards.toString());
                       System.out.println(">>>>>sent in json Builder ");
                }  
        }
}
